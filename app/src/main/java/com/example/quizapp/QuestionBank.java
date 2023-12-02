package com.example.quizapp;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionBank  {


   private ArrayList<Question> questionList;



    public ArrayList<Question> getQuestionList(int selectedQuestions) {

     questionList= new ArrayList<>();

    Question question1=new Question("Java is considered a low-level programming language",true, R.color.aqua);
    Question question2=new Question("Void methods must have at least one parameter",false,R.color.yellow);
    Question question3=new Question("Java is a case-sensitive language",true,R.color.green);
    Question question4=new Question("next is not a Java keyword",true,R.color.maroon);
    Question question5=new Question("A class is an instance of its object",false,R.color.lime);
    Question question6=new Question("Attributes are specified by the class's methods",false,R.color.purple_200);
    Question question7=new Question("A printer is not one of the six logical units of a compute",true,R.color.teal_200);
    Question question8=new Question("Microsoft Word is not an example of operating system software",true,R.color.silver);
    Question question9=new Question("Syntax errors are caught by the compiler. Logic errors have effects at execution time",true,R.color.teal);
    Question question10=new Question("A textbook index is not an algorithm",true,R.color.navy);


    questionList.add(question1);
    questionList.add(question2);
    questionList.add(question3);
    questionList.add(question4);
    questionList.add(question5);

    if(selectedQuestions==10){
        questionList.add(question6);
        questionList.add(question7);
        questionList.add(question8);
        questionList.add(question9);
        questionList.add(question10);
    }

        return questionList;
    }
}
