package com.midnight.model;

public class RecitingWord {
    private int id;
    private int wordId;
    private int userId;
    private int knowTimes;
    private int vagueTimes;
    private int forgetTimes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getKnowTimes() {
        return knowTimes;
    }

    public void setKnowTimes(int knowTimes) {
        this.knowTimes = knowTimes;
    }

    public int getVagueTimes() {
        return vagueTimes;
    }

    public void setVagueTimes(int vagueTimes) {
        this.vagueTimes = vagueTimes;
    }

    public int getForgetTimes() {
        return forgetTimes;
    }

    public void setForgetTimes(int forgetTimes) {
        this.forgetTimes = forgetTimes;
    }
}
