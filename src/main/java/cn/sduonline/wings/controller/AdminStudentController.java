package cn.sduonline.wings.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.sduonline.wings.constant.RoleName;
import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.AdminService;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-10-3.
 */
@RestController
@RequestMapping("/Admin")
public class AdminStudentController {
    private final AdminService adminService;

    @Autowired
    public AdminStudentController(AdminService adminService) {
        this.adminService = adminService;
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
}
