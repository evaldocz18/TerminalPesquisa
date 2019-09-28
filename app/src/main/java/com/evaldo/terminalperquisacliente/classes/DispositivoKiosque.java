package com.evaldo.terminalperquisacliente.classes;

import java.io.Serializable;

public class DispositivoKiosque implements Serializable {
    String nomeDispositivo,idDispositivo, status,key, dataAtivacao, questionarioAtual ;

    public DispositivoKiosque(String nomeDispositivo, String idDispositivo, String status, String key, String dataAtivacao, String questionarioAtual) {
        this.nomeDispositivo = nomeDispositivo;
        this.idDispositivo = idDispositivo;
        this.status = status;
        this.key = key;
        this.dataAtivacao = dataAtivacao;
        this.questionarioAtual = questionarioAtual;


    }

    public DispositivoKiosque(){}

    public String getNomeDispositivo() {
        return nomeDispositivo;
    }

    public void setNomeDispositivo(String nomeDispositivo) {
        this.nomeDispositivo = nomeDispositivo;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataAtivacao() {
        return dataAtivacao;
    }

    public void setDataAtivacao(String dataAtivacao) {
        this.dataAtivacao = dataAtivacao;
    }

    public String getQuestionarioAtual() {
        return questionarioAtual;
    }

    public void setQuestionarioAtual(String questionarioAtual) {
        this.questionarioAtual = questionarioAtual;
    }

    @Override
    public String toString() {
        return "DispositivoKiosque{" +
                "nomeDispositivo='" + nomeDispositivo + '\'' +
                ", idDispositivo='" + idDispositivo + '\'' +
                ", status='" + status + '\'' +
                ", key='" + key + '\'' +
                ", dataAtivacao='" + dataAtivacao + '\'' +
                ", questionarioAtual='" + questionarioAtual + '\'' +
                '}';
    }
}
