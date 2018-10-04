package cn.sduonline.wings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.sduonline.wings.dao.mapper.AdminMapper;
import cn.sduonline.wings.dao.mapper.StudentMapper;
import cn.sduonline.wings.exception.ServiceException;
import cn.sduonline.wings.model.Admin;
import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.model.condition.AdminCondition;
import cn.sduonline.wings.service.AdminService;
import cn.sduonline.wings.vo.Result;

/**
 * @author imaxct
 * @date 18-9-26
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final StudentMapper studentMapper;

    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(StudentMapper studentMapper, AdminMapper adminMapper) {
        this.studentMapper = studentMapper;
        this.adminMapper = adminMapper;
    }

    @Override
    public Result login(String username, String password) {
        AdminCondition condition = new AdminCondition();
        condition.setUsername(username);
        List<Admin> list = adminMapper.selectByCondition(condition);
        if (CollectionUtils.isEmpty(list) || list.size() != 1) {
            throw new ServiceException("用户不存在");
        } else {
            Admin admin = list.get(0);
            if (!admin.getPassword().equals(password)) {
                return Result.err("密码错误", null);
            } else {
                return Result.ok(admin);
            }
        }
    }

    @Override
    public Admin getAdminByUsername(String username) {
        AdminCondition condition = new AdminCondition();
        condition.setUsername(username);
        List<Admin> list = adminMapper.selectByCondition(condition);
        if (CollectionUtils.isEmpty(list) || list.size() != 1) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Result logout() {
        return null;
    }

    @Override
    public Result importStudent(List<Student> students) {
        int total = 0;
        int cnt = students.size() / 50;
        int start, end;
        for (int i = 0; i <= cnt; ++i) {
            start = i * 50;
            end = start + 49;
            end = end > students.size() ? students.size() : end;
            total += studentMapper.insertBatch(students.subList(start, end));
        }
        return Result.ok(String.format("导入:%d/%d条", total, students.size()));
    }

    @Override
    public Result getStudent(int pageNum, int pageSize) {
        Page<Student> studentPage = PageHelper.startPage(pageNum, pageSize).doSelectPage(studentMapper::selectAll);
        return Result.ok(studentPage);
    }

    @Override
    public Result updateStudent(Student student) {
        int num = studentMapper.updateByPrimaryKeySelective(student);
        if (num > 0) {
            return Result.ok(num);
        } else {
            return Result.err("保存失败", num);
        }
    }
}
