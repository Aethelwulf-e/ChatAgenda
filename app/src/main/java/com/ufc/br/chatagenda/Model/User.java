package com.ufc.br.chatagenda.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String id;
    private String numero;
    private String nome;
    private String email;
    private String senha;
    private List<Contato> contatos;

    public User(){}

    public User(String id, String nome, String numero, String email, String senha) {
        this.nome = nome;
        this.id = id;
        this.numero = numero;
        this.email = email;
        this.senha = senha;
        this.contatos = new ArrayList<Contato>();
    }

    public User(String nome, String numero, String email, String senha) {
        this.nome = nome;
        this.numero = numero;
        this.senha = senha;
        this.email = email;
        this.contatos = new ArrayList<Contato>();
    }

    public User(String nome, String numero, String email, String senha, ArrayList<Contato> contatos) {
        this.nome = nome;
        this.numero = numero;
        this.senha = senha;
        this.email = email;
        this.contatos = contatos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
