package com.midnight.controller;

import com.midnight.service.MoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class MoUserController {
    @Autowired
    MoUserService moUserService;
}
