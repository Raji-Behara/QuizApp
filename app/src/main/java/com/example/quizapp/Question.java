package com.example.quizapp;

public class Question {

    String text;
    int color;
    boolean answer;

    public String getText() {
        return text;
    }

    public int getColor() {
        return color;
    }

    public boolean isAnswer() {
        return answer;
    }

    public Question(String text,boolean answer ,int color) {
        this.text = text;
        this.color = color;
        this.answer = answer;

    }
}
