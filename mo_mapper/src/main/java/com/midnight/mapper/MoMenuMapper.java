package com.midnight.mapper;

import com.midnight.model.MoMenu;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MoMenuMapper {
    List<MoMenu> getAllMoMenus();
}
