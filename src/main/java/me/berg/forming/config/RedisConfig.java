package me.berg.forming.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("spring.redis")
public class RedisConfig {
    private String host;
    private int port;
    private String password;
    private int database;

    @Bean
    RedisClient redisClient() {
        RedisURI client = RedisURI.Builder.redis(this.host, this.port)
                .withDatabase(this.database)
                .withPassword(password.toCharArray())
                .build();
        return RedisClient.create(client);
    }

}
