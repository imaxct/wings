package cn.sduonline.wings.controller;

import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.AdminService;
import cn.sduonline.wings.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by imaxct on 18-9-26.
 */
@RestController
@RequestMapping("/Admin")
public class AdminController {

    private final AdminService adminService;


    @PutMapping("/import")
    public Result importStudent(@RequestBody List<Student> students) {
        return adminService.importStudent(students);
    }

    @GetMapping("/list")
    public Result getStudent(@RequestParam int pageNum, @RequestParam int pageSize) {
        return adminService.getStudent(pageNum, pageSize);
    }

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
}
