package me.berg.forming;

import me.berg.forming.component.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class FormingApplicationTests {

    @Autowired
    RedisService redisService;

    @Test
    void contextLoads() {
        String get = redisService.get("RedisTest");
        System.out.println(get);
    }

    @Test
    void bcrypt() {
        String password = BCrypt.hashpw("123456", BCrypt.gensalt());
        System.out.println(password);
    }

}
