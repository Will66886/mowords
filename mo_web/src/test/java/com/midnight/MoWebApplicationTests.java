package com.midnight;

import com.midnight.service.MoMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;



@SpringBootTest
class MoWebApplicationTests {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    MoMenuService moMenuService;
    @Test
    void contextLoads(){
        
    }
}
