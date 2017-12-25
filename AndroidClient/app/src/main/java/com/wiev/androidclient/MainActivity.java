package com.wiev.androidclient;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import client.Client;
import client.UserInfo;

public class MainActivity extends AppCompatActivity {

  private UserInfo user = null;
  private Client client = Client.getInstance();
  private TextView forBalance = null;

  private Button confirmRefill = null;
  private EditText moneyToRefill = null;
  private Button confirmWidthdraw = null;
  private EditText moneyToWidthdraw = null;
  private Button confirmTransfer = null;
  private EditText transferIdTo = null;
  private EditText moneyToTransfer = null;

  private SwipeRefreshLayout swipeRefreshLayout = null;

  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    TabHost tabHost = findViewById(R.id.tabHost);
    tabHost.setup();

    TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");

    tabSpec.setContent(R.id.tab1);
    tabSpec.setIndicator("Info");
    tabHost.addTab(tabSpec);

    tabSpec = tabHost.newTabSpec("tag2");
    tabSpec.setContent(R.id.tab2);
    tabSpec.setIndicator("Dealing");
    tabHost.addTab(tabSpec);

    tabSpec = tabHost.newTabSpec("tag3");
    tabSpec.setContent(R.id.tab3);
    tabSpec.setIndicator("About");
    tabHost.addTab(tabSpec);

    //tabHost.setCurrentTab(0);

    TextView forId = findViewById(R.id.forId);
    TextView forLogin = findViewById(R.id.forLogin);
    forBalance = findViewById(R.id.forBalance);
    TextView forBirthdate = findViewById(R.id.forBirthdate);
    TextView forSecretQuestion = findViewById(R.id.forSecretQuestion);

    confirmRefill = findViewById(R.id.confirmRefill);
    moneyToRefill = findViewById(R.id.moneyToRefill);

    confirmWidthdraw = findViewById(R.id.confirmWidthdraw);
    moneyToWidthdraw = findViewById(R.id.moneyToWidthdraw);

    confirmTransfer = findViewById(R.id.confirmTransfer);
    transferIdTo = findViewById(R.id.transferIdTo);
    moneyToTransfer = findViewById(R.id.moneyToTransfer);

    swipeRefreshLayout = findViewById(R.id.swiperefresh);

    String userPacked = getIntent().getExtras().getString("user");
    user = new Gson().fromJson(userPacked, UserInfo.class);

    //update fields
    forId.setText(String.valueOf(user.userId));
    forLogin.setText(user.userLogin);
    forBalance.setText(String.valueOf(user.balance));
    forBirthdate.setText(user.birthDate.toString());
    forSecretQuestion.setText(user.secretQuestion);
  }

  @Override
  protected void onStart() {
    super.onStart();
    confirmRefill.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (user.balance > 10000000d) {
          Message errorMessage = new Message();
          errorMessage.messageTitle = "Error";
          errorMessage.messageToShow = "you are already rich";
          errorMessage.show(getFragmentManager(), "error_dialog");
        } else if (moneyToRefill.getText().toString().isEmpty()) {
          Message errorMessage = new Message();
          errorMessage.messageTitle = "Error";
          errorMessage.messageToShow = "Fill amount of money first";
          errorMessage.show(getFragmentManager(), "error_dialog");

        } else {
          new Thread(new Runnable() {
            @Override
            public void run() {
              try {
                client.sendMessage("refill", String.valueOf(user.userId), moneyToRefill
                    .getText().toString());
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
                Message message = new Message();
                message.messageTitle = "success";
                message.messageToShow = "Money refilled";
                message.show(getFragmentManager(), "success");
                runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                    moneyToRefill.setText("");
                  }
                });
              } else {
                Message message = new Message();
                message.messageTitle = answer[0];
                message.messageToShow = answer[1];
                message.show(getFragmentManager(), "success");
                runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                    moneyToRefill.setText("");
                  }
                });
              }
            }
          }).start();
        }
      }
    });
    confirmWidthdraw.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (moneyToWidthdraw.getText().toString().isEmpty()) {
          Message errorMessage = new Message();
          errorMessage.messageTitle = "Error";
          errorMessage.messageToShow = "Fill amount of money first";
          errorMessage.show(getFragmentManager(), "error_dialog");
        } else {
          new Thread(new Runnable() {
            @Override
            public void run() {
              try {
                client.sendMessage("withdrawal", String.valueOf(user.userId), moneyToWidthdraw
                    .getText().toString());
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
                Message message = new Message();
                message.messageTitle = "success";
                message.messageToShow = "Your money delivered";
                message.show(getFragmentManager(), "success");
                runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                    moneyToWidthdraw.setText("");
                  }
                });
              } else {
                Message message = new Message();
                message.messageTitle = answer[0];
                message.messageToShow = answer[1];
                message.show(getFragmentManager(), "success");
                runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                    moneyToWidthdraw.setText("");
                  }
                });
              }
            }
          }).start();
        }
      }
    });

    confirmTransfer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (transferIdTo.getText().toString().equals(String.valueOf(user.userId))) {
          Message errorMessage = new Message();
          errorMessage.messageTitle = "Error";
          errorMessage.messageToShow = "You cannot transfer money to youself";
          errorMessage.show(getFragmentManager(), "error_dialog");
        } else if (transferIdTo.getText().toString().isEmpty() || moneyToTransfer.getText
            ().toString().isEmpty()) {
          Message errorMessage = new Message();
          errorMessage.messageTitle = "Error";
          errorMessage.messageToShow = "Fill all fields first";
          errorMessage.show(getFragmentManager(), "error_dialog");
        } else {
          new Thread(new Runnable() {
            @Override
            public void run() {
              try {
                client.sendMessage("transfer", String.valueOf(user.userId),
                    transferIdTo.getText().toString(), moneyToTransfer.getText().toString());
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
                Message message = new Message();
                message.messageTitle = "success";
                message.messageToShow = "Your money transfered";
                message.show(getFragmentManager(), "success");
                runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                    moneyToWidthdraw.setText("");
                  }
                });
              } else {
                Message message = new Message();
                message.messageTitle = answer[0];
                message.messageToShow = answer[1];
                message.show(getFragmentManager(), "success");
                runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                    transferIdTo.setText("");
                    moneyToTransfer.setText("");
                  }
                });
              }
            }
          }).start();
        }
      }
    });

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              client.sendMessage("update", user.userLogin);
            } catch (Exception e) {
              Message errorMessage = new Message();
              errorMessage.messageTitle = "Error";
              errorMessage.messageToShow = e.getMessage();
              errorMessage.show(getFragmentManager(), "error_dialog");
            }
            String[] upInfo = null;
            try {
              upInfo = client.getArrayFromMessage();
            } catch (Exception e) {
              Message errorMessage = new Message();
              errorMessage.messageTitle = "Error";
              errorMessage.messageToShow = e.getMessage();
              errorMessage.show(getFragmentManager(), "error_dialog");
            }
            user.balance = Double.valueOf(upInfo[2]);
            runOnUiThread(new Runnable() {
              @Override
              public void run() {
                forBalance.setText(String.valueOf(user.balance));
                swipeRefreshLayout.setRefreshing(false);
              }
            });
          }
        }).start();
      }
    });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        closePage();
        return true;
    }
    return true;
  }

  @Override
  public void onBackPressed() {
    closePage();
  }

  @Override
  protected void onDestroy() {
    client.close();
    super.onDestroy();
  }

  private void closePage() {
    new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle("Close")
        .setMessage("Are you sure you want to close this page?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            finish();
          }

        })
        .setNegativeButton("No", null)
        .show();
  }
}
