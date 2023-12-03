package com.example.quizapp;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    String fileName = "QuizScore.txt";

    //Save file

    void writeResultFile(Context context, int quizResult,int selectedQuestions) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            fos.write((quizResult+"_"+selectedQuestions+"$").getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //Reset file

    void deleteResult(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            int quizResult;
           fos.write(("").getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Read file

    public QuizResult readAllResults(Context context){
        QuizResult quizResult = new QuizResult();
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            int read = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while (( read = inputStreamReader.read() ) != -1){
                stringBuffer.append((char)read);
            }
            List<String> restulstStrings=convertStringToResults(stringBuffer.toString());
            int correctAnswers=0;
            int totalQuestions=0;
            int totalAttempts=0;
            for(String result:restulstStrings){
              String[] resulsts=result.split("_");
              correctAnswers=correctAnswers+Integer.parseInt(resulsts[0]);
              totalQuestions=totalQuestions+Integer.parseInt(resulsts[1]);
              totalAttempts++;
            }
            quizResult.setCorrectAnswers(correctAnswers);
            quizResult.setSelectedQuestions(totalQuestions);
            quizResult.setAttempts(totalAttempts);
            //Close the FileInputStream after reading all the results
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quizResult;

    }

    private   List<String> convertStringToResults(String fileContent){
        List<String> resultList;
        if(fileContent!=null&&!fileContent.isEmpty()){
           // String[] resultArray=fileContent.split("#");
            String[] resultArray=fileContent.split("\\$");
            resultList= Arrays.asList(resultArray);
          }else {
            //file content is empty or null
            resultList=new ArrayList<String>();
        }

        return resultList;
    }



}
