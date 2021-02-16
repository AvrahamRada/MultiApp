package com.classy.garage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.classy.common.ParentActivity;

public class GarageActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
    }
}