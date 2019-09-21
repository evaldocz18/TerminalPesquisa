package com.evaldo.terminalperquisacliente.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.evaldo.terminalperquisacliente.telasPerguntas.TelaFinalAgradecimentoActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.chamarTelaFinalAgradecimento;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.contextDinamico;

public class DialogTimeoutListener implements DialogInterface.OnShowListener, DialogInterface.OnDismissListener {

    private static final int AUTO_DISMISS_MILLIS = 10000;
    public static CountDownTimer mCountDownTimer;
    public static Context context;
    public static int apertouContinuar;


    public static CountDownTimer cronometro;
    public static  AlertDialog dialog;

    @Override
        public void onShow(final DialogInterface dialog) {
            final Button defaultButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
            final CharSequence positiveButtonText = defaultButton.getText();
            mCountDownTimer = new CountDownTimer(AUTO_DISMISS_MILLIS, 100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (millisUntilFinished > 60000) {
                        defaultButton.setText(String.format(
                                Locale.getDefault(), "%s (%d:%02d)",
                                positiveButtonText,
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished % 60000)
                        ));
                    } else {

                        defaultButton.setText(String.format(
                                Locale.getDefault(), "%s (%d)",
                                positiveButtonText,
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1 //add one so it never displays zero

                        ));
                    }
                }

                @Override
                public void onFinish() {
                    if (((AlertDialog) dialog).isShowing()) {
                        // TODO: call your logout method
                        /*if (interagiu){
                            System.out.println("Interagiu");
                        }else if(interagiu == false){
                            System.out.println("DialogTimeoutListener onFinish() e apertouContinuar = " + apertouContinuar);
                            System.out.println("Não interagiu");
                            cronometro.onFinish();
                            chamarTela();
                        }*/
                        chamarTela();
                        dialog.dismiss();
                    }
                }
            };

            mCountDownTimer.start();
        }
        public static void chamarTela(){
            Intent intent = new Intent(contextDinamico, TelaFinalAgradecimentoActivity.class);
            contextDinamico.startActivity(intent);

        }


    public static void cronometroChamarDialog() {



        cronometro = new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {

            System.out.println("cronometro 1= " + millisUntilFinished + "   Tela = " + contextDinamico);
            }

            public void onFinish() {

                System.out.println("cronometro done Tela = " + contextDinamico);

                timeOutDialog();
            }
        }.start();
    }


    public static void timeOutDialog() {
        dialog = new AlertDialog.Builder(contextDinamico)
                .setTitle("AVISO")
                .setMessage("TEMPO PARA RESPONDER EXCEDIDO")
                .setPositiveButton("CONTINUAR RESPONDENDO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: call your log out method
                        cronometroChamarDialog();

                    }
                })
                .setNegativeButton("FINALIZA EM ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: call method to extend session
                        chamarTelaFinalAgradecimento(contextDinamico);

                    }

                })
                .create();

        DialogTimeoutListener listener = new DialogTimeoutListener();
        dialog.setOnShowListener(listener);
        dialog.setOnDismissListener(listener);
        dialog.show();

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        
    }

    /*public static boolean timeOut() {
        final boolean[] stop = {false};
        dialog = new AlertDialog.Builder(contextDinamico)
                .setTitle("AVISO")
                .setMessage("TEMPO PARA RESPONDER EXCEDIDO")
                .setPositiveButton("CONTINUAR RESPONDENDO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: call your log out method
                        stop[0] = false;

                    }
                })
                .setNegativeButton("FINALIZA EM ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: call method to extend session
                        stop[0] = true;

                    }

                })
                .create();

        DialogTimeoutListener listener = new DialogTimeoutListener();
        dialog.setOnShowListener(listener);
        dialog.setOnDismissListener(listener);
        dialog.show();
        return stop[0];
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
            mCountDownTimer.cancel();
        }*/

}