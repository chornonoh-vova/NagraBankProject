package com.wiev.androidclient;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import client.Checker;
import client.Client;
import client.Md5Hasher;


public class RegistryActivity extends AppCompatActivity implements ActionDialog.NoticeDialogListener{
    private ImageButton registration = null;
    private EditText login = null;
    private EditText password = null;
    private EditText password_confirm = null;
    private EditText birthdate = null;
    private EditText question = null;
    private EditText answer = null;

    private Client client = new Client();
    String ip = null;

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

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String loginToSend = login.getText().toString();
                final String passwordToSend = password.getText().toString();
                String passwordConfirmToSend = password_confirm.getText().toString();
                final String birthdateToSend = birthdate.getText().toString();
                final String questionToSend = question.getText().toString();
                final String answerToSend = answer.getText().toString();
                if (!Checker.verifyLogin(loginToSend)) {
                    Message errorMessage = new Message();
                    errorMessage.messageTitle = "Error";
                    errorMessage.messageToShow = "Incorrect login";
                    errorMessage.show(getFragmentManager(), "error_dialog");
                } else if (!Checker.verifyPinCode(passwordToSend) || !passwordToSend.equals(passwordConfirmToSend)) {
                    Message errorMessage = new Message();
                    errorMessage.messageTitle = "Error";
                    errorMessage.messageToShow = "Incorrect password or not confirmed";
                    errorMessage.show(getFragmentManager(), "error_dialog");
                } else if (!Checker.verifyDate(birthdateToSend)) {
                    Message errorMessage = new Message();
                    errorMessage.messageTitle = "Error";
                    errorMessage.messageToShow = "Incorrect birthdate";
                    errorMessage.show(getFragmentManager(), "error_dialog");
                } else {
                    final String hashedPin = Md5Hasher.getMd5Hash(passwordToSend);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                client.sendMessage("registry", hashedPin, loginToSend, birthdateToSend,
                                        questionToSend, answerToSend);

                            } catch (Exception e) {
                                Message errorMessage = new Message();
                                errorMessage.messageTitle = "Error";
                                errorMessage.messageToShow = e.getMessage();
                                errorMessage.show(getFragmentManager(), "error_dialog");
                            }
                            String[] answer = null;
                            try {
                                answer = client.getArrayFromMessage();
                            } catch (Exception e) {
                                Message errorMessage = new Message();
                                errorMessage.messageTitle = "Error";
                                errorMessage.messageToShow = e.getMessage();
                                errorMessage.show(getFragmentManager(), "error_dialog");

                            }
                            if (answer[0].equals("success")) {
                                ActionDialog message = new ActionDialog();
                                message.title = "success";
                                message.message = "Welcome to our Family!\n" +
                                        "You can now switch to login page";
                                message.show(getFragmentManager(), "success");

                            } else {
                                Message message = new Message();
                                message.messageTitle = answer[0];
                                message.messageToShow = answer[1];
                                message.show(getFragmentManager(), "error");
                            }
                        }
                    }).start();
                }
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        finish();
    }
}
