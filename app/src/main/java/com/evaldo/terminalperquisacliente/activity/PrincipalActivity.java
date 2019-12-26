package com.evaldo.terminalperquisacliente.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.evaldo.terminalperquisacliente.R;
import com.evaldo.terminalperquisacliente.util.PerguntasDAO;
import com.evaldo.terminalperquisacliente.classes.PerguntasQuestionario;

import java.util.Timer;
import java.util.TimerTask;

import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.chamarPrimeiraTela;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.iniciarGerenciamento;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.perguntasQuestionario;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.questionarioAtual;

public class PrincipalActivity extends AppCompatActivity {

    public static int pularTela = 1;
    public static boolean chamarPrimeiraTelaUmVez = true;

    boolean  conexao;
    Timer timer;
    boolean verificarConaxaoUmaVez = true;
    PerguntasDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        System.out.println("PrincipalActivity");

        chamarGerenciadorTelas();

        verificarConexaoInternetAtiva();



    }

    private boolean recuperandoUltimoQuestionarioSQLite() {

        dao = new PerguntasDAO(this);
        PerguntasQuestionario perbanco = null;
        perbanco = dao.obterQuestionarioBanco();
        perguntasQuestionario = perbanco;

        if (perbanco == null) {

            Toast.makeText(PrincipalActivity.this, "DISPOSITIVO NÃO POSSUI DADOS ARMAZENADOS (É NECESSARIO CONEXÃO COM A INTERNET)", Toast.LENGTH_LONG).show();

            return false;

        } else {
            return true;
        }
    }

    private boolean verificarConexaoInternetAtiva() {

        final ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (cm != null) {
                            NetworkInfo ni = cm.getActiveNetworkInfo();


                            if (ni != null && ni.isConnected()) {

                                System.out.println("Tem internet!!!!!");
                                if (chamarPrimeiraTelaUmVez) {
                                    //Toast.makeText(PrincipalActivity.this, "TOTEM CONECTADO COM A INTERNET", Toast.LENGTH_LONG).show();
                                }
                                conexao = true;

                            } else {

                                System.out.println("Não tem internet!!!!!");

                                if (verificarConaxaoUmaVez) {

                                    verificarConaxaoUmaVez = false;
                                    Toast.makeText(PrincipalActivity.this, "SEM CONEXÂO COM A INTERNET (ARMAZENANDO NO DISPOSITIVO)", Toast.LENGTH_LONG).show();

                                    recuperandoUltimoQuestionarioSQLite();

                                    questionarioAtual = perguntasQuestionario.getNomeQuestionario();

                                    if (recuperandoUltimoQuestionarioSQLite()) {

                                        iniciarGerenciamento(PrincipalActivity.this);

                                        chamarPrimeiraTela(PrincipalActivity.this);

                                    }
                                }

                                conexao = false;

                            }

                        }

                    }
                });
            }
        }, 10000, 100000);

        return conexao;
    }

    private void chamarGerenciadorTelas() {
        Intent intent = new Intent(this, TelaGerenciadorActivity.class);
        onPause();
        startActivity(intent);
    }


}
