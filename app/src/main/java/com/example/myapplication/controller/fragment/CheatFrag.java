package com.example.myapplication.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.controller.CheatActivity;
import com.example.myapplication.controller.QuizPagerActivity;

import static android.app.Activity.RESULT_OK;


public class CheatFrag extends Fragment {

    private TextView mTextViewAnswer;
    private Button mButtonCheat;
    private Button mButtonBack;
    private boolean mIsAnswerTrue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.cheat_frag, container, false);
        findAllViews(view);
        setClickListeners(view);
        mIsAnswerTrue = getActivity().getIntent().getBooleanExtra(QuizPagerActivity.EXTRA_KEY_IS_ANSWER_TRUE,false);
        return view;
    }

    private void setClickListeners(View view) {
        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAnswerTrue) {
                    mTextViewAnswer.setText(R.string.button_true);
                } else {
                    mTextViewAnswer.setText(R.string.button_false);
                }

                saveResult(true);
            }
        });

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheatFrag.this.getActivity().finish();
            }
        });
    }

    private void saveResult(boolean isCheated) {
        Intent intent = new Intent();
        intent.putExtra(CheatActivity.EXTRA_IS_CHEAT, isCheated);
        this.getActivity()
    .setResult(RESULT_OK, intent);

    }

    private void findAllViews(View view) {
        mTextViewAnswer = view.findViewById(R.id.text_view_show_answer);
        mButtonCheat = view.findViewById(R.id.button_show_cheat);
        mButtonBack = view.findViewById(R.id.button_back);
    }
}