package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

//Listagem dos Personagens
public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Personagens";
    //Mátodo sendo usado somente nessa classe
    private final PersonagemDAO dao = new PersonagemDAO();

    //Buscar a Superclasse que esta na IDE
    @Override
    //Buscar as informações da Superclasse
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Criação da Activity
        setContentView(R.layout.activity_lista_personagem); //Setar um contexto para abrir uma view, tipo o lugar ou a posição especificada que ela vai estar
        //Definir o titulo associado a esta atividade no cabeçalho
        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();


    }

    private void configuraFabNovoPersonagem() {
        //Chamar uma Activity para mandar para uma outra localidade
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add); //Fazer funcionar algo ao apertar e então identificar o floating Action Button pelo fab
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() { //Dar uma chamada
            //Ao clicar ele ativa o Start Activity
            @Override
            public void onClick(View v) {
                abreFormulario();
            }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() { //Ele garante as informações do nosso projeto
        super.onResume();

        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem); //Exibe uma visualização verticalmmente onde cada visualização é exibido abaixo do anterior
        final List<Personagem> personagens = dao.todos(); //Cria uma listagem ordenada e salva os dados
        listaDePersonagens(listaDePersonagens, personagens);
        configuraItemPorClique(listaDePersonagens);
    }

    private void configuraItemPorClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                //Pegar as informações
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                //Fazer uma chamada entre uma listagem e entre um formulário
                abreFormularioEditar(personagemEscolhido);
            }
        });
    }

    private void abreFormularioEditar(Personagem personagemEscolhido) {
        Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        //transacionar as informações que vão para o formulário
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(vaiParaFormulario);
    }

    private void listaDePersonagens(ListView listaDePersonagens, List<Personagem> personagens) {
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens)); //Define os dados por trás da ListView
    }
}
