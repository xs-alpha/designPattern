package com.cf.sqlTest.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.cf"})
@MapperScan("com.cf.sqlTest.dao.mapper")
@SpringBootApplication
public class sqlTestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(sqlTestApiApplication.class, args);
    }
}
