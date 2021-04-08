package com.example.listapersonagens.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;


    public Personagem(String nome, String nascimento, String altura) {

        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    //Pegando uma string
    public String getNome() {
        return nome;
    }

    //Editando uma string
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    @NonNull
    @Override
    public  String toString(){
        return  nome;
    }

  //Definir um local ou posição especificada
    public void setId(int id){

        this.id = id;
    }

    //Receber um local ou posição especificada
    public int getId(){

        return id;
    }

}
