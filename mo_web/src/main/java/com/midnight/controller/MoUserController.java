package com.midnight.controller;

import com.midnight.model.MoUser;
import com.midnight.service.MoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MoUserController {
    @Autowired
    MoUserService moUserService;
//    @GetMapping("/queryUserList")
//    @ResponseBody
//    public List<MoUser> getUser(){
//        List<MoUser> users = moUserService.getUser();
//        for (MoUser user : users) {
//            System.out.println(user);
//        }
//        return users;
//    }
}
