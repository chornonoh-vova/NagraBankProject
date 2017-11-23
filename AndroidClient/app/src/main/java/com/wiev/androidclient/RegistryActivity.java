package com.wiev.androidclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class RegistryActivity extends AppCompatActivity{
    private ImageButton registration = null;
    private EditText login = null;
    private EditText password = null;
    private EditText password_confirm = null;
    private EditText birthdate = null;
    private EditText question = null;
    private EditText answer = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        registration = findViewById(R.id.registration);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        password_confirm = findViewById(R.id.password_confirm);
        birthdate = findViewById(R.id.birthdate);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
