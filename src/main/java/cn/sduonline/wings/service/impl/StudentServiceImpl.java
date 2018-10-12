package cn.sduonline.wings.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import cn.sduonline.wings.constant.SettingName;
import cn.sduonline.wings.dao.mapper.CourseMapper;
import cn.sduonline.wings.dao.mapper.SelectMapper;
import cn.sduonline.wings.dao.mapper.SettingMapper;
import cn.sduonline.wings.dao.mapper.StudentMapper;
import cn.sduonline.wings.model.*;
import cn.sduonline.wings.model.condition.CourseCondition;
import cn.sduonline.wings.model.condition.SelectCondition;
import cn.sduonline.wings.model.condition.SettingCondition;
import cn.sduonline.wings.model.condition.StudentCondition;
import cn.sduonline.wings.service.StudentService;
import cn.sduonline.wings.util.AcademyUtil;
import cn.sduonline.wings.util.BeanUtil;
import cn.sduonline.wings.vo.Result;
import cn.sduonline.wings.vo.SelectionVO;

/**
 * Created by imaxct on 18-9-27.
 */
@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentMapper studentMapper;
    private final SettingMapper settingMapper;
    private final SelectMapper selectMapper;
    private final CourseMapper courseMapper;
    private final TransactionTemplate transactionTemplate;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, SettingMapper settingMapper, SelectMapper selectMapper,
        CourseMapper courseMapper, TransactionTemplate transactionTemplate) {
        this.studentMapper = studentMapper;
        this.settingMapper = settingMapper;
        this.selectMapper = selectMapper;
        this.courseMapper = courseMapper;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Student getStudentByNo(String stuNo) {
        StudentCondition condition = new StudentCondition();
        condition.setStudentNo(stuNo);
        List<Student> list = studentMapper.selectByCondition(condition);
        if (CollectionUtils.isEmpty(list) || list.size() != 1) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Student getStudentByCrawler(String stuNo, String password) {
        return new AcademyUtil().getStudent(stuNo, password);
    }

    @Override
    public Student saveStudent(Student student) {
        Assert.notNull(student, "对象为空");
        Student dbStudent = studentMapper.selectByPrimaryKey(student.getId());
        if (null != dbStudent) {
            student = BeanUtil.parseObject(dbStudent, student, Student.class);
        }
        studentMapper.updateByPrimaryKeySelective(student);
        return student;
    }

    public int createStudent(Student student) {
        Assert.notNull(student, "对象为空");
        return studentMapper.insert(student);
    }

    @Override
    public Result getAnnouncement() {
        SettingCondition condition = new SettingCondition();
        condition.setSettingName(SettingName.ANNOUNCEMENT);
        List<Setting> list = settingMapper.selectByCondition(condition);
        if (CollectionUtils.isEmpty(list)) {
            Setting setting = new Setting();
            setting.setSettingName(SettingName.ANNOUNCEMENT);
            setting.setSettingValue("无公告");
            settingMapper.insert(setting);
            return Result.ok(setting.getSettingValue());
        }
        return Result.ok(list.get(0).getSettingValue());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Result<List<SelectionVO>> getSelectedCourse(Long studentId) {
        SelectCondition condition = new SelectCondition();
        condition.setStudentId(studentId);
        List<SelectionVO> list = selectMapper.selectJoinCourse(condition);
        return Result.ok(list);
    }

    @Override
    public Result selectCourse(Student student, Long courseId) {

        SettingCondition settingCondition = new SettingCondition();
        settingCondition.setSettingName(SettingName.SELECT_LIMIT);
        List<Setting> settings = settingMapper.selectByCondition(settingCondition);
        if (!CollectionUtils.isEmpty(settings)) {
            Setting setting = settings.get(0);
            int selectLimit = Integer.parseInt(setting.getSettingValue());
            SelectCondition selectCondition = new SelectCondition();
            selectCondition.setStudentId(student.getId());
            int selected = selectMapper.countByCondition(selectCondition);
            if (selected >= selectLimit) {
                return Result.err("当前选课数量已经超过本期选课数量限制", null);
            }
        }

        return transactionTemplate.execute((t) -> {
            try {
                Course course = courseMapper.selectByPrimaryKeyForUpdate(courseId);
                Assert.notNull(course, "课程不存在");

                if (course.getAvailableNum() <= 0) {
                    return Result.err("课余量为0, 不能选择", null);
                }

                if (course.getDeadline().before(new Date(System.currentTimeMillis()))) {
                    return Result.err("该课程报名已经截止", null);
                }

                if (!StringUtils.isEmpty(course.getGradeLimit())) {
                    if (!course.getGradeLimit().contains(student.getGrade())) {
                        return Result.err("当前年级不允许报名此课", null);
                    }
                }

                PoorLevelEnum poorLevel = PoorLevelEnum.valueOf(student.getPoorLevel());
                if (poorLevel == PoorLevelEnum.NOT_POOR) {
                    if (course.getNotPoorNum() <= 0) {
                        return Result.err("非困难名额已满", null);
                    } else {
                        course.setNotPoorNum(course.getNotPoorNum() - 1);
                    }
                }

                course.setAvailableNum(course.getAvailableNum() - 1);

                if (course.getAvailableNum() < course.getNotPoorNum()) {
                    course.setNotPoorNum(course.getAvailableNum());
                }

                Assert.isTrue(course.getNotPoorNum() >= 0, "课余量不足");
                Assert.isTrue(course.getAvailableNum() >= 0, "课余量不足");

                courseMapper.updateByPrimaryKeySelective(course);

                Select select = new Select();
                select.setCourseId(courseId);
                select.setStudentId(student.getId());

                selectMapper.insert(select);

                return Result.ok(null);
            } catch (IllegalArgumentException e) {
                LOGGER.error("selectCourse", e);
                t.setRollbackOnly();
                return Result.err(e.getMessage(), null);
            } catch (DuplicateKeyException e) {
                LOGGER.error("selectCourse", e);
                t.setRollbackOnly();
                return Result.err("选课失败, 重复选课", null);
            } catch (Exception e) {
                LOGGER.error("selectCourse", e);
                t.setRollbackOnly();
                return Result.err("选课失败, 重复选课或课余量不足", null);
            }
        });
    }

    @Override
    public Result deselectCourse(Student student, Long courseId) {
        SelectCondition selectCondition = new SelectCondition();
        selectCondition.setStudentId(student.getId());
        selectCondition.setCourseId(courseId);
        List<Select> selectList = selectMapper.selectByCondition(selectCondition);
        Assert.notEmpty(selectList, "退选失败, 未选择该课程");

        return transactionTemplate.execute((t) -> {
            try {
                PoorLevelEnum poorLevel = PoorLevelEnum.valueOf(student.getPoorLevel());
                Course course = courseMapper.selectByPrimaryKeyForUpdate(courseId);
                Assert.notNull(course, "课程不存在");
                if (poorLevel == PoorLevelEnum.NOT_POOR) {
                    course.setNotPoorNum(course.getNotPoorNum() + 1);
                }
                course.setAvailableNum(course.getAvailableNum() + 1);
                courseMapper.updateByPrimaryKeySelective(course);
                selectMapper.deleteByPrimaryKey(selectList.get(0).getId());
                return Result.ok(null);
            } catch (Exception e) {
                LOGGER.error("deselectCourse", e);
                t.setRollbackOnly();
                return Result.err("退选失败, 请稍候", null);
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public Result<List<Course>> getCourseList() {
        List<Course> courseList = courseMapper.selectByCondition(new CourseCondition());
        return Result.ok(courseList);
    }
}
