package com.midnight;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;


@SpringBootTest
class MoWebApplicationTests {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        String key1 = redisTemplate.opsForValue().get("key1");
        System.out.println(key1);

    }

}
