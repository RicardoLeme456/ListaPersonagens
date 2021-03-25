package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;
import com.example.listapersonagens.ui.activities.ListaPersonagemActivity;

import java.util.ArrayList;
import java.util.List;



public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();

    public void salva(Personagem persoangemSalvo) {

        personagens.add(persoangemSalvo);

    }

    public List<Personagem> todos(){
       return new ArrayList<>(personagens);

    }

}
