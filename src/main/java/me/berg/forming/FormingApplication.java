package me.berg.forming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("me.berg.forming.mapper")
public class FormingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormingApplication.class, args);
    }

}
