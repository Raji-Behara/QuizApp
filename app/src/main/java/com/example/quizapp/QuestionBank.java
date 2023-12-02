package com.example.quizapp;

import android.content.Context;
import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionBank  {


   private ArrayList<Question> questionList;



    public ArrayList<Question> getQuestionList( int selectedQuestions,Context context) {

     questionList= new ArrayList<>();
        Question question1=new Question(context.getResources().getString(R.string.q1),true,R.color.aqua);
        Question question2=new Question(context.getResources().getString(R.string.q2),true,R.color.blue);
        Question question3=new Question(context.getResources().getString(R.string.q3),true,R.color.yellow);
        Question question4=new Question(context.getResources().getString(R.string.q4),true,R.color.blue);
        Question question5=new Question(context.getResources().getString(R.string.q5),true,R.color.green);
        Question question6=new Question(context.getResources().getString(R.string.q6),true,R.color.gray);
        Question question7=new Question(context.getResources().getString(R.string.q7),true,R.color.teal);
        Question question8=new Question(context.getResources().getString(R.string.q8),true,R.color.maroon);
        Question question9=new Question(context.getResources().getString(R.string.q9),true,R.color.silver);
        Question question10=new Question(context.getResources().getString(R.string.q10),true,R.color.purple);

        Question question11=new Question(context.getResources().getString(R.string.q11),false,R.color.olive);
        Question question12=new Question(context.getResources().getString(R.string.q12),true,R.color.purple_700);
        Question question13=new Question(context.getResources().getString(R.string.q13),true,R.color.DarkOrange);
        Question question14=new Question(context.getResources().getString(R.string.q14),true,R.color.Gold);
        Question question15=new Question(context.getResources().getString(R.string.q15),false,R.color.Tomato);




    questionList.add(question1);
    questionList.add(question2);
    questionList.add(question3);
    questionList.add(question4);
    questionList.add(question5);

    if(selectedQuestions>=10){
        questionList.add(question6);
        questionList.add(question7);
        questionList.add(question8);
        questionList.add(question9);
        questionList.add(question10);
    }
if(selectedQuestions>=15)
{
    questionList.add(question11);
    questionList.add(question12);
    questionList.add(question13);
    questionList.add(question14);
    questionList.add(question15);
}
        return questionList;
    }
}
