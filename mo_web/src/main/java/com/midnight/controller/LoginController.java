package com.midnight.controller;


import com.midnight.model.MoUser;
import com.midnight.service.MoUserService;
import com.midnight.service.UserTokenManager;
import com.midnight.util.JacksonUtil;
import com.midnight.util.ResponseUtil;
import com.midnight.util.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
//@CrossOrigin
public class LoginController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    MoUserService moUserService;
    @PostMapping("/login")
    public Object login(@RequestBody String body, HttpServletRequest request){
        System.out.println("12312");
        var username = JacksonUtil.parseString(body, "username");
        var password = JacksonUtil.parseString(body, "password");

        if (StringUtils.isEmpty(username) && StringUtils.isEmpty(password)){
            return ResponseUtil.badArgument();
        }
        List<MoUser> userList = moUserService.queryByName(username);
        MoUser user = null;
        if (userList.size() > 1){
            return ResponseUtil.serious();
        }else if (userList.size() == 0){
            return ResponseUtil.fail(700,"账号不存在");
        }else {
            user = userList.get(0);
        }
        var encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPwd())){
            return ResponseUtil.fail(700,"密码不正确");
        }

        
        var token = UserTokenManager.generateToken(user.getId());
//        redisTemplate.opsForValue().set("token",token);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("user",user);
        return ResponseUtil.ok(map);
    }

    public static void main(String[] args) {
        var encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        boolean matches = encoder.matches(encode, "123456");
        System.out.println(encode);
        System.out.println(matches);
    }
}
