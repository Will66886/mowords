package com.midnight.controller.config;

import com.midnight.model.MoMenu;
import com.midnight.service.MoMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    @Autowired
    MoMenuService moMenuService;
    @GetMapping("/menu")
    public List<MoMenu> getMenus(){
        List<MoMenu> allMenus = moMenuService.getAllMenus();
        
        return allMenus;
    }
}
