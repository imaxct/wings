package cn.sduonline.wings.config;

import cn.sduonline.wings.model.Admin;
import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.AdminService;
import cn.sduonline.wings.service.StudentService;
import cn.sduonline.wings.util.RoleName;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by imaxct on 18-9-27.
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();

        if (!CollectionUtils.isEmpty(principals.fromRealm(RoleName.ROLE_ADMIN))) {
            roles.add(RoleName.ROLE_ADMIN);
        } else if (!CollectionUtils.isEmpty(principals.fromRealm(RoleName.ROLE_STUDENT))) {
            roles.add(RoleName.ROLE_STUDENT);
        }
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        Admin admin = adminService.getAdminByUsername(username);
        if (admin == null) {
            Student student = studentService.getStudentByNo(username);
            if (student == null) {
                student = studentService.getStudentByCrawler(username, new String(usernamePasswordToken.getPassword()));
                if (student == null) {
                    throw new AuthenticationException("登录失败, 用户不存在");
                } else {
                    studentService.saveStudent(student);
                    return new SimpleAuthenticationInfo(username, new String(usernamePasswordToken.getPassword()), RoleName.ROLE_STUDENT);
                }
            } else {
                return new SimpleAuthenticationInfo(username, student.getIdNo(), RoleName.ROLE_STUDENT);
            }
        } else {
            return new SimpleAuthenticationInfo(username, admin.getPassword(), RoleName.ROLE_ADMIN);
        }
    }
}
