package cn.sduonline.wings.service;

import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.vo.Result;
import cn.sduonline.wings.vo.SelectionVO;

import java.util.List;

/**
 * Created by imaxct on 18-9-26.
 */
public interface StudentService {

    /**
     * 学生登录
     *
     * @param username 学号
     * @param password 身份证号或者教务密码
     * @return
     */
    Result login(String username, String password);

    /**
     * 导入学生信息
     *
     * @param students
     * @return
     */
    Result importStudent(List<Student> students);


    /**
     * 获取公告
     *
     * @return
     */
    Result getAnnouncement();

    /**
     * 获取已选课程
     *
     * @return
     */
    Result<List<SelectionVO>> getSelectedCourse(Long studentId);
}
