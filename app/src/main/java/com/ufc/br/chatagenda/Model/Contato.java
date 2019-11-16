package com.ufc.br.chatagenda.Model;

public class Contato {

    private String id;
    private String numero;
    private String nome;
    private String email;

    public Contato(){}

    public Contato( String id, String nome, String numero, String email ){
        this.nome = nome;
        this.id = id;
        this.numero = numero;
        this.email = email;
    }

    public Contato( String nome, String numero, String email ) {
        this.nome = nome;
        this.numero = numero;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
