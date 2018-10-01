package cn.sduonline.wings.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import cn.sduonline.wings.constant.RoleName;
import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.StudentService;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-10-1.
 */
@RestController
@RequestMapping("/Student")
public class StudentController {

	private final StudentService studentService;

	@PostMapping("/login")
	public Result login(@RequestParam String username, @RequestParam String password) {
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

	@GetMapping("/selected")
	@RequiresRoles(RoleName.ROLE_STUDENT)
	public Result getSelectedCourse() {
		String studentNo = (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		Student student = studentService.getStudentByNo(studentNo);
		Assert.notNull(student, "学生信息不存在");
		return studentService.getSelectedCourse(student.getId());
	}

	@GetMapping("/announce")
	public Result getAnnouncement() {
		return studentService.getAnnouncement();
	}

	@PutMapping("/select")
	@RequiresRoles(RoleName.ROLE_STUDENT)
	public Result select(@RequestParam Long courseId) {
		String studentNo = (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		Student student = studentService.getStudentByNo(studentNo);
		Assert.notNull(student, "学生信息不存在");
		return studentService.selectCourse(student, courseId);
	}

	@DeleteMapping("/deselect")
	@RequiresRoles(RoleName.ROLE_STUDENT)
	public Result deselect(@RequestParam Long courseId) {
		String studentNo = (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		Student student = studentService.getStudentByNo(studentNo);
		Assert.notNull(student, "学生信息不存在");
		return studentService.deselectCourse(student, courseId);
	}

	@GetMapping("/listCourse")
	@RequiresRoles(RoleName.ROLE_STUDENT)
	public Result getCourseList() {
		return studentService.getCourseList();
	}

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
}
