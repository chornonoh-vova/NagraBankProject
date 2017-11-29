package com.wiev.androidclient;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class ForgotActivity extends AppCompatActivity implements ActionDialog.NoticeDialogListener {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

    }
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        finish();
    }
}
