package com.midnight.service;

import com.midnight.mapper.MoWordsMapper;
import com.midnight.model.MoWords;
import com.midnight.translation.YouDaoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoWordsService {
    @Autowired
    MoWordsMapper moWordsMapper;
    public void addWord(String word){
        MoWords moWord = moWordsMapper.getMoWord(word);
        if (moWord == null){
            String translation = YouDaoAPI.getTranslation(word);
            moWord = new MoWords();
            moWord.setTranslation(translation);
            moWord.setWord(word);
        }
        moWordsMapper.addMoWords(List.of(moWord));
    }
}
