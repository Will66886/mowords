package com.midnight.controller;

import com.midnight.annotation.LoginUser;
import com.midnight.model.RecitingWord;
import com.midnight.model.WordStatus;
import com.midnight.service.MoUserWordsService;
import com.midnight.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/moUserWords")
public class MoUserWordsController {
    @Autowired
    MoUserWordsService moUserWordsService;

    @PostMapping(value = "/addUserWords")
    public Object addUserWords(@LoginUser Integer userId,@RequestBody Integer[] wordIds){
        if (wordIds.length == 0){
            return ResponseUtil.fail(400,"未选择单词");
        }
        var list = new LinkedList<RecitingWord>();
        for (Integer wordId : wordIds) {
            list.push(new RecitingWord(wordId,0,0,WordStatus.UNLEARN));
        }
        moUserWordsService.addWord(userId,list);
        return ResponseUtil.ok();
    }
    @PostMapping(value = "/learnWord")
    public Object learnWord(@LoginUser Integer userId,Integer wordId,Integer status){
        if (wordId == null || status == null){
            return ResponseUtil.fail(400,"参数为空");
        }
        RecitingWord recitingWord = moUserWordsService.learnWord(userId, wordId, status);
        return ResponseUtil.ok(recitingWord);
    }
}
