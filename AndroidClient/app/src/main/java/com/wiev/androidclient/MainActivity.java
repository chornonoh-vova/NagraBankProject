package com.wiev.androidclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements ActionDialog.NoticeDialogListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
