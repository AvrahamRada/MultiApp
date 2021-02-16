package com.classy.common;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class ParentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
    }

    protected void set(String text){

    }
}
