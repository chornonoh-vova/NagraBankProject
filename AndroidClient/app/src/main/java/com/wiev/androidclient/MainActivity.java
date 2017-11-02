package com.wiev.androidclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.app.DialogFragment;
import android.widget.TextView;

import com.google.gson.Gson;

import client.Client;
import client.UserInfo;

public class MainActivity extends AppCompatActivity implements ActionDialog.NoticeDialogListener,
    SwipeRefreshLayout.OnRefreshListener {

    private UserInfo user = null;
    private Client client = new Client();

    private String ip = null;
    private TextView forLogin = null;
    private TextView forBalance = null;
    private TextView forBirthdate = null;
    private TextView forSecretQuestion = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forLogin = findViewById(R.id.forLogin);
        forBalance = findViewById(R.id.forBalance);
        forBirthdate = findViewById(R.id.forBirthdate);
        forSecretQuestion = findViewById(R.id.forSecretQuestion);

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

        String userPacked = getIntent().getExtras().getString("user");
        user = client.gson.fromJson(userPacked, UserInfo.class);

        //update fields
        forLogin.setText(user.userLogin);
        forBalance.setText(String.valueOf(user.balance));
        forBirthdate.setText(user.birthDate.toString());
        forSecretQuestion.setText(user.secretQuestion);
    }

    @Override
    public void onBackPressed() {
        ActionDialog dialog = new ActionDialog();
        dialog.message = "Do you really wanna quit?";
        dialog.title = "Back";
        dialog.show(getFragmentManager(), "action_dialog");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) { super.onBackPressed(); }

    @Override
    public void onRefresh() {

    }
}
