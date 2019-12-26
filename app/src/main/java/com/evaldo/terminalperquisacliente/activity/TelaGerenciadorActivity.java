package com.evaldo.terminalperquisacliente.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.evaldo.terminalperquisacliente.R;
import com.evaldo.terminalperquisacliente.classes.TerminalPesquisa;
import com.evaldo.terminalperquisacliente.util.PerguntasDAO;
import com.evaldo.terminalperquisacliente.classes.PerguntasQuestionario;
import com.evaldo.terminalperquisacliente.telasPerguntas.PerguntaAbertaActivity;
import com.evaldo.terminalperquisacliente.telasPerguntas.PerguntaPrimeiraEmotion3DivulgacaoActivity;
import com.evaldo.terminalperquisacliente.telasPerguntas.PerguntaEmotion6Activity;
import com.evaldo.terminalperquisacliente.telasPerguntas.PerguntaSimOuNaoActivity;
import com.evaldo.terminalperquisacliente.telasPerguntas.PerguntaTelefoneOuEmailActivity;
import com.evaldo.terminalperquisacliente.telasPerguntas.TelaFinalAgradecimentoActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.evaldo.terminalperquisacliente.activity.PrincipalActivity.chamarPrimeiraTelaUmVez;
import static com.evaldo.terminalperquisacliente.activity.PrincipalActivity.pularTela;


public class TelaGerenciadorActivity extends AppCompatActivity {
    //Iniciando o firebase
    private DatabaseReference referencePerguntas, referenceTerminal;
    private ProgressBar progressBar;
    private Drawable progressDrawable;

    //Variaveis uteis para todas as classes das telas
    public static String perguntaAtual = "Pergunta atual não recebeu dado", tipoRespostaAtual = "Tipo resposta atual não recebeu dado";
    public static boolean chamarProximaPergunta;
    public static Context contextDinamico, contextAnterior;

    //Variaveis contendo o conteudo de perguntas e respostas (como se fosse carregando o objeto PerguntasQuestionario)
    public static int contPerguntas;
    boolean conexaoAtiva;


    //Quem deve setar o questionario atual é o administrador AINDA FALTA FAZER!
    public static String questionarioAtual;
    public static PerguntasQuestionario perguntasQuestionario;
    public static TerminalPesquisa terminalPesquisa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_gerenciador);

        progressBar = findViewById(R.id.progress_bar);

        verificarInternet();


        if (conexaoAtiva) {
            firebaseLocalizarTerminal();

        } else if (!conexaoAtiva && perguntasQuestionario != null){


            chamarPrimeiraTela(TelaGerenciadorActivity.this);

            iniciarGerenciamento(TelaGerenciadorActivity.this);
        }



    }

    private void verificarInternet() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();


            if (ni != null && ni.isConnected()) {

                System.out.println("Tem internet!!!!!");

                conexaoAtiva = true;
            } else {
                conexaoAtiva = false;
            }

        }
    }


    public static void iniciarGerenciamento(Context context) {

        chamarPrimeiraTelaUmVez = true;

        verificarLimitePergunta();

        decidirNumeroDaPerguntaETipoRespostasEChamarTelas();

        contextDinamico = context;
        decidirTipoDeTela(contextDinamico);


        // pausarTelaGerenciador();

    }

    private void firebaseLocalizarTerminal() {

        referenceTerminal = FirebaseDatabase.getInstance().getReference().child("Terminais de Pesquisa").child("id");
        referenceTerminal.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    System.out.println("--------------------data.child(idDispositivo)= " + data.child("idDispositivo") );
                    System.out.println("---------------------equals(pegarIDDispositivo())= " + equals(pegarIDDispositivo()));
                    if (data.child("idDispositivo").getValue().equals(pegarIDDispositivo())) {

                        terminalPesquisa = data.getValue(TerminalPesquisa.class);
                        //if (terminalPesquisa.getStatus(""))
                        questionarioAtual = terminalPesquisa.getQuestionarioAtual();

                        firebaseLocalizarQuestionario();

                        break;

                    } else {

                        //System.out.println("Não achou o dispositivo " + data.child("id").getValue().equals(pegarIDDispositivo()));

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TelaGerenciadorActivity.this, "Erro no firebase", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void chamarPrimeiraTela(Context context) {
       ///Essa sempre será a primeira tela pois criar atributos como key hora para o questionário
        //System.out.println("chamarPrimeiraTela");
        if (chamarPrimeiraTelaUmVez) {
            perguntaAtual = perguntasQuestionario.getPergunta1();
            System.out.println("Chamando a primeira tela obrigatoria tela 3 emotion");

            chamarPrimeiraTelaUmVez = false;
            Intent intent1 = new Intent(context, PerguntaPrimeiraEmotion3DivulgacaoActivity.class);

            context.startActivity(intent1);
            //finish();

        } else {
            System.out.println("Primeira tela já foi chamada");
        }

    }

    public boolean salvarBanco() {

        if (perguntasQuestionario != null) {

            PerguntasDAO dao;
            dao = new PerguntasDAO(this);
            dao.apagarBanco();
            long id = dao.inserir(perguntasQuestionario);
           // Toast.makeText(this, "Salvo no banco de dados = " + id, Toast.LENGTH_SHORT).show();

            return true;

        } else {

            return false;

        }

    }

    public static boolean verificarLimitePergunta() {
        System.out.println("---------Verificar Limite Perguntas--------------");
        System.out.println("pularTela =" + pularTela);
        contPerguntas = perguntasQuestionario.getContPerguntas();
        System.out.println("contPerguntas = " + contPerguntas);

        if (pularTela <= contPerguntas) {

            chamarProximaPergunta = true;

            System.out.println("verificarLimitePergunta() = " + chamarProximaPergunta);


        } else if (pularTela > contPerguntas) {

            chamarProximaPergunta = false;
            tipoRespostaAtual = "TelaFinal";
            System.out.println("verificarLimitePergunta() = " + chamarProximaPergunta);

            //chamarTelaFinal();


        }
        return chamarProximaPergunta;
    }

    public static void decidirNumeroDaPerguntaETipoRespostasEChamarTelas() {


        System.out.println("decidirNumeroDaPerguntaETipoRespostasEChamarTelas()");
        if (chamarProximaPergunta) {
            System.out.println("if chamarProximaPergunta");
            System.out.println("pularTela = " + pularTela);
            switch (pularTela) {

                case 2:

                    System.out.println("case 2");

                    perguntaAtual = perguntasQuestionario.getPergunta2();

                    tipoRespostaAtual = perguntasQuestionario.getResposta2();


                    break;

                case 3:
                    System.out.println("case 3");

                    perguntaAtual = perguntasQuestionario.getPergunta3();


                    tipoRespostaAtual = perguntasQuestionario.getResposta3();


                    break;

                case 4:
                    System.out.println("case 4");
                    perguntaAtual = perguntasQuestionario.getPergunta4();

                    tipoRespostaAtual = perguntasQuestionario.getResposta4();


                    break;

                case 5:
                    System.out.println("case 5");
                    perguntaAtual = perguntasQuestionario.getPergunta5();

                    tipoRespostaAtual = perguntasQuestionario.getResposta5();


                    break;

                case 6:
                    System.out.println("case 6");
                    perguntaAtual = perguntasQuestionario.getPergunta6();

                    tipoRespostaAtual = perguntasQuestionario.getResposta6();


                    break;

                case 7:
                    System.out.println("case 7");
                    perguntaAtual = perguntasQuestionario.getPergunta7();

                    tipoRespostaAtual = perguntasQuestionario.getResposta7();

                    break;

                case 8:
                    System.out.println("case 8");
                    perguntaAtual = perguntasQuestionario.getPergunta8();

                    tipoRespostaAtual = perguntasQuestionario.getResposta8();


                    break;

                case 9:
                    System.out.println("case 9");
                    perguntaAtual = perguntasQuestionario.getPergunta9();

                    tipoRespostaAtual = perguntasQuestionario.getResposta9();


                    break;

                case 10:
                    System.out.println("case 10");
                    perguntaAtual = perguntasQuestionario.getPergunta10();

                    tipoRespostaAtual = perguntasQuestionario.getResposta10();


                    break;
                default:
            }
        }
    }

    public static void chamarTelaFinalAgradecimento(Context context) {
        System.out.println("chamarTelaFinalAgradecimento()");
        Intent intent2 = new Intent(context, TelaFinalAgradecimentoActivity.class);
        context.startActivity(intent2);
    }

    private void firebaseLocalizarQuestionario() {


        referencePerguntas = FirebaseDatabase.getInstance().getReference().child("Banco Perguntas Questionario").child("id");
        referencePerguntas.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.child("nomeQuestionario").getValue().equals(questionarioAtual)) {

                        perguntasQuestionario = data.getValue(PerguntasQuestionario.class);


                        salvarBanco();

                        chamarPrimeiraTela(TelaGerenciadorActivity.this);

                        iniciarGerenciamento(TelaGerenciadorActivity.this);


                    } else {


                    }
                }
                if (perguntasQuestionario == null) {
                    //chamarPrimeiraTela(TelaGerenciadorActivity.this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TelaGerenciadorActivity.this, "Erro no firebase", Toast.LENGTH_LONG).show();
            }
        });

    }

    public static void decidirTipoDeTela(Context context) {
        System.out.println("decidirTipoDeTela");

        Intent intent3emotion = new Intent(context, PerguntaPrimeiraEmotion3DivulgacaoActivity.class);
        Intent intent6emotion = new Intent(context, PerguntaEmotion6Activity.class);
        Intent intentSimOuNao = new Intent(context, PerguntaSimOuNaoActivity.class);
        Intent intentAberta = new Intent(context, PerguntaAbertaActivity.class);
        Intent intentTelefoneOuEmail = new Intent(context, PerguntaTelefoneOuEmailActivity.class);
        Intent intentFinalAgradecimento = new Intent(context, TelaFinalAgradecimentoActivity.class);


        if (tipoRespostaAtual.equals("Princial 3 Emotions e Ouvidoria")) {

            System.out.println("if 3emotion" + tipoRespostaAtual);

            context.startActivity(intent3emotion);

        } else if (tipoRespostaAtual.equals("6 Emotions")) {

            System.out.println("if 6emotion" + tipoRespostaAtual);

            context.startActivity(intent6emotion);

        } else if (tipoRespostaAtual.equals("(Sim) ou (Não)")) {

            System.out.println("if SimOuNao" + tipoRespostaAtual);

            context.startActivity(intentSimOuNao);

        } else if (tipoRespostaAtual.equals("Resposta Aberta")) {

            System.out.println("if Aberta" + tipoRespostaAtual);

            context.startActivity(intentAberta);

        } else if (tipoRespostaAtual.equals("Telefone e Email")) {

            System.out.println("if TelefoneOuEmail" + tipoRespostaAtual);

            context.startActivity(intentTelefoneOuEmail);

        } else if (tipoRespostaAtual.equals("TelaFinal")) {
            context.startActivity(intentFinalAgradecimento);
        }


    }

    private String pegarIDDispositivo() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Você não pode sair dessa tela!", Toast.LENGTH_LONG).show();

    }

}
