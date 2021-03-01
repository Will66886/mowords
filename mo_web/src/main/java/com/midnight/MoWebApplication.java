package com.midnight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.midnight.mapper")
public class MoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoWebApplication.class, args);
    }

}
