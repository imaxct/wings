package cn.sduonline.wings.service;

import cn.sduonline.wings.model.Course;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-9-26.
 */
public interface CourseService {

    /**
     * 获取课程列表
     *
     * @return
     */
    Result<Course> getCourseList();


    /**
     * 更新课程
     *
     * @param course
     * @return
     */
    Result<Course> updateCourse(Course course);

    /**
     * 创建课程
     *
     * @param course
     * @return
     */
    Result<Course> createCourse(Course course);

    /**
     * 删除课程
     *
     * @param course
     * @return
     */
    Result deleteCourse(Course course);

}
