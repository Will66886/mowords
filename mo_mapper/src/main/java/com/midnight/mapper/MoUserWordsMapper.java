package com.midnight.mapper;

import com.midnight.model.MoUserWords;

import java.util.List;

public interface MoUserWordsMapper {

    int addUserWords(List<MoUserWords> list);
    
    int updateUserWords(List<MoUserWords> list);
}
