package cn.sduonline.wings;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.sduonline.wings.dao.mapper")
public class WingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WingsApplication.class, args);
    }
}
