package com.evaldo.terminalperquisacliente.telasPerguntas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.evaldo.terminalperquisacliente.R;

public class PerguntaPrimeiraNPSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira_nps);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onclickReclamacoes(View view) {
        Intent intent = new Intent(this, OuvidoriaReclamacoesActivity.class);
        startActivity(intent);

    }

    public void onclickSugestoes(View view) {
        Intent intent = new Intent(this, OuvidoriaSugestoesActivity.class);
        startActivity(intent);
    }

    public void onclickElogios(View view) {
        Intent intent = new Intent(this, OuvidoriaElogiosActivity.class);
        startActivity(intent);
    }
}
