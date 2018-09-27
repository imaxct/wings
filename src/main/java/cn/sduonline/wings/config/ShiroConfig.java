package cn.sduonline.wings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by imaxct on 18-9-27.
 */
@Configuration
public class ShiroConfig {

    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }
}
