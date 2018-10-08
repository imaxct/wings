package cn.sduonline.wings.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
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
@SuppressWarnings("unchecked")
@RequiresRoles(RoleName.ROLE_ADMIN)
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
    public Result getStudent(@RequestParam int pageNum, @RequestParam int pageSize) {
        return adminService.getStudent(pageNum, pageSize);
    }

    @PostMapping("/updateStudent")
    public Result updateStudentInfo(@RequestBody Student student) {
        Assert.notNull(student, "参数不能为空");
        Assert.notNull(student.getId(), "id不能为空");
        return adminService.updateStudent(student);
    }
}
