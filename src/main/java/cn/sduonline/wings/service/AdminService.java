package cn.sduonline.wings.service;

import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.vo.Result;

import java.util.List;

/**
 * Created by imaxct on 18-9-26.
 */
public interface AdminService {
    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    Result login(String username, String password);


    /**
     * 登出
     *
     * @return
     */
    Result logout();

    /**
     * 导入学生信息
     *
     * @param students
     * @return
     */
    Result importStudent(List<Student> students);

    /**
     * 分页获取学生列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result getStudent(int pageNum, int pageSize);


}
