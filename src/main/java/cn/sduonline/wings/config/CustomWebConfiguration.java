package cn.sduonline.wings.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.sduonline.wings.interceptor.StudentInfoInterceptor;

/**
 * Created by imaxct on 18-10-2.
 */
@Configuration
public class CustomWebConfiguration implements WebMvcConfigurer {
    private final StudentInfoInterceptor studentInfoInterceptor;

    @Autowired
    public CustomWebConfiguration(StudentInfoInterceptor studentInfoInterceptor) {
        this.studentInfoInterceptor = studentInfoInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(studentInfoInterceptor).addPathPatterns("/Student/**")
            .excludePathPatterns("/Student/fill");
    }
}
