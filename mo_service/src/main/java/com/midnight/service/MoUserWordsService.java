package com.midnight.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.midnight.mapper.MoUserWordsMapper;
import com.midnight.mapper.MoWordsMapper;
import com.midnight.memory.Ebbinghaus;
import com.midnight.memory.MnemonicsFactory;
import com.midnight.model.MoUserWords;
import com.midnight.model.MoWords;
import com.midnight.model.RecitingWord;
import com.midnight.util.JacksonUtil;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RabbitListener(queuesToDeclare = @Queue(value = "updateUserWord"))
public class MoUserWordsService {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    MoUserWordsMapper moUserWordsMapper;
    @Autowired
    MoWordsMapper moWordsMapper;

    /**
     * 添加学习单词
     * @param userId
     * @param list
     */
    public void addWord(Integer userId,LinkedList<RecitingWord> newList){
        /*
        将新添加的单词保存至数据库中
        获取redis中的list，如果list为空则新建list插入，不为空则插入list尾部
         */
        Runnable task = () -> {
            ArrayList<MoUserWords> userWordsList = new ArrayList<>(newList.size());
            for (RecitingWord recitingWord : newList) {
                userWordsList.add(new MoUserWords(null,userId,recitingWord.getWordId(),0,0, LocalDate.now(),0));
            }
            moUserWordsMapper.addUserWords(userWordsList);
        };
        new Thread(task).start();


        LinkedList<RecitingWord> list = new LinkedList<>();
        Map<Object, Object> userWordsMap = redisTemplate.boundHashOps("moUserWords").entries();
        if (userWordsMap != null && userWordsMap.size() != 0){
            String json = userWordsMap.get(userId.toString()).toString();
            List<RecitingWord> oldList = JacksonUtil.toObject(json, new TypeReference<List<RecitingWord>>(){});
            list.addAll(oldList);
        }
        list.addAll(newList);
        List<RecitingWord> distinctList = list.stream().distinct().collect(Collectors.toList());
        String json = JacksonUtil.toJson(distinctList);
        redisTemplate.boundHashOps("moUserWords").put(userId.toString(),json);

    }

    /**
     * 获取学习单词
     * @param userId
     * @return
     */
    public MoWords getWord(Integer userId){
        Map<Object, Object> userMap = redisTemplate.boundHashOps("moUserWords").entries();
        if (userMap==null) return null;

        String json = (String)userMap.get(userId);
        if (json==null || json.length() <= 2) return null;//如果list为空，json字符串为[]
        
        List<RecitingWord> list = JacksonUtil.toList(json);
        RecitingWord recitingWord = list.get(0);
        Integer wordId = recitingWord.getWordId();
        return moWordsMapper.getMoWordById(wordId);
    }

    /**
     * 学习单词
     * @param userId
     * @param wordId
     * @param status
     * @return
     */
    public RecitingWord learnWord(Integer userId,Integer wordId,Integer status){
        Map<Object, Object> userMap = redisTemplate.boundHashOps("moUserWords").entries();
        if (userMap==null) return null;
        String json = (String)userMap.get(userId);
        if (json==null || json.length() <= 2) return null;
        LinkedList<RecitingWord> list = new LinkedList<>(JacksonUtil.toList(json));

        if(wordId == null && status == null){
            /*
            开始单词学习
            将数据库中今日单词加载到redis中
             */
            return list.get(0);
        }

        MnemonicsFactory mnemonics = new Ebbinghaus();//艾宾浩斯算法
        List<RecitingWord> newList = mnemonics.learnWord(userId, wordId, status, list);
        //如果未学单词已空，则将全部已背诵单词通过rabbitmq保存至数据库中
        if (newList.size()==0){
            Map<Object, Object> learnedWords = redisTemplate.boundHashOps("learnedWords").entries();
            List<RecitingWord> learnedList = (List) learnedWords.get("userId");
//            moUserWordConsumer.updateUserWord(learnedList);
            return null;
        }
        String newJson = JacksonUtil.toJson(newList);
        redisTemplate.boundHashOps("moUserWords").put(userId.toString(),newJson);
        return newList.get(0);
    }

    public int updateUserWords(ArrayList<MoUserWords> list){
        return moUserWordsMapper.updateUserWords(list);
    }
}
