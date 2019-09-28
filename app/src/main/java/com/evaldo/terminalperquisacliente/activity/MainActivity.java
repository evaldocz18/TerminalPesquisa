package com.evaldo.terminalperquisacliente.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.evaldo.terminalperquisacliente.R;
import com.evaldo.terminalperquisacliente.telasPerguntas.PerguntaPrimeiraEmotion3DivulgacaoActivity;
import com.evaldo.terminalperquisacliente.telasPerguntas.PerguntaPrimeiraNPSActivity;

import java.security.Principal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chamarTela();

    }
    private void chamarTela() {
        Intent intent = new Intent(this, PrincipalActivity.class);
        onStop();
        startActivity(intent);
    }

}
