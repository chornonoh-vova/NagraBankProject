package com.wiev.androidclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mainToolBar = findViewById(R.id.my_toolbar);
        mainToolBar.setTitle(R.string.activity_main);
        setSupportActionBar(mainToolBar);
    }
}
