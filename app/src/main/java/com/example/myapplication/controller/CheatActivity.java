package com.example.myapplication.controller;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.controller.fragment.CheatFrag;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_IS_CHEAT = "com.example.myapplication.isCheated";

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragment_cheat_place,new CheatFrag()).commit();
    }
}