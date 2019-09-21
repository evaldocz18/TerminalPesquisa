package com.evaldo.terminalperquisacliente.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.bd";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table perguntasQuestionario (id integer primary key autoincrement, " +
                "nomeQuestionario varchar(100), " +
                "idDispositivo varchar (100)," +
                "hora varchar(50)," +
                "administradorResponsavel varchar(50),"+
                "pergunta1 varchar(1000),"+
                "pergunta2 varchar(1000),"+
                "pergunta3 varchar(1000),"+
                "pergunta4 varchar(1000),"+
                "pergunta5 varchar(1000),"+
                "pergunta6 varchar(1000),"+
                "pergunta7 varchar(1000),"+
                "pergunta8 varchar(1000),"+
                "pergunta9 varchar(1000),"+
                "pergunta10 varchar(1000),"+
                "resposta1 varchar(1000),"+
                "resposta2 varchar(1000),"+
                "resposta3 varchar(1000),"+
                "resposta4 varchar(1000),"+
                "resposta5 varchar(1000),"+
                "resposta6 varchar(1000),"+
                "resposta7 varchar(1000),"+
                "resposta8 varchar(1000),"+
                "resposta9 varchar(1000),"+
                "resposta10 varchar(1000),"+
                "contPerguntas integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
