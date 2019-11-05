package com.evaldo.terminalperquisacliente.telasPerguntas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.evaldo.terminalperquisacliente.R;
import com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity;

import static com.evaldo.terminalperquisacliente.activity.PrincipalActivity.chamarPrimeiraTelaUmVez;
import static com.evaldo.terminalperquisacliente.activity.PrincipalActivity.pularTela;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.tipoRespostaAtual;

public class TelaFinalAgradecimento2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_final_agradecimento2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        splashScreen();

        ReiniciarContadorDeTelas();


    }

    private void splashScreen() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                chamarGerenciadorTelas();
            }
        }, 7000);

    }

    private void chamarGerenciadorTelas() {
        Intent intent = new Intent(this, TelaGerenciadorActivity.class);
        finish();
        startActivity(intent);
    }


    private void ReiniciarContadorDeTelas() {
        //Iniciar como os mesmo valores da classe PrincipalActivity
        chamarPrimeiraTelaUmVez = true;
        pularTela = 1;
        //Deve ser zerada para receber os novos valores do questionario
        tipoRespostaAtual = "";

    }
}