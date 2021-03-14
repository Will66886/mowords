package com.midnight.memory;

import com.midnight.model.RecitingWord;
import com.midnight.model.WordStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ebbinghaus implements MnemonicsFactory{
    private final Log logger = LogFactory.getLog(Ebbinghaus.class);
    public ArrayList<RecitingWord> learnedList = new ArrayList<>();//已学习单词队列

    @Override
    public List<RecitingWord> learnWord(Integer userId, Integer wordId, Integer status, LinkedList<RecitingWord> list) {

        RecitingWord recitingWord = list.get(0);
        recitingWord.setLearnTimes(recitingWord.getLearnTimes()+1);
        
        switch (status){
            case 1:
                list = choiceKnow(recitingWord, list);
                break;
            case 2:
                list = choiceVague(recitingWord,list);
                break;
            case 3:
                list = choiceForget(recitingWord,list);
                break;
            default:
                logger.debug("400,status不存在"+status);
        }
        return list;
    }

    public LinkedList<RecitingWord> choiceKnow(RecitingWord recitingWord,LinkedList<RecitingWord> list) {
        int iterations = recitingWord.getIterations();
        WordStatus preStatus = recitingWord.getStatus();
        switch (preStatus) {
            case KNOW:
                //单词如果认识则直接移除背诵列表，放入已背诵列表
                if (iterations == 3) {
                    recitingWord.setStatus(WordStatus.KNOW);
                    learnedList.add(recitingWord);
                    list.remove(0);
                    break;
                }
                recitingWord.setIterations(iterations+1);
                list.add((iterations+1)*6,recitingWord);
                break;
            case UNLEARN:
                learnedList.add(recitingWord);
                list.remove(0);
                return list;
            case VAGUE:
                recitingWord.setStatus(WordStatus.VAGUE);
                recitingWord.setIterations(1);
                list.add(6,recitingWord);
                break;
            case FORGET:
                recitingWord.setStatus(WordStatus.FORGET);
                recitingWord.setIterations(1);
                list.add(6,recitingWord);
                break;
            default:
                logger.debug("500,WordStatus不存在");
                break;
        }
        return list;
    }
    public LinkedList<RecitingWord> choiceVague(RecitingWord recitingWord,LinkedList<RecitingWord> list) {
        int iterations = recitingWord.getIterations();
        WordStatus preStatus = recitingWord.getStatus();
        switch (preStatus) {
            case VAGUE:
                recitingWord.setIterations(iterations+1);
                list.add((iterations+1)*6,recitingWord);
                break;
            case FORGET:
            case UNLEARN:
                recitingWord.setStatus(WordStatus.VAGUE);
                recitingWord.setIterations(1);
                list.add(6,recitingWord);
                break;
            case KNOW:
                if (iterations == 3) {
                    learnedList.add(recitingWord);
                    list.remove(0);
                    break;
                }
                recitingWord.setIterations(iterations+1);
                list.add((iterations+1)*6,recitingWord);
                break;
            default:
                logger.debug("500,WordStatus不存在");
        }
        return list;
    }
    public LinkedList<RecitingWord> choiceForget(RecitingWord recitingWord,LinkedList<RecitingWord> list) {
        int iterations = recitingWord.getIterations();
        WordStatus preStatus = recitingWord.getStatus();
        switch (preStatus) {
            case VAGUE:
            case KNOW:
            case UNLEARN:
                recitingWord.setStatus(WordStatus.FORGET);
                recitingWord.setIterations(1);
                list.add(6,recitingWord);
                break;
            case FORGET:
                if (iterations == 3) {
                    learnedList.add(recitingWord);
                    list.remove(0);
                    break;
                }
                recitingWord.setIterations(iterations+1);
                list.add(1,recitingWord);
            default:
                logger.debug("500,WordStatus不存在");
        }
        return list;
    }
}
