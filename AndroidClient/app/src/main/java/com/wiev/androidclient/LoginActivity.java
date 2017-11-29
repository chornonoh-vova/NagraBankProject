package com.wiev.androidclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Date;

import client.Checker;
import client.Client;
import client.Md5Hasher;
import client.UserInfo;

public class LoginActivity extends AppCompatActivity {
  private Button connect = null;
  private Button logIn = null;
  private Button forgot = null;
  private Button registry = null;

  private EditText ipEditText = null;
  private EditText loginEditText = null;
  private EditText pinEditText = null;

  private Client client = new Client();

  private String savedIp = null;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle(R.string.activity_login);
    setContentView(R.layout.activity_login);

    connect = findViewById(R.id.connect);
    logIn = findViewById(R.id.logIn);
    logIn.setEnabled(false);
    forgot = findViewById(R.id.forgot);
    registry = findViewById(R.id.registry);

    ipEditText = findViewById(R.id.ipEditText);
    loginEditText = findViewById(R.id.loginEditText);
    pinEditText = findViewById(R.id.pinEditText);

    SharedPreferences sharedPrefs = getPreferences(Context.MODE_PRIVATE);
    savedIp = sharedPrefs.getString(getString(R.string.saved_ip), null);
    ipEditText.setText(savedIp);

    connect.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            SharedPreferences sharedPrefs = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(getString(R.string.saved_ip), ipEditText.getText().toString());
            editor.commit();
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
              //show error message: cannot create connection e.getMessage
              Message errorMessage = new Message();
              errorMessage.messageTitle = "Error";
              errorMessage.messageToShow = e.getMessage();
              errorMessage.show(getFragmentManager(), "error_dialog");
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
                //show error message: cannot send message
                Message errorMessage = new Message();
                errorMessage.messageTitle = "Error";
                errorMessage.messageToShow = e.getMessage();
                errorMessage.show(getFragmentManager(), "error_dialog");
              }
            } else {
              //show error message: wrong login or password
              Message errorMessage = new Message();
              errorMessage.messageTitle = "Error";
              errorMessage.messageToShow = "Wrong login or pin";
              errorMessage.show(getFragmentManager(), "error_dialog");
            }
            try {
              String[] answer = client.getArrayFromMessage();
              if (answer[0].equals("success")) {
                //create UserInfo object
                UserInfo user = new UserInfo();
                user.userId = Integer.valueOf(answer[1]);
                user.balance = Double.valueOf(answer[2]);
                user.secretQuestion = answer[3];
                user.birthDate = Date.valueOf(answer[4]);
                user.userLogin = loginEditText.getText().toString();
                user.pin = Md5Hasher.getMd5Hash(pinEditText.getText().toString());

                String userPacked = client.gson.toJson(user);
                //switch to main activity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("ip", savedIp);
                intent.putExtra("user", userPacked);
                startActivity(intent);
              } else {
                //show error message answer[0], answer[1]
                Message errorMessage = new Message();
                errorMessage.messageTitle = answer[0];
                errorMessage.messageToShow = answer[1];
                errorMessage.show(getFragmentManager(), "error_dialog");
              }
            } catch (Exception e) {
              //show error message: cannot receive answer
              Message errorMessage = new Message();
              errorMessage.messageTitle = "Error";
              errorMessage.messageToShow = e.getMessage();
              errorMessage.show(getFragmentManager(), "error_dialog");
            }
          }
        }).start();
      }
    });

    forgot.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //TODO: show forgot pin activity
          Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
          startActivity(intent);
      }
    });

    registry.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //TODO: to registry activity
        Intent intent = new Intent(LoginActivity.this, RegistryActivity.class);
        intent.putExtra("ip", savedIp);
        startActivity(intent);
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
