package com.evaldo.terminalperquisacliente.classes;

import java.io.Serializable;
import java.util.Arrays;

public class TerminalPesquisa implements Serializable {
    String nomeDispositivo,idDispositivo, nomeFuncionario, emailFuncionario, status,key, dataAtivacao, questionarioAtual, imagemDivulgacaoEPergunta[], ultimaVezOnline[], imagemDivulgacao[] ;

    public TerminalPesquisa(String nomeDispositivo, String idDispositivo, String nomeFuncionario, String emailFuncionario, String status, String key, String dataAtivacao, String questionarioAtual, String[] imagemDivulgacaoEPergunta, String[] ultimaVezOnline, String[] imagemDivulgacao) {
        this.nomeDispositivo = nomeDispositivo;
        this.idDispositivo = idDispositivo;
        this.nomeFuncionario = nomeFuncionario;
        this.emailFuncionario = emailFuncionario;
        this.status = status;
        this.key = key;
        this.dataAtivacao = dataAtivacao;
        this.questionarioAtual = questionarioAtual;
        this.imagemDivulgacaoEPergunta = imagemDivulgacaoEPergunta;
        this.ultimaVezOnline = ultimaVezOnline;
        this.imagemDivulgacao = imagemDivulgacao;
    }

    public TerminalPesquisa(){}

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

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getEmailFuncionario() {
        return emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public String[] getImagemDivulgacaoEPergunta() {
        return imagemDivulgacaoEPergunta;
    }

    public void setImagemDivulgacaoEPergunta(String[] imagemDivulgacaoEPergunta) {
        this.imagemDivulgacaoEPergunta = imagemDivulgacaoEPergunta;
    }

    public String[] getUltimaVezOnline() {
        return ultimaVezOnline;
    }

    public void setUltimaVezOnline(String[] ultimaVezOnline) {
        this.ultimaVezOnline = ultimaVezOnline;
    }

    public String[] getImagemDivulgacao() {
        return imagemDivulgacao;
    }

    public void setImagemDivulgacao(String[] imagemDivulgacao) {
        this.imagemDivulgacao = imagemDivulgacao;
    }

    @Override
    public String toString() {
        return "TerminalPesquisa{" +
                "nomeDispositivo='" + nomeDispositivo + '\'' +
                ", idDispositivo='" + idDispositivo + '\'' +
                ", nomeFuncionario='" + nomeFuncionario + '\'' +
                ", emailFuncionario='" + emailFuncionario + '\'' +
                ", status='" + status + '\'' +
                ", key='" + key + '\'' +
                ", dataAtivacao='" + dataAtivacao + '\'' +
                ", questionarioAtual='" + questionarioAtual + '\'' +
                ", imagemDivulgacaoEPergunta=" + Arrays.toString(imagemDivulgacaoEPergunta) +
                ", ultimaVezOnline=" + Arrays.toString(ultimaVezOnline) +
                ", imagemDivulgacao=" + Arrays.toString(imagemDivulgacao) +
                '}';
    }
}
