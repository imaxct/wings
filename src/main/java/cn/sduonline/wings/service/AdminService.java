package cn.sduonline.wings.service;

import java.util.List;

import cn.sduonline.wings.model.Admin;
import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.vo.Result;

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
     * 通过用户名获取用户
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);

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

    /**
     * 更新学生数据
     * 
     * @param student
     * @return
     */
    Result updateStudent(Student student);

}
