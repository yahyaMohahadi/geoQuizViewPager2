package com.example.myapplication.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.controller.QuizPagerActivity;


public class QuizFrag extends Fragment {

    private static TextView mTextViewQuestion;
    private static String mStringQuestion;

    public static QuizFrag getInstanse(String stringQuestion) {
        QuizFrag quizFrag = new QuizFrag();
        quizFrag.setStringQuestion(stringQuestion);
        return quizFrag;

    }

    public static void setStringQuestion(String stringQuestion) {
        mStringQuestion = stringQuestion;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.quiz_frag,
                container
                , false);
        if (savedInstanceState != null) {
            setStringQuestion(savedInstanceState.getString(QuizPagerActivity.BUNDLE_KEY_CURRENT_INDEX));
        }
        findViews(view);
        updateQuestion();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(QuizPagerActivity.BUNDLE_KEY_CURRENT_INDEX, mStringQuestion);
    }

    private static void updateQuestion() {
        mTextViewQuestion.setText(mStringQuestion);
    }


    private void findViews(View view) {
        mTextViewQuestion = view.findViewById(R.id.text_view_question);
    }
}