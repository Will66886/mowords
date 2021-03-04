package com.midnight.model;

public class MoWords {
    private int id;
    private String word;
    private String translation;
    private String pronunciation;

    public MoWords() {
    }

    public MoWords(int id, String word, String translation, String pronunciation) {
        this.id = id;
        this.word = word;
        this.translation = translation;
        this.pronunciation = pronunciation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }
}
