package cn.sduonline.wings.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.sduonline.wings.constant.RoleName;
import cn.sduonline.wings.model.Course;
import cn.sduonline.wings.service.CourseService;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-10-3.
 */
@RestController
@RequestMapping("/Admin")
@RequiresRoles(RoleName.ROLE_ADMIN)
public class AdminCourseController {

    private final CourseService courseService;

    @Autowired
    public AdminCourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courseList")
    public Result getCourseList() {
        return courseService.getCourseList();
    }

    @PostMapping("/updateCourse")
    public Result updateCourse(@RequestBody Course course) {
        return courseService.updateCourse(course);
    }

    @PutMapping("/createCourse")
    public Result createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @DeleteMapping("/deleteCourse")
    public Result deleteCourse(@RequestBody Course course) {
        return courseService.deleteCourse(course);
    }
}
