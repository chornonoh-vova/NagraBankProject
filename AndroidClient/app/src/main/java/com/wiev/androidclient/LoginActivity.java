package com.wiev.androidclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import client.Client;

public class LoginActivity extends AppCompatActivity {
    private Button logIn = null;
    private Button forgot = null;
    private Button registry = null;

    private EditText ipEditText = null;
    private EditText loginEditText = null;
    private EditText pinEditText = null;

    private Client client = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logIn = findViewById(R.id.logIn);
        forgot = findViewById(R.id.forgot);
        registry = findViewById(R.id.registry);

        ipEditText = findViewById(R.id.ipEditText);
        loginEditText = findViewById(R.id.loginEditText);
        pinEditText = findViewById(R.id.pinEditText);

        client = new Client();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.openConnection(ipEditText.getText().toString());
                } catch (Exception e) {
                    //TODO: show error message: cannot create connection
                }
            }
        }).start();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}
