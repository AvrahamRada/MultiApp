package com.classy.garage;

import android.os.Bundle;

import com.classy.common.commonParent.ParentActivity;

public class GarageActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeVariables("Garage");
        listeners();
    }
}