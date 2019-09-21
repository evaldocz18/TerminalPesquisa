package com.evaldo.terminalperquisacliente.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Dialog {

    //private AlertDialog dialog;
    private AlertDialog.Builder builder;

    public Dialog(String title, Context context){
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

    }

    public void setConfirm(final Runnable runnable){
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                runnable.run();
            }
        });
    }

    public void setNegation(final Runnable runnable){
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                runnable.run();
            }
        });
    }

    public void show(){

        builder.create().show();
    }


}
