package cn.sduonline.wings.service.impl;

import cn.sduonline.wings.model.Course;
import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.StudentService;
import cn.sduonline.wings.vo.Result;
import cn.sduonline.wings.vo.SelectionVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by imaxct on 18-9-27.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public Result login(String username, String password) {
        return null;
    }

    @Override
    public Student getStudentByNo(String stuNo) {
        return null;
    }

    @Override
    public Student getStudentByCrawler(String stuNo, String password) {
        return null;
    }

    @Override
    public Student saveStudent(Student student) {
        return null;
    }

    @Override
    public Result getAnnouncement() {
        return null;
    }

    @Override
    public Result<List<SelectionVO>> getSelectedCourse(Long studentId) {
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
