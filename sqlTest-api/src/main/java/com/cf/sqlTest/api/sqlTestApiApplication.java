package com.cf.sqlTest.api;

import com.cf.sqlTest.services.service.TestZjfService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.cf"})
@MapperScan("com.cf.sqlTest.dao.mapper")
@SpringBootApplication
public class sqlTestApiApplication {

    @Qualifier()
    public static void main(String[] args) {
        SpringApplication.run(sqlTestApiApplication.class, args);
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();

        app.getBean(TestZjfService.class);
    }
}
