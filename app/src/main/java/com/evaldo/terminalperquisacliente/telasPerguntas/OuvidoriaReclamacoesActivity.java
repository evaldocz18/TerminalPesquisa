package com.evaldo.terminalperquisacliente.telasPerguntas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.evaldo.terminalperquisacliente.R;
import com.evaldo.terminalperquisacliente.util.Chronometer;
import com.evaldo.terminalperquisacliente.util.Dialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.dispositivoKiosque;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.perguntasQuestionario;

public class OuvidoriaReclamacoesActivity extends AppCompatActivity {


    private AutoCompleteTextView reclamacao;
    private Button enviarReclamacao;
    private String idReclamacao = UUID.randomUUID().toString();
    private Chronometer chronometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ouvidoria_reclamacoes);

        enviarReclamacao = findViewById(R.id.bt_enviarReclamacao);
        reclamacao = findViewById(R.id.tv_reclamacao);

        verificarUsuarioEstaDigitando();

        chronometer();

        verificarUsuarioEstaDigitando();

    }

    private void verificarUsuarioEstaDigitando() {
        reclamacao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // System.out.println("beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("DIGITOU!!");
                chronometer.reset();


            }

            @Override
            public void afterTextChanged(Editable s) {
                //  System.out.println("afterTextChanged");

            }


        });
    }

    private void chronometer() {
        chronometer = new Chronometer(15000);
        chronometer.setCallback(new Runnable() {
            @Override
            public void run() {
                final Chronometer dialogTimer = new Chronometer(10000);
                dialogTimer.setCallback(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent2 = new Intent(OuvidoriaReclamacoesActivity.this, TelaFinalAgradecimentoActivity.class);
                        OuvidoriaReclamacoesActivity.this.startActivity(intent2);
                    }
                });

                dialogTimer.start();

                final Dialog dialog = new Dialog("Tempo Limite Excedido (Deseja Continuar respondendo?)", OuvidoriaReclamacoesActivity.this);
                dialog.setConfirm(new Runnable() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialogTimer.stop();
                                Intent intent2 = new Intent(OuvidoriaReclamacoesActivity.this, TelaFinalAgradecimentoActivity.class);
                                OuvidoriaReclamacoesActivity.this.startActivity(intent2);
                            }
                        });
                    }
                });

                dialog.setNegation(new Runnable() {
                    @Override
                    public void run() {
                        dialogTimer.stop();
                        chronometer.start();
                    }
                });

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.show();
                    }
                });

            }
        });
        chronometer.start();

    }

    public void clickVoltarReclamacao(View view) {
        chronometer.stop();
        Intent intent = new Intent(this, PerguntaEmotion3DivulgacaoActivity.class);
        finish();
        startActivity(intent);
    }

    public void clickEnviarReclamacao(View view) {

        if(reclamacao.getText().length() >2){

            DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
            DatabaseReference bancoOuvidoriaReferencia = databaseReferencia.child("Banco Ouvidoria");

            bancoOuvidoriaReferencia.child("Reclamacoes").child("id").child(idReclamacao).child("hora").setValue(pegandoHora());
            bancoOuvidoriaReferencia.child("Reclamacoes").child("id").child(idReclamacao).child("administradorResponsavel").setValue(perguntasQuestionario.getAdministradorResponsavel());
            bancoOuvidoriaReferencia.child("Reclamacoes").child("id").child(idReclamacao).child("idDispositivo").setValue(dispositivoKiosque.getIdDispositivo());
            bancoOuvidoriaReferencia.child("Reclamacoes").child("id").child(idReclamacao).child("resposta").setValue(reclamacao.getText().toString());


        }

        chronometer.stop();
        Intent intent = new Intent(this, TelaFinalAgradecimentoActivity.class);
        finish();
        startActivity(intent);
    }

    private String pegandoHora() {
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        Date dataCal = new Date();
        Date dataHora = new Date();
        String dataFormatada = formataData.format(dataCal);
        String horaFormatada = formatHora.format(dataHora);
        String dataEHora = "Data " + dataFormatada + " Hora " + horaFormatada ;
        //System.out.println("Data " + dataFormatada + " Hora " + horaFormatada );

        return dataEHora;
    }

}