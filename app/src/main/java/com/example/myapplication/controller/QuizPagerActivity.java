package com.example.myapplication.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.controller.adapter.QuestionAdapter;
import com.example.myapplication.model.Question;

public class QuizPagerActivity extends AppCompatActivity {
    private Button mButtonTrue;
    private Button mButtonFalse;
    private Button mButtonNext;
    private Button mButtonPrevious;
    private Button mButtonCheat;
    public static final String TAG = "QuizActivity";
    public static final String BUNDLE_KEY_CURRENT_INDEX = "currentIndex";
    public static final String EXTRA_KEY_IS_ANSWER_TRUE = "com.example.myapplication.isAnswerTrue";
    public static final int REQUEST_CODE_CHEAT_ACTIVITY = 0;
    private ViewPager2 mQuestionViewPager;
    private boolean mIsCheated = false;
    private int mCurrentIndex = 0;


    private Question[] mQuestionBank = {
            new Question("استرالیا بزرگ ترین قاره جهان است", false),
            new Question("اقیانوس آرام بزرگ ترین اقیانوس جهان است", true),
            new Question("همه کشورهای خاورمیانه عرب زبان هستند", false),
            new Question("مصر در قاره آفریقا قرار دارد", true),
            new Question("کشور آمریکا پرجمعیت ترین کشور دنیا است", false),
            new Question("استرالیا بزرگ ترین قاره جهان است.", false)
    };

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);/*
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame_frag_quiz,new QuizFrag()).commit();*/
        findView();
        setOnklick();
        setUI();
    }

    private void setUI() {
        QuestionAdapter adapter = new QuestionAdapter(this, mQuestionBank);
        mQuestionViewPager.setAdapter(adapter);
        mQuestionViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mCurrentIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void findView() {
        mQuestionViewPager = findViewById(R.id.viewPager_question);
        mButtonTrue = findViewById(R.id.button_true);
        mButtonFalse = findViewById(R.id.button_false);
        mButtonNext = findViewById(R.id.button_next);
        mButtonPrevious = findViewById(R.id.button_previous);
        mButtonCheat = findViewById(R.id.button_cheat);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK || data == null)
            return;
        if (requestCode == QuizPagerActivity.REQUEST_CODE_CHEAT_ACTIVITY) {
            mIsCheated = data.getBooleanExtra(CheatActivity.EXTRA_IS_CHEAT, false);
        }
    }

    private void startCheatActivity() {
        boolean isAnswerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        Intent intent = new Intent(this, CheatActivity.class);

        intent.putExtra(QuizPagerActivity.EXTRA_KEY_IS_ANSWER_TRUE, isAnswerTrue);

        startActivityForResult(intent, QuizPagerActivity.REQUEST_CODE_CHEAT_ACTIVITY);
    }

    public void checkAnswer(boolean userPressed) {
        boolean isAnswerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        if (mIsCheated) {
            Toast.makeText(this, R.string.toast_judgment, Toast.LENGTH_SHORT).show();
        } else {
            if (userPressed == isAnswerTrue) {
                Toast.makeText(this, R.string.toast_correct, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.toast_incorrect, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void setOnklick() {
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (++mCurrentIndex) % mQuestionBank.length;
                mQuestionViewPager.setCurrentItem(mCurrentIndex);
                mIsCheated = false;
            }
        });

        mButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (--mCurrentIndex + mQuestionBank.length) % mQuestionBank.length;
                mQuestionViewPager.setCurrentItem(mCurrentIndex);
                mIsCheated = false;
            }
        });
        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCheatActivity();
            }
        });
    }
}