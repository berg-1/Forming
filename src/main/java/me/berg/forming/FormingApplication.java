package me.berg.forming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("me.berg.forming.mapper")
public class FormingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormingApplication.class, args);
    }

}
