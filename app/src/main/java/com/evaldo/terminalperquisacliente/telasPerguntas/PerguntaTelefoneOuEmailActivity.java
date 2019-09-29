package com.evaldo.terminalperquisacliente.telasPerguntas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import static com.evaldo.terminalperquisacliente.telasPerguntas.PerguntaPrimeiraEmotion3DivulgacaoActivity.idRespostaQuestionario;
import static com.evaldo.terminalperquisacliente.util.DialogTimeoutListener.cronometro;

public class PerguntaTelefoneOuEmailActivity extends AppCompatActivity {

    private TextView tvPerguntaTelefoneOuEmail;
    private Button enviarTelefoneOuEmail, naoEnviarEPular;
    private EditText etTelefone, etEmail;
    private Chronometer chronometer;

    String pergunta = perguntaAtual;
    String telefone, email, telefoneEmail;
    DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference bancoRespostasQuestionarioReferencia = databaseReferencia.child("Banco Respostas Questionário");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta_telefone_ou_email);

        tvPerguntaTelefoneOuEmail = findViewById(R.id.tv_pertunta_TelefoneOuEmail);
        etTelefone = findViewById(R.id.et_telefone);
        etEmail = findViewById(R.id.et_email);
        enviarTelefoneOuEmail = findViewById(R.id.bt_enviar_telefoneOuEmail);
        naoEnviarEPular = findViewById(R.id.bt_enviar_telefoneOuEmail);

        tvPerguntaTelefoneOuEmail.setText(perguntaAtual) ;

        contextDinamico = this;

        if (verificarQuestionarioCarregado()){

            verificarUsuarioEstaDigitando();

            chronometer();
        }




    }
    private boolean verificarQuestionarioCarregado() {

        if (perguntasQuestionario == null) {

            Toast.makeText(this, "Um erro inesperado aconteceu estamos reiniciando o aplicativo", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
            return false;
        } else {
            return true;
        }

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
                        Intent intent2 = new Intent(PerguntaTelefoneOuEmailActivity.this, TelaFinalAgradecimentoActivity.class);
                        PerguntaTelefoneOuEmailActivity.this.startActivity(intent2);
                    }
                });

                dialogTimer.start();

                final Dialog dialog = new Dialog("Tempo Limite Excedido (Deseja Continuar respondendo?)", PerguntaTelefoneOuEmailActivity.this);
                dialog.setConfirm(new Runnable() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialogTimer.stop();
                                Intent intent2 = new Intent(PerguntaTelefoneOuEmailActivity.this, TelaFinalAgradecimentoActivity.class);
                                PerguntaTelefoneOuEmailActivity.this.startActivity(intent2);
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


    private void verificarUsuarioEstaDigitando() {

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("DIGITOU!!");
                chronometer.reset();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etTelefone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("DIGITOU!!");
                chronometer.reset();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }


    private void chamarProximaTela() {

        chronometer.stop();
        //cronometro.cancel();

        pularTela++;

        verificarLimitePergunta();

        decidirNumeroDaPerguntaETipoRespostasEChamarTelas();

        contextAnterior = contextDinamico;

        decidirTipoDeTela(contextDinamico);
    }


    public void clickEnviarTelefoneOuEmail(View view) {

        verificarContato();
        enviarQuestionario(pularTela + "." + pergunta, "(" + telefoneEmail + ")");
        chamarProximaTela();
    }

    public void clickNaoEnviarTelefoneOuEmail(View view) {
        enviarQuestionario(pularTela + "." + pergunta, "(Não se identificou)");
        chamarProximaTela();
    }

    private void verificarContato() {
        telefone = String.valueOf(etTelefone.getText());
        email = String.valueOf(etEmail.getText());
        telefoneEmail ="Telefone: " + telefone + " email: " + email;

        System.out.println(telefoneEmail);
        if (telefone.equals("")) {
            telefoneEmail = "Não se identificou";
        }

    }

    private void enviarQuestionario(String pergunta, String resposta) {


        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("pergunta"+pularTela).setValue(pergunta);
        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("resposta"+pularTela).setValue(resposta);
    }

    @Override
    public void onBackPressed() {
       /* Toast.makeText(this, "VOLTAR", Toast.LENGTH_SHORT).show();

        pularTela--;

        cronometro.cancel();

        verificarLimitePergunta();

        decidirNumeroDaPerguntaETipoRespostasEChamarTelas();

        decidirTipoDeTela(contextAnterior);

        finish();*/
    }
}
