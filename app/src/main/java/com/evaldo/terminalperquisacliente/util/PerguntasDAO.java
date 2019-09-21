package com.evaldo.terminalperquisacliente.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.evaldo.terminalperquisacliente.classes.PerguntasQuestionario;
import com.evaldo.terminalperquisacliente.util.Conexao;

public class PerguntasDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public  PerguntasDAO(Context context){

        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();



    }

    public void apagarBanco() {
        String sql = "DROP TABLE IF EXISTS perguntasQuestionario";
        banco.execSQL(sql);
        conexao.onCreate(banco);
    }




    public PerguntasQuestionario obterQuestionarioBanco(){

        PerguntasQuestionario perguntasQuestionarioBanco = null;

        Cursor cursor = banco.query("perguntasQuestionario", new String[]{"idDispositivo","hora","nomeQuestionario","administradorResponsavel",
                        "pergunta1","pergunta2","pergunta3","pergunta4","pergunta5","pergunta6","pergunta7","pergunta8","pergunta9","pergunta10",
                        "resposta1","resposta2","resposta3","resposta4","resposta5","resposta6","resposta7","resposta8","resposta9","resposta10","contPerguntas"},
                null, null,null,null,null);

        while(cursor.moveToNext()){
            perguntasQuestionarioBanco = new PerguntasQuestionario();
            perguntasQuestionarioBanco.setIdDispositivo(cursor.getString(0));
            perguntasQuestionarioBanco.setHora(cursor.getString(1));
            perguntasQuestionarioBanco.setNomeQuestionario(cursor.getString(2));
            perguntasQuestionarioBanco.setAdministradorResponsavel(cursor.getString(3));
            perguntasQuestionarioBanco.setPergunta1(cursor.getString(4));
            perguntasQuestionarioBanco.setPergunta2(cursor.getString(5));
            perguntasQuestionarioBanco.setPergunta3(cursor.getString(6));
            perguntasQuestionarioBanco.setPergunta4(cursor.getString(7));
            perguntasQuestionarioBanco.setPergunta5(cursor.getString(8));
            perguntasQuestionarioBanco.setPergunta6(cursor.getString(9));
            perguntasQuestionarioBanco.setPergunta7(cursor.getString(10));
            perguntasQuestionarioBanco.setPergunta8(cursor.getString(11));
            perguntasQuestionarioBanco.setPergunta9(cursor.getString(12));
            perguntasQuestionarioBanco.setPergunta10(cursor.getString(13));
            perguntasQuestionarioBanco.setResposta1(cursor.getString(14));
            perguntasQuestionarioBanco.setResposta2(cursor.getString(15));
            perguntasQuestionarioBanco.setResposta3(cursor.getString(16));
            perguntasQuestionarioBanco.setResposta4(cursor.getString(17));
            perguntasQuestionarioBanco.setResposta5(cursor.getString(18));
            perguntasQuestionarioBanco.setResposta6(cursor.getString(19));
            perguntasQuestionarioBanco.setResposta7(cursor.getString(20));
            perguntasQuestionarioBanco.setResposta8(cursor.getString(21));
            perguntasQuestionarioBanco.setResposta9(cursor.getString(22));
            perguntasQuestionarioBanco.setResposta10(cursor.getString(23));
            perguntasQuestionarioBanco.setContPerguntas(cursor.getInt(24));
        }
        return perguntasQuestionarioBanco;
    }

    public long inserir(PerguntasQuestionario perguntasQuestionario){

        ContentValues values = new ContentValues();

        values.put("idDispositivo", perguntasQuestionario.getIdDispositivo());
        values.put("hora", perguntasQuestionario.getHora());
        values.put("nomeQuestionario", perguntasQuestionario.getNomeQuestionario());
        values.put("administradorResponsavel", perguntasQuestionario.getAdministradorResponsavel());
        values.put("pergunta1", perguntasQuestionario.getPergunta1());
        values.put("pergunta2", perguntasQuestionario.getPergunta2());
        values.put("pergunta3", perguntasQuestionario.getPergunta3());
        values.put("pergunta4", perguntasQuestionario.getPergunta4());
        values.put("pergunta5", perguntasQuestionario.getPergunta5());
        values.put("pergunta6", perguntasQuestionario.getPergunta6());
        values.put("pergunta7", perguntasQuestionario.getPergunta7());
        values.put("pergunta8", perguntasQuestionario.getPergunta8());
        values.put("pergunta9", perguntasQuestionario.getPergunta9());
        values.put("pergunta10", perguntasQuestionario.getPergunta10());
        values.put("resposta1", perguntasQuestionario.getResposta1());
        values.put("resposta2", perguntasQuestionario.getResposta2());
        values.put("resposta3", perguntasQuestionario.getResposta3());
        values.put("resposta4", perguntasQuestionario.getResposta4());
        values.put("resposta5", perguntasQuestionario.getResposta5());
        values.put("resposta6", perguntasQuestionario.getResposta6());
        values.put("resposta7", perguntasQuestionario.getResposta7());
        values.put("resposta8", perguntasQuestionario.getResposta8());
        values.put("resposta9", perguntasQuestionario.getResposta9());
        values.put("resposta10", perguntasQuestionario.getResposta10());
        values.put("contPerguntas", perguntasQuestionario.getContPerguntas());

        return banco.insert("perguntasQuestionario",null,values);


    }

}
