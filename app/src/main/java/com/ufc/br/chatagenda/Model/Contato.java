package com.ufc.br.chatagenda.Model;

import java.io.Serializable;

public class Contato implements Serializable {

    private String id;
    private String numero;
    private String nome;

    public Contato(){}

    public Contato( String id, String nome, String numero ){
        this.nome = nome;
        this.id = id;
        this.numero = numero;
    }

    public Contato( String nome, String numero ) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
