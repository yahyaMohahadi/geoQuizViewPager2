package com.example.myapplication.model;

public class Question {
    private String mTextQuestion;
    private boolean mAnswerTrue;

    public String getTextQuestion() {
        return mTextQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        mTextQuestion = textQuestion;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question(String textResId, boolean answerTrue) {
        mTextQuestion = textResId;
        mAnswerTrue = answerTrue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mTextResId=" + mTextQuestion +
                ", mAnswerTrue=" + mAnswerTrue +
                '}';
    }
}
