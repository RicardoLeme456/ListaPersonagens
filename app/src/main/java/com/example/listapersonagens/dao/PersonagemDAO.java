package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;


//Guardar as informações do Personagem
public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>(); //Criação de Listagens para os respectivos personagens
    private static int contadorDeId = 1;

    //Salvar as informações dentro de uma base de dados
    public void salva(Personagem persoangemSalvo) {

        //Local onde o personagem sera salvo
        persoangemSalvo.setId(contadorDeId);
        personagens.add(persoangemSalvo); //Inputar uma informação
        atualizaId();

    }

    private void atualizaId() {
        contadorDeId++; //Adicionar dentro da listagem
    }

    //Editando as informações
    public void editar(Personagem personagem) {
        Personagem personagemEscolhido = buscaPersonagemId(personagem);

        if (personagemEscolhido != null)
        //posicionamento ideal aonde vai encontrar o objeto
        {
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }

    }

    private Personagem buscaPersonagemId(Personagem personagem) {
        //Usado para caso de uso de listas
        for (Personagem p : personagens) {

            if (p.getId() == personagem.getId()) {
                return p;
            }
        }
        return null;
    }

    //Imprimir essa lista no xml
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);

    }

}
