package com.wiev.androidclient;

import client.Checker;
import client.Client;
import client.Md5Hasher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ForgotActivity extends AppCompatActivity {
    private Client client = new Client();
    String ip = null;
    private EditText forgotLoginEdit = null;
    private TextView questionForgot = null;
    private EditText answerForgot = null;
    private EditText newPassword = null;
    private EditText newPassword2 = null;
    private ImageButton forgot = null;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Forgot password");
        setContentView(R.layout.activity_forgot);

        forgotLoginEdit = findViewById(R.id.loginEdit);
        questionForgot = findViewById(R.id.forQuestion);
        answerForgot = findViewById(R.id.answerEdit);
        newPassword = findViewById(R.id.password);
        newPassword2 = findViewById(R.id.passwordConfirm);
        forgot = findViewById(R.id.forgot);

        ip = getIntent().getExtras().getString("ip");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.openConnection(ip);
                } catch (Exception e) {
                    Message errorMessage = new Message();
                    errorMessage.messageTitle = "Error";
                    errorMessage.messageToShow = e.getMessage();
                    errorMessage.show(getFragmentManager(), "error_dialog");
                }
            }
        }).start();

        forgotLoginEdit.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    loginEditPressed();
                    return true;
                }
                return false;
            }
        });

        answerForgot.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    answerEditPressed();
                    return true;
                }
                return false;
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotClick();
            }
        });
    }

    private void forgotClick() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String loginToSend = forgotLoginEdit.getText().toString();
                String pin = newPassword.getText().toString();
                String confirmPin = newPassword2.getText().toString();

                if (!Checker.verifyPinCode(pin) || !pin.equals(confirmPin)) {
                    Message errorMessage = new Message();
                    errorMessage.messageTitle = "Error";
                    errorMessage.messageToShow = "Passwords do not match, please try again";
                    errorMessage.show(getFragmentManager(), "error_dialog");
                    return;
                }

                final String hashedPin = Md5Hasher.getMd5Hash(pin);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            client.sendMessage("changePin", loginToSend, hashedPin);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String[] answer = new String[0];
                        try {
                            answer = client.getArrayFromMessage();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (answer[0].equals("success")) {
                            final String[] finalAnswer = answer;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Message errorMessage = new Message();
                                    errorMessage.messageTitle = "Success";
                                    errorMessage.messageToShow = "Password changed successfully. " + "You can log in now!";
                                    errorMessage.show(getFragmentManager(), "success_dialog");

                                }
                            });
                        } else {
                            Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                            startActivity(intent);

                        }
                    }
                }).start();
            }
        }).start();
    }

    private void answerEditPressed() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String loginToSend = forgotLoginEdit.getText().toString();
                String answerToSend = answerForgot.getText().toString();

                try {
                    client.sendMessage("checkQuestion", loginToSend, answerToSend);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String[] answer = new String[0];
                try {
                    answer = client.getArrayFromMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (answer[0].equals("success")) {
                    final String[] finalAnswer = answer;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Message errorMessage = new Message();
                            errorMessage.messageTitle = "Success";
                            errorMessage.messageToShow = "Enter a new password!";
                            errorMessage.show(getFragmentManager(), "success_dialog");
                        }
                    });
                } else {
                    Message errorMessage = new Message();
                    errorMessage.messageTitle = "Error";
                    errorMessage.messageToShow = "Answer incorrect, please try again";
                    errorMessage.show(getFragmentManager(), "error_dialog");
                }
            }
        }).start();
    }

    private void loginEditPressed(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String loginToSend = forgotLoginEdit.getText().toString();

                if (!Checker.verifyLogin(loginToSend)) {
                    return;
                }

                try {
                    client.sendMessage("getQuestion", loginToSend);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String[] answer = new String[0];
                try {
                    answer = client.getArrayFromMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (answer[0].equals("success")) {
                    final String[] finalAnswer = answer;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            questionForgot.setText(finalAnswer[1]);
                        }
                    });
                } else {
                    Message errorMessage = new Message();
                    errorMessage.messageTitle = "Error";
                    errorMessage.messageToShow = "Login incorrect, please try again";
                    errorMessage.show(getFragmentManager(), "error_dialog");

                }
            }
        }).start();
    }
}



