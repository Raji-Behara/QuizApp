package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    FileManager fm;
    Button btnTrue;
    Button btnFalse;
    TextView addQuestion;
    ProgressBar progress;
    int currentProgress=1;
    private String resultMessage="Your score is #correctAnswer# out of #selectedQuestions#";
    private String averageMessage="Your correct answers are   ##correctAnswer## out of #totalQuestions# " +
            " i.e  ##/## in ##noOfAttempts## attempts";
    int nextIndex =0;
   int correctAnswers;
    int incorrectAnswers;

    int selectedQuizQuestions;
private QuestionBank questionBank;

private ArrayList<Question> quizQuestionList;
int configChange=0;


   @SuppressLint("MissingInflatedId")
   @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       addQuestion = findViewById(R.id.question_text);

       btnTrue = findViewById(R.id.btn_true);
       btnFalse = findViewById(R.id.btn_false);

       progress = findViewById(R.id.progressbar);
        progress.setProgress(currentProgress);

       selectedQuizQuestions=getIntent().getIntExtra("selectedQuizQuestions",10);
       fm =  ((MyApp)getApplication()).fileManager;
       questionBank = new QuestionBank();
       quizQuestionList = questionBank.getQuestionList(selectedQuizQuestions,MainActivity.this);

       //getting question from framelayout

       Fragment f = getSupportFragmentManager().findFragmentById(R.id.framelayout);

           if (f != null) {
               getSupportFragmentManager().beginTransaction().remove(f).commit();
               quizQuestionList=((MyApp)getApplication()).getAppQuestionList();
               nextIndex=((MyApp) getApplication()).getQuestionIndex();
               correctAnswers=((MyApp)getApplication()).getCorrectAnswers();

               selectedQuizQuestions=((MyApp)getApplication()).getSelectedQuizQuestions();

           }
           else {

               quizQuestionList= questionBank.getQuestionList(selectedQuizQuestions,MainActivity.this);
               Collections.shuffle(quizQuestionList);
               ((MyApp)getApplication()).setAppQuestionList(quizQuestionList);
               ((MyApp)getApplication()).setQuestionIndex(nextIndex);
               ((MyApp)getApplication()).setCorrectAnswers(correctAnswers);
               ((MyApp)getApplication()).setSelectedQuizQuestions(selectedQuizQuestions);

           }

       FirstFragment firstFragment = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout);
       if (firstFragment == null) {

           progress.setProgress(1);
           progress.setMax(selectedQuizQuestions);
           firstFragment = FirstFragment.newInstance(quizQuestionList.get(0));
           getSupportFragmentManager().beginTransaction().add(R.id.framelayout, firstFragment).commit();
           nextIndex++;

           progress.setProgress(currentProgress);

       } else {
           getSupportFragmentManager().beginTransaction().remove(firstFragment).commit();
           String msg = addQuestion.getText().toString();
           firstFragment = FirstFragment.newInstance(quizQuestionList.get(nextIndex));
           getSupportFragmentManager().beginTransaction().add(R.id.framelayout, firstFragment).commit();
           msg = addQuestion.getText().toString();
           nextIndex++;

       }

       if(nextIndex==quizQuestionList.size()) {
           askThenSave();
       }

//True button clicked

       btnTrue.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

//progress bar
               currentProgress = currentProgress + 1;
               progress.setProgress(currentProgress);
               progress.setMax(selectedQuizQuestions);

               if (quizQuestionList.get(nextIndex).isAnswer() == true) {
                   Toast.makeText(MainActivity.this, "Corrct", Toast.LENGTH_SHORT).show();
                   Log.d("true clicked", "progress");
                   correctAnswers++;
               } else {

                   // Toast.makeText(MainActivity.this,"InCorrct",Toast.LENGTH_SHORT).show();
                   Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                   incorrectAnswers++;

               }

               Fragment f = getSupportFragmentManager().findFragmentById(R.id.framelayout);
               if (f != null) {
                   getSupportFragmentManager().beginTransaction().remove(f).commit();

               }

               Collections.shuffle(quizQuestionList);
               FirstFragment firstFragment = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout);
               firstFragment = FirstFragment.newInstance(quizQuestionList.get(nextIndex));
               getSupportFragmentManager().beginTransaction().add(R.id.framelayout, firstFragment).commit();

               nextIndex++;
             if(nextIndex==quizQuestionList.size())

              {
                  //calculateScore(quizQuestionList,correctAnswers);
                   askThenSave();
              }

           }
       });
//False button
       btnFalse.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               currentProgress = currentProgress + 1;
               progress.setProgress(currentProgress);
               progress.setMax(selectedQuizQuestions);

               if (quizQuestionList.get(nextIndex).isAnswer() == false) {
                   Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                   correctAnswers++;

               } else {

                   Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                   incorrectAnswers++;

               }

               Fragment f = getSupportFragmentManager().findFragmentById(R.id.framelayout);
               if (f != null) {
                   getSupportFragmentManager().beginTransaction().remove(f).commit();
               }
               FirstFragment firstFragment = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout);
               firstFragment = FirstFragment.newInstance(quizQuestionList.get(nextIndex));
               getSupportFragmentManager().beginTransaction().add(R.id.framelayout, firstFragment).commit();
               nextIndex++;

               if(nextIndex==quizQuestionList.size())
               {
                   askThenSave();
               }

           }
       });
   }

//save results in filesystem
       void askThenSave(){
           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
           resultMessage=resultMessage.replace("#correctAnswer#",correctAnswers+"").replace("#selectedQuestions#",quizQuestionList.size()+"");
           builder.setMessage(resultMessage);
           builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {

                //calling write function of filemanager
                 fm.writeResultFile(MainActivity.this,correctAnswers,quizQuestionList.size());
               }
           });
           builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                  // getAllToDoFromDB();
               }
           });
           builder.create().show();

       }
       //Read the file from file system and calculate Average

    void getAverage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        QuizResult quizResult = fm.readAllResults(MainActivity.this);
        averageMessage=averageMessage.replace("##correctAnswer##",quizResult.getCorrectAnswers()+"")
                .replace("#totalQuestions# ",quizResult.getSelectedQuestions()+"")
                .replace("##/##",quizResult.getCorrectAnswers()+"/"+quizResult.getSelectedQuestions())
                .replace("##noOfAttempts##",quizResult.getAttempts()+"");
        builder.setMessage(averageMessage);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }

//delete file from file system

    void askThenDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        resultMessage=resultMessage.replace("#correctAnswer#",correctAnswers+"").replace("#totalQuestions#",quizQuestionList.size()+"");
        builder.setTitle("Are you sure you want to  Reset the file");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                fm.deleteResult(MainActivity.this);


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }


//Bonus Question
 //Select questions using Radiobuttoins in dialogue alert
    private void showRadioButtonDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" Select Number of Questions")
                .setSingleChoiceItems(new CharSequence[]{"5", "10","15"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the selected option
                        switch (which) {
                            case 0:
                              Toast.makeText(MainActivity.this,"5 Questions Quiz Selected",Toast.LENGTH_SHORT).show();  // Option 1 is selected
                               // selectedQuizQuestions=5;
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                intent.putExtra("selectedQuizQuestions",5);
                                startActivity(intent);
                                break;
                            case 1:
                                Toast.makeText(MainActivity.this,"10Questions Quiz Selected",Toast.LENGTH_SHORT).show();
                               // selectedQuizQuestions=10;
                                Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                                intent1.putExtra("selectedQuizQuestions",10);
                                startActivity(intent1);

                                break;
                            case 2:
                                Toast.makeText(MainActivity.this,"15 Questions Quiz Selected",Toast.LENGTH_SHORT).show();  // Option 1 is selected
                                // selectedQuizQuestions=5;
                                Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                                intent2.putExtra("selectedQuizQuestions",15);
                                startActivity(intent2);
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

//Menu Option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       MenuInflater inflater= getMenuInflater();
       inflater.inflate(R.menu.menu_example,menu);
        return super.onCreateOptionsMenu(menu);
    }

//Menu items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//average
            case R.id.average:
                getAverage();
                return true;
        //select number of questions

            case R.id.select:

                showRadioButtonDialog();

                return true;
//delete file
            case R.id.reset:

                askThenDelete();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}