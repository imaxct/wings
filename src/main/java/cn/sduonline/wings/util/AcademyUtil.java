package cn.sduonline.wings.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import cn.sduonline.wings.model.PoorLevelEnum;
import cn.sduonline.wings.model.Student;

/**
 * Created by imaxct on 18-10-1.
 */
public class AcademyUtil {

    // 教务登录入口关闭
    // 换用新的登录验证方式
//    private static final String URL_LOGIN = "http://bkjws.sdu.edu.cn/b/ajaxLogin";
    private static final String URL_LOGIN = "http://202.194.15.137:8082/api/auth/login/system";
    private static final String URL_EDU_LOGIN = "http://202.194.15.137:8082/api/auth/login/edu";
    private static final String URL_INFO = "http://bkjws.sdu.edu.cn/b/grxx/xs/xjxx/detail";
//    private static final String PARAM_NAME = "j_username";
//    private static final String PARAM_PASS = "j_password";
    private static final String PARAM_NAME = "u";
    private static final String PARAM_PASS = "p";
    private static final Logger LOG = LoggerFactory.getLogger(AcademyUtil.class);
    private ThreadLocal<Map<String, String>> cookies = new ThreadLocal<>();

    private void login(String username, String password) {
        Connection.Response response;
        try {
            response = Jsoup.connect(URL_LOGIN).data(PARAM_NAME, username).data(PARAM_PASS, password)
//                .data(PARAM_PASS, DigestUtils.md5DigestAsHex(password.getBytes()))
                .method(Connection.Method.POST)
                .ignoreHttpErrors(true).ignoreContentType(true).timeout(0).execute();
        } catch (IOException e) {
            LOG.error("crawler", e);
            throw new IllegalArgumentException("统一身份认证失败");
        }
        Assert.notNull(response, "访问统一身份认证失败");
        if (response.statusCode() == 200) {
            String respBody = response.body();
//            if (respBody.contains("JSESSIONID")) {
//                Map<String, String> cookie = new HashMap<>();
//
//                String respStr = response.body().replace("\n", "");
//                cookie.put("JSESSIONID", respStr.substring(respStr.indexOf("=") + 1));
//                cookies.set(cookie);
//                return;
//            }
//            if (respBody.contains("success")) {
//                cookies.set(response.cookies());
//                return;
//            }
            JSONObject jsonObject = JSON.parseObject(respBody);
            if (jsonObject.getInteger("code") == 0) {
                try {
                    Connection.Response eduResp = Jsoup.connect(URL_EDU_LOGIN).data(PARAM_NAME, username)
                            .data(PARAM_PASS, password).method(Connection.Method.POST)
                            .ignoreContentType(true).ignoreHttpErrors(true).timeout(0).execute();

                    Map<String, String> cookie = new HashMap<>();
                    String respStr = eduResp.body().replace("\n", "");
                    cookie.put("JSESSIONID", respStr.substring(respStr.indexOf("=") + 1));
                    cookies.set(cookie);
                    return;
                } catch (IOException e) {
                    LOG.error("crawler", e);
                    throw new IllegalArgumentException("统一身份认证失败");
                }
            } else {
                throw new IllegalArgumentException(jsonObject.getString("message"));
            }
        }
        throw new IllegalArgumentException("统一身份认证失败");
    }

    public Student getStudent(String username, String password) {
        login(username, password);
        Connection.Response response;
        try {
            response = Jsoup.connect(URL_INFO).method(Connection.Method.POST).timeout(0).ignoreContentType(true)
                .cookies(cookies.get()).execute();

        } catch (IOException e) {
            LOG.error("getInfo", e);
            return null;
        }

        cookies.remove();

        if (response.statusCode() == 200) {

            net.sf.json.JSONObject object = net.sf.json.JSONObject.fromObject(response.body());
            if ("success".equals(object.getString("result"))) {
                object = object.getJSONObject("object");
                Student student = new Student();
                student.setStudentNo(object.getString("xh"));
                student.setAcademy(object.getString("xsjc"));
                student.setClassName(object.getString("bm"));
                student.setIdNo(object.getString("sfzh"));
                student.setNation(object.getString("mz"));
                student.setMajor(object.getString("zym"));
                student.setSex(object.getString("xb"));
                student.setGrade(object.getString("ssnj"));
                student.setStudentName(object.getString("xm"));
                student.setPoorLevel(PoorLevelEnum.NOT_POOR.name());
                return student;
            }
        }
        return null;
    }
}
