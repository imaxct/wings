package cn.sduonline.wings.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.sduonline.wings.constant.SettingName;
import cn.sduonline.wings.service.SettingService;

/**
 * Created by imaxct on 18-10-7.
 */
@Component
public class SiteOpenInterceptor implements HandlerInterceptor {
    private final SettingService settingService;

    @Autowired
    public SiteOpenInterceptor(SettingService settingService) {
        this.settingService = settingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        String siteOpen = settingService.getSettingByName(SettingName.SITE_OPEN);
        if (StringUtils.isEmpty(siteOpen)) {
            return true;
        }
        if ("true".equalsIgnoreCase(siteOpen)) {
            return true;
        }
        response.setCharacterEncoding("UTF-8");
        response.setStatus(404);
        response.setContentType("application/json");
        // {"ok":false,"msg":"站点关闭","data":null}
        response.getWriter().println("{\"ok\":false,\"msg\":\"站点关闭\",\"data\":null}");
        return false;
    }
}
