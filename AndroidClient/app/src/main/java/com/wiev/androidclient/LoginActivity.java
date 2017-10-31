package com.wiev.androidclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import client.Checker;
import client.Client;
import client.Md5Hasher;

public class LoginActivity extends AppCompatActivity {
    private Button connect = null;
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

        connect = findViewById(R.id.connect);
        logIn = findViewById(R.id.logIn);
        logIn.setEnabled(false);
        forgot = findViewById(R.id.forgot);
        registry = findViewById(R.id.registry);

        ipEditText = findViewById(R.id.ipEditText);
        loginEditText = findViewById(R.id.loginEditText);
        pinEditText = findViewById(R.id.pinEditText);

        client = new Client();

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            client.openConnection(ipEditText.getText().toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ipEditText.setEnabled(false);
                                    connect.setEnabled(false);
                                    logIn.setEnabled(true);
                                }
                            });
                        } catch (Exception e) {
                            //TODO: show error message: cannot create connection e.getMessage
                        }
                    }
                }).start();
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        if (Checker.verifyLogin(loginEditText.getText().toString()) && Checker.verifyPinCode(pinEditText.getText().toString())) {
                            String hashedPin = Md5Hasher.getMd5Hash(pinEditText.getText().toString());
                            try {
                                client.sendMessage("login", loginEditText.getText().toString(), hashedPin);
                            } catch (Exception e) {
                                //TODO: show error message e.getMessage
                            }
                        } else {
                            //TODO: show error message: wrong login or password
                        }
                        try {
                            String[] answer = client.getArrayFromMessage();
                            if (answer[0].equals("success")) {
                                //TODO: to main activity
                            } else {
                                //TODO: show error message answer[0], answer[1]
                            }
                        } catch (Exception e) {
                            //TODO: show error message: cannot receive answer
                        }
                    }
                }).start();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: show forgot pin activity
            }
        });

        registry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: to registry activity
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            client.closeConnection();
        } catch (Exception e) {
            //do nothing
        }
    }
}
