package com.ufc.br.chatagenda.Model;

import androidx.annotation.NonNull;

public class Mensagem {

    private String origem;
    private String destino;
    private String hora;
    private String conteudo;

   public Mensagem(){}

    public Mensagem(String origem, String destino, String hora, String conteudo) {
        this.origem = origem;
        this.destino = destino;
        this.hora = hora;
        this.conteudo = conteudo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @NonNull
    @Override
    public String toString() {
        return conteudo;
    }
}
