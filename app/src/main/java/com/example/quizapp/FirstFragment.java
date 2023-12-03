package com.example.quizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    static   String questionText ;
    static int color;
    public static FirstFragment newInstance(Question qtn) {
        FirstFragment ff = new FirstFragment();
        questionText = qtn.getText();
        color    =qtn.getColor();
        return ff;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.first_fragment,container,false);
        TextView text = v.findViewById(R.id.question_text);
        text.setText(questionText);
       // text.setTextColor(color);
       // text.setBackgroundColor(color);
        text.setBackgroundResource(color);
        return v;

    }
}
