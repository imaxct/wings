package cn.sduonline.wings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sduonline.wings.dao.mapper.CourseMapper;
import cn.sduonline.wings.model.Course;
import cn.sduonline.wings.model.condition.CourseCondition;
import cn.sduonline.wings.service.CourseService;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-9-30.
 */
@Service
@SuppressWarnings("unchecked")
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public Result<List<Course>> getCourseList() {
        List<Course> courseList = courseMapper.selectByCondition(new CourseCondition());
        return Result.ok(courseList);
    }

    @Override
    public Result<Course> updateCourse(Course course) {
        Course dbCourse = courseMapper.selectByPrimaryKey(course.getId());
        // 已经开始选课
        // 当前已选数量 = dbCourse.getTotalNum() - dbCourse.getAvailableNum()
        if (dbCourse.getTotalNum() - dbCourse.getAvailableNum() > course.getTotalNum()) {
            throw new IllegalArgumentException("总课余量不能小于已选数量");
        }
        course.setAvailableNum(course.getTotalNum() - dbCourse.getTotalNum() + dbCourse.getAvailableNum());
        int num = courseMapper.updateByPrimaryKeyWithBLOBs(course);
        if (num > 0) {
            return Result.ok(num);
        } else {
            return Result.err("更新失败", num);
        }
    }

    @Override
    public Result<Course> createCourse(Course course) {
        int num = courseMapper.insert(course);
        if (num > 0) {
            return Result.ok(num);
        } else {
            return Result.err("创建失败", num);
        }
    }

    @Override
    public Result deleteCourse(Course course) {
        int num = courseMapper.deleteByPrimaryKey(course.getId());
        if (num > 0) {
            return Result.ok(num);
        } else {
            return Result.err("删除失败", num);
        }
    }
}
