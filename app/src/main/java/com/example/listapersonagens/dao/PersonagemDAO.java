package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;
import com.example.listapersonagens.ui.activities.ListaPersonagemActivity;

import java.util.ArrayList;
import java.util.List; //Importação da lista



public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>(); //Criação de Listagens para os respectivos personagens
    private static int contadorDeId = 1;

    //Peguei as informações
    public void salva(Personagem persoangemSalvo) {

        //Local onde o personagem sera salvo
        persoangemSalvo.setId(contadorDeId);
        personagens.add(persoangemSalvo); //Inputar uma informação
        contadorDeId++;

    }

    public void editar(Personagem personagem){
        Personagem personagemEscolhido = null;
        //Usado para caso de uso de listas
        for (Personagem p: personagens){

            if(p.getId() == personagem.getId()){
                personagemEscolhido = p;
            }
        }

        if(personagemEscolhido != null)
            //posicionamento ideal aonde vai encontrar o objeto
        {
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }

    }

    //Imprimir essa lista no xml
    public List<Personagem> todos(){
       return new ArrayList<>(personagens);

    }

}
