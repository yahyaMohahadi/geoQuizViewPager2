package com.example.myapplication.controller.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.controller.fragment.QuizFrag;
import com.example.myapplication.model.Question;



public class QuestionAdapter extends FragmentStateAdapter {
    private Question[] mQuestions;
    public QuestionAdapter(@NonNull FragmentActivity fragmentActivity, Question[] questions) {
        super(fragmentActivity);
        mQuestions = questions;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = QuizFrag.getInstanse(mQuestions[position].getTextQuestion());
        return fragment;
    }

    @Override
    public int getItemCount() {
        return mQuestions.length;
    }
}
