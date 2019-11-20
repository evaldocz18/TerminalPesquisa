package com.evaldo.terminalperquisacliente.telasPerguntas;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.evaldo.terminalperquisacliente.R;
import com.evaldo.terminalperquisacliente.activity.MainActivity;
import com.evaldo.terminalperquisacliente.classes.TerminalPesquisa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.evaldo.terminalperquisacliente.activity.PrincipalActivity.pularTela;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.contPerguntas;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.contextDinamico;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.decidirNumeroDaPerguntaETipoRespostasEChamarTelas;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.decidirTipoDeTela;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.perguntaAtual;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.perguntasQuestionario;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.questionarioAtual;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.terminalPesquisa;
import static com.evaldo.terminalperquisacliente.activity.TelaGerenciadorActivity.verificarLimitePergunta;

public class PerguntaPrimeiraEmotion3DivulgacaoActivity extends AppCompatActivity {


    private ImageView exelente, razoavel, ruim, divulgacaoDinamica;

    private TextView tvPergunta;

    private String pergunta = perguntaAtual;

    private DatabaseReference reference, referenceKiosque;

    //static TerminalPesquisa terminalPesquisa = new TerminalPesquisa("Dispositivo teste","idteste", "ATIVADO","Data 20-11-2019 Hora 13:31:56", questionarioAtual );

    DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference bancoRespostasQuestionarioReferencia = databaseReferencia.child("Banco Respostas Questionário");

    public static String idRespostaQuestionario;

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_emotion3_divulgacao);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if (verificarQuestionarioCarregado()) {

            carregandoInterface();
            contextDinamico = this;

        }

        //download_imagem();
    }

    private void modeFullScreen() {
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

    private void download_imagem() {


        StorageReference reference = storage.getReference().child("imagem").child("master.jpeg");

        reference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                if (task.isSuccessful()) {

                    url = task.getResult().toString();

                    download_gif_url(url);

                }
            }
        });
    }

    private void download_gif_url(String url) {


        Glide.with(this).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                // progressBar.setVisibility(View.GONE);

                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                //  progressBar.setVisibility(View.GONE);

                return false;
            }
        }).into(divulgacaoDinamica);
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


    private void carregandoInterface() {
        exelente = (ImageView) findViewById(R.id.iv_excelente);
        razoavel = (ImageView) findViewById(R.id.iv_razoavel);
        ruim = (ImageView) findViewById(R.id.iv_ruim);
        divulgacaoDinamica = (ImageView) findViewById(R.id.iv_divulgacao);

        tvPergunta = findViewById(R.id.tv_pergunta3emotion);
        tvPergunta.setText(pergunta);
    }

    private void chamarProximaTela() {
        pularTela = 2;

        contextDinamico = PerguntaPrimeiraEmotion3DivulgacaoActivity.this;

        verificarLimitePergunta();

        decidirNumeroDaPerguntaETipoRespostasEChamarTelas();

        onPause();

        decidirTipoDeTela(contextDinamico);
    }

    public void clickExelente3Emotion(View view) {
        gerarIDRespostasQuestionario();

        enviarPrimeiraRespostaQuestionario(pularTela + "." + pergunta, "(Exelente)");

        chamarProximaTela();


    }

    public void clickRazoavel3Emotion(View view) {
        onPause();
        gerarIDRespostasQuestionario();

        enviarPrimeiraRespostaQuestionario(pularTela + "." + pergunta, "(Razoável)");

        chamarProximaTela();
    }

    public void clickRuim3Emotion(View view) {

        gerarIDRespostasQuestionario();

        enviarPrimeiraRespostaQuestionario(pularTela + "." + pergunta, "(Ruim)");

        chamarProximaTela();
    }

    private void enviarPrimeiraRespostaQuestionario(String pergunta, String resposta) {

        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("hora").setValue(pegandoHora());
        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("administradorResponsavel").setValue(perguntasQuestionario.getAdministradorResponsavel());
        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("qtdPerguntas").setValue(contPerguntas);
        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("nomeDispositivo").setValue(terminalPesquisa.getNomeDispositivo());
        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("idDispositivo").setValue(terminalPesquisa.getIdDispositivo());
        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("nomeQuestionario").setValue(questionarioAtual);
        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("pergunta" + pularTela).setValue(pergunta);
        bancoRespostasQuestionarioReferencia.child("id").child(idRespostaQuestionario).child("resposta" + pularTela).setValue(resposta);
        //pularTela+=1;
    }

    private void gerarIDRespostasQuestionario() {
        idRespostaQuestionario = UUID.randomUUID().toString();
    }

    private String pegandoHora() {
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        Date dataCal = new Date();
        Date dataHora = new Date();
        String dataFormatada = formataData.format(dataCal);
        String horaFormatada = formatHora.format(dataHora);
        String dataEHora = "Data " + dataFormatada + " Hora " + horaFormatada;
        //System.out.println("Data " + dataFormatada + " Hora " + horaFormatada );

        return dataEHora;
    }

    private String pegarIDDispositivo() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Você não pode sair dessa tela!", Toast.LENGTH_LONG).show();
        System.out.println("Não pode volta!");
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

     /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_cliente_configuracao, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //////////////////////////MENU CARREGANDO//////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

           case R.id.item_voltar:

               Toast.makeText(this, "VOCÊ JÁ ESTA NA PRIMEIRA PERGUNTA!", Toast.LENGTH_LONG).show();

               return true;

            case R.id.item_avancar:
                gerarIDRespostasQuestionario();

                Toast.makeText(this, "Avançar", Toast.LENGTH_LONG).show();

                enviarPrimeiraRespostaQuestionario(pergunta,"(Não respondeu esta pergunta)");

                onPause();

                chamarProximaTela();

                return true;

            case R.id.item_configuracoes:

                Toast.makeText(this, "Confirgurações", Toast.LENGTH_LONG).show();

                return true;

        }
        return super.onOptionsItemSelected(item);
    }*/
}
