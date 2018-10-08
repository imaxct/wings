package cn.sduonline.wings.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import cn.sduonline.wings.constant.RoleName;
import cn.sduonline.wings.model.Course;
import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.StudentService;
import cn.sduonline.wings.vo.Result;
import cn.sduonline.wings.vo.SelectionVO;

/**
 * Created by imaxct on 18-10-1.
 */
@RestController
@RequestMapping("/Student")
@SuppressWarnings("unchecked")
@RequiresRoles(RoleName.ROLE_STUDENT)
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/selected")
    public Result<List<SelectionVO>> getSelectedCourse() {
        String studentNo = (String)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Student student = studentService.getStudentByNo(studentNo);
        Assert.notNull(student, "学生信息不存在");
        return studentService.getSelectedCourse(student.getId());
    }

    @PutMapping("/select")
    public Result select(@RequestParam Long courseId) {
        String studentNo = (String)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Student student = studentService.getStudentByNo(studentNo);
        Assert.notNull(student, "学生信息不存在");
        return studentService.selectCourse(student, courseId);
    }

    @DeleteMapping("/deselect")
    public Result deselect(@RequestParam Long courseId) {
        String studentNo = (String)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Student student = studentService.getStudentByNo(studentNo);
        Assert.notNull(student, "学生信息不存在");
        return studentService.deselectCourse(student, courseId);
    }

    @GetMapping("/listCourse")
    public Result<List<Course>> getCourseList() {
        return studentService.getCourseList();
    }

    @PutMapping("/fill")
    public Result<Student> fillInfo(@RequestBody Student student) {
        Assert.notNull(student, "参数不能为空");
        Assert.notNull(student.getId(), "id不能为空");
        return Result.ok(studentService.saveStudent(student));
    }
}
