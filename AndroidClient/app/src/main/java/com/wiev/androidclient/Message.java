package com.wiev.androidclient;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class Message extends DialogFragment {
    String messageTitle = null;
    String messageToShow = null;

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(messageTitle);
        builder.setMessage(messageToShow);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //ok button pressed
                return;
            }
        });
        return builder.create();
    }
}
