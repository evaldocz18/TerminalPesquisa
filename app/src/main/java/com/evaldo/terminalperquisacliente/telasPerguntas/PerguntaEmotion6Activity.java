package com.evaldo.terminalperquisacliente.telasPerguntas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.evaldo.terminalperquisacliente.R;
import com.evaldo.terminalperquisacliente.activity.MainActivity;
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
import static com.evaldo.terminalperquisacliente.util.DialogTimeoutListener.cronometroChamarDialog;

public class PerguntaEmotion6Activity extends AppCompatActivity {

    private ImageView exelente, bom, razoavel, ruim, pessimo;

    private TextView tvPergunta;

    String pergunta = perguntaAtual;
    DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference bancoRespostasQuestionarioReferencia = databaseReferencia.child("Banco Respostas Questionário");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta_emotion6);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        System.out.println("Tela 6emotion pularTela = " + pularTela);

        exelente = findViewById(R.id.iv_excelente6);
        bom = findViewById(R.id.iv_bom6);
        razoavel = findViewById(R.id.iv_razoavel6);
        ruim = findViewById(R.id.iv_ruim6);
        pessimo = findViewById(R.id.iv_pessimo6);
        tvPergunta = findViewById(R.id.tv_pergunta_6emotion);
        tvPergunta.setText(pergunta);

        if (verificarQuestionarioCarregado()) {
            contextDinamico = this;
            cronometroChamarDialog();

        }

    }

    /////Modo Full Screnn Inicio
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
    /////Modo Full Screnn Fim

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


    private void chamarProximaTela() {

        //interagiu = true;
        //dialog.dismiss();

        cronometro.cancel();

        pularTela++;

        verificarLimitePergunta();

        decidirNumeroDaPerguntaETipoRespostasEChamarTelas();

        contextAnterior = contextDinamico;

        decidirTipoDeTela(contextDinamico);
    }

    private void enviarQuestionario(String pergunta, String resposta) {


        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("pergunta" + pularTela).setValue(pergunta);
        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("resposta" + pularTela).setValue(resposta);
    }

    public void clickExelente6Emotion(View view) {

        System.out.println("clickouExcelente");

        enviarQuestionario(pularTela + "." + pergunta, "(Exelente)");

        chamarProximaTela();
    }

    public void clickBom6Emotion(View view) {
        enviarQuestionario(pularTela + "." + pergunta, "(Bom)");

        chamarProximaTela();
    }

    public void clickRazoavel6Emotion(View view) {
        enviarQuestionario(pularTela + "." + pergunta, "(Razoável)");

        chamarProximaTela();
    }

    public void clickRuim6Emotion(View view) {
        enviarQuestionario(pularTela + "." + pergunta, "(Ruim)");

        chamarProximaTela();
    }

    public void clickPessimo6Emotion(View view) {
        enviarQuestionario(pularTela + "." + pergunta, "(Péssimo)");

        chamarProximaTela();
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



    /* @Override
     public boolean onCreateOptionsMenu(Menu menu) {

         getMenuInflater().inflate(R.menu.menu_cliente, menu);

         return super.onCreateOptionsMenu(menu);
     }

     //////////////////////////MENU CARREGANDO//////////////////////////////////////////////////////////////////////////////////////

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {

         switch (item.getItemId()) {

             case R.id.item_voltar:

                 Toast.makeText(this, "Voltar", Toast.LENGTH_LONG).show();

                 //pularTela--;

                 finish();

                 return true;

             case R.id.item_avancar:

                 Toast.makeText(this, "Avançar", Toast.LENGTH_LONG).show();

                 enviarQuestionario(pergunta, "(Não respondeu esta pergunta)");

                 chamarProximaTela();

                 return true;

         }
         return super.onOptionsItemSelected(item);
     }*/

}


