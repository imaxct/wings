package cn.sduonline.wings.service.impl;

import cn.sduonline.wings.dao.mapper.SettingMapper;
import cn.sduonline.wings.dao.mapper.StudentMapper;
import cn.sduonline.wings.model.*;
import cn.sduonline.wings.service.StudentService;
import cn.sduonline.wings.util.SettingName;
import cn.sduonline.wings.vo.Result;
import cn.sduonline.wings.vo.SelectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by imaxct on 18-9-27.
 */
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final SettingMapper settingMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, SettingMapper settingMapper) {
        this.studentMapper = studentMapper;
        this.settingMapper = settingMapper;
    }

    @Override
    public Result login(String username, String password) {
        return null;
    }

    @Override
    public Student getStudentByNo(String stuNo) {
        StudentExample example = new StudentExample();
        example.createCriteria().andStudentNoEqualTo(stuNo);
        List<Student> list = studentMapper.selectByExample(example);
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
        SettingExample example = new SettingExample();
        example.createCriteria().andSettingNameEqualTo(SettingName.ANNOUNCEMENT);
        List<Setting> list = settingMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list) || list.size() != 1) {
            Setting setting = new Setting();
            setting.setSettingName(SettingName.ANNOUNCEMENT);
            setting.setSettingValue("无公告");
            settingMapper.insert(setting);
            return Result.ok(setting);
        }
        return Result.ok(list.get(0));
    }

    @Override
    public Result<List<SelectionVO>> getSelectedCourse(Long studentId) {
        SelectExample example = new SelectExample();
        example.createCriteria().andStudentIdEqualTo(studentId);
        return null;
    }

    @Override
    public Result selectCourse(Long studentId, Long courseId) {
        return null;
    }

    @Override
    public Result deselectCourse(Long studentId, Long courseId) {
        return null;
    }

    @Override
    public Result<List<Course>> getCourseList() {
        return null;
    }
}
