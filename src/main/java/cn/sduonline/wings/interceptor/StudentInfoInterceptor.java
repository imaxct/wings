package cn.sduonline.wings.interceptor;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.sduonline.wings.exception.AuthException;
import cn.sduonline.wings.exception.InfoIncompleteException;
import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.StudentService;

/**
 * Created by imaxct on 18-10-2.
 */
@Component
public class StudentInfoInterceptor implements HandlerInterceptor {
    private final StudentService studentService;

    @Autowired
    public StudentInfoInterceptor(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (null == SecurityUtils.getSubject()) {
            throw new AuthException("登录已过期或未登录");
        }
        if (null == SecurityUtils.getSubject().getPrincipals()
            || SecurityUtils.getSubject().getPrincipals().isEmpty()) {
            throw new AuthException("登录已过期或未登录");
        }
        String username = (String)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Student student = studentService.getStudentByNo(username);
        if (student == null) {
            throw new AuthException("登录已过期或未登录");
        }
        BeanInfo info = Introspector.getBeanInfo(Student.class);
        for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
            if (null == descriptor.getReadMethod().invoke(student)) {
                throw new InfoIncompleteException("信息不全, 请先补全信息");
            }
        }
        return true;
    }
}
