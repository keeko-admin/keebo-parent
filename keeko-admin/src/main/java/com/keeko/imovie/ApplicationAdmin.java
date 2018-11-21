package com.keeko.imovie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.keeko.imovie.mapper", "com.keeko.imovie.config"}) //扫描包
public class ApplicationAdmin {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationAdmin.class, args);
    }

}
