package com.midnight.model;

import java.time.LocalDate;

public class MoUserWords {
    private Integer id;
    private Integer userId;
    private Integer wordsId;
    private Integer times;//学习次数
    private Integer level;
    private LocalDate forgetDay;
    private Integer status;//0-未学习，1-已学习

    public MoUserWords() {
    }

    public MoUserWords(Integer id, Integer userId, Integer wordsId, Integer times, Integer level, LocalDate forgetDay, Integer status) {
        this.id = id;
        this.userId = userId;
        this.wordsId = wordsId;
        this.times = times;
        this.level = level;
        this.forgetDay = forgetDay;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getWordsId() {
        return wordsId;
    }

    public void setWordsId(Integer wordsId) {
        this.wordsId = wordsId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public LocalDate getForgetDay() {
        return forgetDay;
    }

    public void setForgetDay(LocalDate forgetDay) {
        this.forgetDay = forgetDay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
