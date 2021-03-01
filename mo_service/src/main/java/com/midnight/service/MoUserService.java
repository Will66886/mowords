package com.midnight.service;

import com.midnight.mapper.MoUserMapper;
import com.midnight.model.MoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoUserService {
    @Autowired
    MoUserMapper moUserMapper;
    public List<MoUser> queryByName(String username){
        return moUserMapper.queryByName(username);
    }
}
