package com.midnight.memory;

import com.midnight.model.MoWords;
import com.midnight.model.RecitingWord;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface MnemonicsFactory {
    List<RecitingWord> learnWord(Integer userId, Integer wordId, Integer status, LinkedList<RecitingWord> list);
}
