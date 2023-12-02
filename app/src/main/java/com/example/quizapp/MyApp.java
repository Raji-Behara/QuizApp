package com.example.quizapp;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
    FileManager fileManager = new FileManager();

   private int questionIndex;

   private ArrayList<Question> appQuestionList;

   private int correctAnswers;

    public int getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public ArrayList<Question> getAppQuestionList() {
        if(appQuestionList==null)
        {
            appQuestionList=new ArrayList<>();
        }
        return appQuestionList;
    }

    public void setAppQuestionList(ArrayList<Question> appQuestionList) {
        this.appQuestionList = appQuestionList;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
