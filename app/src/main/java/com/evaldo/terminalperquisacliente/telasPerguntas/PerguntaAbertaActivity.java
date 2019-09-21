package com.evaldo.terminalperquisacliente.telasPerguntas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.evaldo.terminalperquisacliente.R;
import com.evaldo.terminalperquisacliente.activity.MainActivity;
import com.evaldo.terminalperquisacliente.util.Chronometer;
import com.evaldo.terminalperquisacliente.util.Dialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.evaldo.terminalperquisacliente.activity.PrincipalActivity.pularTela;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.contextAnterior;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.contextDinamico;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.decidirNumeroDaPerguntaETipoRespostasEChamarTelas;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.decidirTipoDeTela;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.perguntaAtual;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.perguntasQuestionario;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.verificarLimitePergunta;
import static com.evaldo.terminalperquisacliente.telasPerguntas.PerguntaEmotion3DivulgacaoActivity.idRespostaQuestionario;

public class PerguntaAbertaActivity extends AppCompatActivity {

    private TextView tvPerguntaAberta;
    private AutoCompleteTextView ACTVRespostaAberta;

    String pergunta = perguntaAtual;
    String respostaAberta;
    DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference bancoRespostasQuestionarioReferencia = databaseReferencia.child("Banco Respostas Questionário");
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta_aberta);

        tvPerguntaAberta = findViewById(R.id.tv_pertunta_Aberta);
        tvPerguntaAberta.setText(pergunta);

        ACTVRespostaAberta = findViewById(R.id.tv_respostaAberta);

        if (verificarQuestionarioCarregado()) {
            contextDinamico = this;

            chronometer();

            verificarUsuarioEstaDigitando();
        }

    }

    private boolean verificarQuestionarioCarregado() {

        if (perguntasQuestionario == null) {

            Toast.makeText(this, "Um erro inesperado aconteceu estamos reiniciando o aplicativo", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
            return false;
        }else {
            return true;
        }

    }


    private void verificarUsuarioEstaDigitando() {
        ACTVRespostaAberta.addTextChangedListener(new TextWatcher() {
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
                        Intent intent2 = new Intent(PerguntaAbertaActivity.this, TelaFinalAgradecimentoActivity.class);
                        PerguntaAbertaActivity.this.startActivity(intent2);
                    }
                });

                dialogTimer.start();

                final Dialog dialog = new Dialog("Tempo Limite Excedido (Deseja Continuar respondendo?)", PerguntaAbertaActivity.this);
                dialog.setConfirm(new Runnable() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialogTimer.stop();
                                Intent intent2 = new Intent(PerguntaAbertaActivity.this, TelaFinalAgradecimentoActivity.class);
                                PerguntaAbertaActivity.this.startActivity(intent2);
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

    private void chamarProximaTela() {

        chronometer.stop();

        pularTela++;

        verificarLimitePergunta();

        decidirNumeroDaPerguntaETipoRespostasEChamarTelas();

        contextAnterior = contextDinamico;

        decidirTipoDeTela(contextDinamico);
    }


    public void clickComentarioPular(View view) {

        enviarQuestionario(pularTela + "." + pergunta, "(Não deixou comentario)");
        chamarProximaTela();
    }

    public void clickBotaoEnviarComentario(View view) {
        respostaAberta = "(" + respostaAberta + "" + ACTVRespostaAberta.getText() + ")";
        enviarQuestionario(pularTela + "." + pergunta, respostaAberta);
        chamarProximaTela();
    }

    private void enviarQuestionario(String pergunta, String resposta) {


        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("pergunta" + pularTela).setValue(pergunta);
        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("resposta" + pularTela).setValue(resposta);
    }

    /*@Override
    public void onBackPressed() {
        Toast.makeText(this, "VOLTAR", Toast.LENGTH_SHORT).show();

        pularTela--;

        verificarLimitePergunta();

        decidirNumeroDaPerguntaETipoRespostasEChamarTelas();

        decidirTipoDeTela(contextAnterior);

        finish();
    }*/

}
