package cn.sduonline.wings.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.StudentService;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-10-2.
 */
@RestController
@RequestMapping("/Common")
@SuppressWarnings("unchecked")
public class CommonStudentController {
    private final StudentService studentService;

    @Autowired
    public CommonStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/announce")
    public Result<String> getAnnouncement() {
        return studentService.getAnnouncement();
    }

    @PostMapping("/login")
    public Result<Student> login(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        return Result.ok(studentService.getStudentByNo(username));
    }

    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.ok(null);
    }
}
