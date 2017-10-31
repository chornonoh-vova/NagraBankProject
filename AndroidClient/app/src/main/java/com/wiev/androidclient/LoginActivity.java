package com.wiev.androidclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import client.Checker;
import client.Client;
import client.Md5Hasher;

public class LoginActivity extends AppCompatActivity {
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logInClick(View view) {
        new Thread(new ClientThread()).start();
    }

    private class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                socket = new Socket("192.168.1.101", 4444);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                final EditText loginEditText = findViewById(R.id.loginEditText);
                String loginToSend = loginEditText.getText().toString();

                final EditText pinEditText = findViewById(R.id.pinEditText);
                String pinToSend = pinEditText.getText().toString();

                if (!Checker.verifyLogin(loginToSend) || loginToSend.isEmpty()) {
                    //TODO: show error message(wrong login)
                    loginEditText.setText("");
                }
                if (!Checker.verifyPinCode(pinToSend) || loginToSend.isEmpty()) {
                    //TODO: show error message(wrong pin)
                    pinEditText.setText("");
                }

                String hashedPin = Md5Hasher.getMd5Hash(pinToSend);

                Client.sendMessage(out, "login", loginToSend, hashedPin);

                String[] answer = Client.getArrayFromMessage(in);

                //TODO: open main activity if success, show error message if error

                Client.close(out);

                socket.close();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
