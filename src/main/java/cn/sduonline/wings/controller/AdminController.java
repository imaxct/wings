package cn.sduonline.wings.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.AdminService;
import cn.sduonline.wings.service.SelectionService;
import cn.sduonline.wings.util.RoleName;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-9-26.
 */
@RestController
@RequestMapping("/Admin")
public class AdminController {

	private final AdminService adminService;

	private final SelectionService selectionService;

	@Autowired
	public AdminController(AdminService adminService, SelectionService selectionService) {
		this.adminService = adminService;
		this.selectionService = selectionService;
	}

	@PutMapping("/import")
	@RequiresRoles(RoleName.ROLE_ADMIN)
	public Result importStudent(@RequestBody List<Student> students) {
		return adminService.importStudent(students);
	}

	@GetMapping("/listStudent")
	@RequiresRoles(RoleName.ROLE_ADMIN)
	public Result getStudent(@RequestParam int pageNum, @RequestParam int pageSize) {
		return adminService.getStudent(pageNum, pageSize);
	}

	@GetMapping("/exportSelect")
	@RequiresRoles(RoleName.ROLE_ADMIN)
	public Result exportCourseSelection(@RequestParam Long courseId) {
		return selectionService.exportSelection(courseId);
	}

	@GetMapping("/exportAll")
	@RequiresRoles(RoleName.ROLE_ADMIN)
	public Result exportAllSelection() {
		return selectionService.exportAllSelection();
	}

	@PostMapping("/login")
	public Result login(@RequestParam String username, @RequestParam String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.login(token);
		return Result.ok(adminService.getAdminByUsername(username));
	}

	@GetMapping("/logout")
	public Result logout() {
		SecurityUtils.getSubject().logout();
		return Result.ok(null);
	}
}
