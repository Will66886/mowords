package com.midnight.mapper;

import com.midnight.model.MoWords;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoWordsMapper {
    List<MoWords> getMoWordsByPage(@Param("page") Integer page, @Param("size") Integer size,@Param(("word")) String word);

    MoWords getMoWord(String word);

    int addMoWords(List<MoWords> list);
    
}
