package com.evaldo.terminalperquisacliente.classes;


import java.io.Serializable;

public class PerguntasQuestionario implements Serializable {
    public String key, idDispositivo, hora, nomeQuestionario, administradorResponsavel,
            pergunta1, pergunta2, pergunta3, pergunta4, pergunta5, pergunta6, pergunta7, pergunta8, pergunta9, pergunta10,
            resposta1, resposta2, resposta3, resposta4, resposta5, resposta6, resposta7, resposta8, resposta9, resposta10;
    public int contPerguntas;

    public PerguntasQuestionario(String idDispositivo, String hora, String nomeQuestionario, String administradorResponsavel, String pergunta1, String pergunta2, String pergunta3, String pergunta4, String pergunta5, String pergunta6, String pergunta7, String pergunta8, String pergunta9, String pergunta10, String resposta1, String resposta2, String resposta3, String resposta4, String resposta5, String resposta6, String resposta7, String resposta8, String resposta9, String resposta10, int contPerguntas) {
        this.idDispositivo = idDispositivo;
        this.hora = hora;
        this.nomeQuestionario = nomeQuestionario;
        this.administradorResponsavel = administradorResponsavel;
        this.pergunta1 = pergunta1;
        this.pergunta2 = pergunta2;
        this.pergunta3 = pergunta3;
        this.pergunta4 = pergunta4;
        this.pergunta5 = pergunta5;
        this.pergunta6 = pergunta6;
        this.pergunta7 = pergunta7;
        this.pergunta8 = pergunta8;
        this.pergunta9 = pergunta9;
        this.pergunta10 = pergunta10;
        this.resposta1 = resposta1;
        this.resposta2 = resposta2;
        this.resposta3 = resposta3;
        this.resposta4 = resposta4;
        this.resposta5 = resposta5;
        this.resposta6 = resposta6;
        this.resposta7 = resposta7;
        this.resposta8 = resposta8;
        this.resposta9 = resposta9;
        this.resposta10 = resposta10;
        this.contPerguntas = contPerguntas;
    }

    public PerguntasQuestionario() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNomeQuestionario() {
        return nomeQuestionario;
    }

    public void setNomeQuestionario(String nomeQuestionario) {
        this.nomeQuestionario = nomeQuestionario;
    }

    public String getAdministradorResponsavel() {
        return administradorResponsavel;
    }

    public void setAdministradorResponsavel(String administradorResponsavel) {
        this.administradorResponsavel = administradorResponsavel;
    }

    public String getResposta1() {
        return resposta1;
    }

    public void setResposta1(String resposta1) {
        this.resposta1 = resposta1;
    }

    public String getPergunta1() {
        return pergunta1;
    }

    public void setPergunta1(String pergunta1) {
        this.pergunta1 = pergunta1;
    }

    public String getResposta2() {
        return resposta2;
    }

    public void setResposta2(String resposta2) {
        this.resposta2 = resposta2;
    }

    public String getPergunta2() {
        return pergunta2;
    }

    public void setPergunta2(String pergunta2) {
        this.pergunta2 = pergunta2;
    }

    public int getContPerguntas() {
        return contPerguntas;
    }

    public void setContPerguntas(int contPerguntas) {
        this.contPerguntas = contPerguntas;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getPergunta3() {
        return pergunta3;
    }

    public void setPergunta3(String pergunta3) {
        this.pergunta3 = pergunta3;
    }

    public String getPergunta4() {
        return pergunta4;
    }

    public void setPergunta4(String pergunta4) {
        this.pergunta4 = pergunta4;
    }

    public String getPergunta5() {
        return pergunta5;
    }

    public void setPergunta5(String pergunta5) {
        this.pergunta5 = pergunta5;
    }

    public String getPergunta6() {
        return pergunta6;
    }

    public void setPergunta6(String pergunta6) {
        this.pergunta6 = pergunta6;
    }

    public String getPergunta7() {
        return pergunta7;
    }

    public void setPergunta7(String pergunta7) {
        this.pergunta7 = pergunta7;
    }

    public String getPergunta8() {
        return pergunta8;
    }

    public void setPergunta8(String pergunta8) {
        this.pergunta8 = pergunta8;
    }

    public String getPergunta9() {
        return pergunta9;
    }

    public void setPergunta9(String pergunta9) {
        this.pergunta9 = pergunta9;
    }

    public String getPergunta10() {
        return pergunta10;
    }

    public void setPergunta10(String pergunta10) {
        this.pergunta10 = pergunta10;
    }

    public String getResposta3() {
        return resposta3;
    }

    public void setResposta3(String resposta3) {
        this.resposta3 = resposta3;
    }

    public String getResposta4() {
        return resposta4;
    }

    public void setResposta4(String resposta4) {
        this.resposta4 = resposta4;
    }

    public String getResposta5() {
        return resposta5;
    }

    public void setResposta5(String resposta5) {
        this.resposta5 = resposta5;
    }

    public String getResposta6() {
        return resposta6;
    }

    public void setResposta6(String resposta6) {
        this.resposta6 = resposta6;
    }

    public String getResposta7() {
        return resposta7;
    }

    public void setResposta7(String resposta7) {
        this.resposta7 = resposta7;
    }

    public String getResposta8() {
        return resposta8;
    }

    public void setResposta8(String resposta8) {
        this.resposta8 = resposta8;
    }

    public String getResposta9() {
        return resposta9;
    }

    public void setResposta9(String resposta9) {
        this.resposta9 = resposta9;
    }

    public String getResposta10() {
        return resposta10;
    }

    public void setResposta10(String resposta10) {
        this.resposta10 = resposta10;
    }

    @Override
    public String toString() {
        return "PerguntasQuestionario{" +
                "key='" + key + '\'' +
                ", idDispositivo='" + idDispositivo + '\'' +
                ", hora='" + hora + '\'' +
                ", nomeQuestionario='" + nomeQuestionario + '\'' +
                ", administradorResponsavel='" + administradorResponsavel + '\'' +
                ", pergunta1='" + pergunta1 + '\'' +
                ", pergunta2='" + pergunta2 + '\'' +
                ", pergunta3='" + pergunta3 + '\'' +
                ", pergunta4='" + pergunta4 + '\'' +
                ", pergunta5='" + pergunta5 + '\'' +
                ", pergunta6='" + pergunta6 + '\'' +
                ", pergunta7='" + pergunta7 + '\'' +
                ", pergunta8='" + pergunta8 + '\'' +
                ", pergunta9='" + pergunta9 + '\'' +
                ", pergunta10='" + pergunta10 + '\'' +
                ", resposta1='" + resposta1 + '\'' +
                ", resposta2='" + resposta2 + '\'' +
                ", resposta3='" + resposta3 + '\'' +
                ", resposta4='" + resposta4 + '\'' +
                ", resposta5='" + resposta5 + '\'' +
                ", resposta6='" + resposta6 + '\'' +
                ", resposta7='" + resposta7 + '\'' +
                ", resposta8='" + resposta8 + '\'' +
                ", resposta9='" + resposta9 + '\'' +
                ", resposta10='" + resposta10 + '\'' +
                ", contPerguntas=" + contPerguntas +
                '}';
    }
}


