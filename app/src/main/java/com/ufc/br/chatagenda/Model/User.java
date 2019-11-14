package com.ufc.br.chatagenda.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private int id;
    private int numero;
    private String nome;
    private String senha;
    private List<User> amigos;

    public User(int id, String nome, int numero, String senha) {
        this.nome = nome;
        this.id = id;
        this.numero = numero;
        this.senha = senha;
        this.amigos = new ArrayList<User>();
    }

    public User(String nome, int numero, String senha) {
        this.nome = nome;
        this.numero = numero;
        this.senha = senha;
        this.amigos = new ArrayList<User>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void addAmigo(User amigo) {

    }

    public void deleteAmigo(int id) {

    }
}
