package me.berg.forming;

import me.berg.forming.component.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FormingApplicationTests {

    @Autowired
    RedisService redisService;

    @Test
    void contextLoads() {
        String get = redisService.get("RedisTest");
        System.out.println(get);
    }

}
