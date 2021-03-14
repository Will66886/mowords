package com.midnight.model;

import java.io.Serializable;

public class RecitingWord implements Serializable {
    private int wordId;
    private int learnTimes;//学习次数
    private int iterations;//当前状态维系次数
    private WordStatus status;//0-未学，1-认识，2-模糊，3-忘记

    public RecitingWord() {
    }

    public RecitingWord(int wordId, int learnTimes, int iterations, WordStatus status) {
        this.wordId = wordId;
        this.learnTimes = learnTimes;
        this.iterations = iterations;
        this.status = status;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getLearnTimes() {
        return learnTimes;
    }

    public void setLearnTimes(int learnTimes) {
        this.learnTimes = learnTimes;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public WordStatus getStatus() {
        return status;
    }

    public void setStatus(WordStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RecitingWord{" +
                "wordId=" + wordId +
                ", learnTimes=" + learnTimes +
                ", iterations=" + iterations +
                ", status=" + status +
                '}';
    }
}

