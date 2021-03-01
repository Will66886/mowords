package com.midnight.mapper;

import com.midnight.model.MoUser;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MoUserMapper {
    List<MoUser> queryByName(String username);
}
