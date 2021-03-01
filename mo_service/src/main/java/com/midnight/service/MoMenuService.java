package com.midnight.service;

import com.midnight.mapper.MoMenuMapper;
import com.midnight.model.MoMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoMenuService {
    @Autowired
    MoMenuMapper moMenuMapper;
    public List<MoMenu> getAllMenus(){
        return moMenuMapper.getAllMoMenus();
    }
}
