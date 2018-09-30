package cn.sduonline.wings.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

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
import cn.sduonline.wings.util.SettingName;
import cn.sduonline.wings.vo.Result;
import cn.sduonline.wings.vo.SelectionVO;

/**
 * Created by imaxct on 18-9-27.
 */
@Service
public class StudentServiceImpl implements StudentService {
	private final StudentMapper studentMapper;
	private final SettingMapper settingMapper;
	private final SelectMapper selectMapper;
	private final CourseMapper courseMapper;

	private final TransactionTemplate transactionTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

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
	public Result login(String username, String password) {
		return null;
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
		return null;
	}

	@Override
	public Student saveStudent(Student student) {
		studentMapper.insert(student);
		return student;
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
			return Result.ok(setting);
		}
		return Result.ok(list.get(0));
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
	public Result selectCourse(Long studentId, Long courseId) {
		Student student = studentMapper.selectByPrimaryKey(studentId);

		SettingCondition settingCondition = new SettingCondition();
		settingCondition.setSettingName(SettingName.SELECT_LIMIT);
		List<Setting> settings = settingMapper.selectByCondition(settingCondition);
		if (!CollectionUtils.isEmpty(settings)) {
			Setting setting = settings.get(0);
			int selectLimit = Integer.parseInt(setting.getSettingValue());
			SelectCondition selectCondition = new SelectCondition();
			selectCondition.setStudentId(studentId);
			int selected = selectMapper.countByCondition(selectCondition);
			if (selected >= selectLimit) {
				return Result.err("当前选课数量已经超过本期选课数量限制", null);
			}
		}

		transactionTemplate.execute((t) -> {
			try {
				Course course = courseMapper.selectByPrimaryKeyForUpdate(courseId);

				if (course.getAvailableNum() <= 0) {
					return Result.err("课余量为0, 不能选择", null);
				}

				PoorLevelEnum poorLevel = PoorLevelEnum.valueOf(student.getPoorLevel());
				if (poorLevel == PoorLevelEnum.NOT_POOR) {
					if (course.getNotPoorNum() <= 0) {
						return Result.err("非困难名额已满", null);
					}
				} else {
					course.setNotPoorNum(course.getNotPoorNum() - 1);
				}

				course.setAvailableNum(course.getAvailableNum() - 1);

				courseMapper.updateByPrimaryKeySelective(course);

				Select select = new Select();
				select.setCourseId(courseId);
				select.setStudentId(studentId);

				selectMapper.insert(select);

				return Result.ok(null);
			} catch (Exception e) {
				LOGGER.error("selectCourse", e);
				t.setRollbackOnly();
				return Result.err("选课失败, 重复选课或课余量不足", null);
			}
		});
		return Result.err("选课失败", null);
	}

	@Override
	public Result deselectCourse(Long studentId, Long courseId) {
		SelectCondition selectCondition = new SelectCondition();
		selectCondition.setStudentId(studentId);
		selectCondition.setCourseId(courseId);
		List<Select> selectList = selectMapper.selectByCondition(selectCondition);
		if (CollectionUtils.isEmpty(selectList)) {
			return Result.err("退选失败, 未选择该课程", null);
		}
		Student student = studentMapper.selectByPrimaryKey(studentId);
		transactionTemplate.execute((t) -> {
			try {
				PoorLevelEnum poorLevel = PoorLevelEnum.valueOf(student.getPoorLevel());
				Course course = courseMapper.selectByPrimaryKeyForUpdate(courseId);
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
		return Result.err("退选失败", null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Result<List<Course>> getCourseList() {
		List<Course> courseList = courseMapper.selectByCondition(new CourseCondition());
		return Result.ok(courseList);
	}
}
