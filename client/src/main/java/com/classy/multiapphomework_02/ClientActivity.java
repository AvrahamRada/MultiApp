package com.classy.multiapphomework_02;

import android.os.Bundle;


import com.classy.common.commonParent.ParentActivity;

public class ClientActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeVariables("Client");
        listeners();
    }
}