package com.example.listapersonagens.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
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
    private final PersonagemDAO dao = new PersonagemDAO(); //Instanciar a classe personagem dao para a lista de personagens
    private ArrayAdapter<Personagem> adapter; //Criar um adapter array da classe Personagem

    //Buscar a Superclasse que esta na IDE
    @Override
    //Buscar as informações da Superclasse
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Criação da Activity
        setContentView(R.layout.activity_lista_personagem); //Setar um contexto para abrir uma view, tipo o lugar ou a posição especificada que ela vai estar
        //Definir o titulo associado a esta atividade no cabeçalho
        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();
        configuraLista();
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
        atualizaPersonagem();
    }

    private void atualizaPersonagem() {
        adapter.clear();  //Limpa a lista de personagns
        adapter.addAll(dao.todos());  //Adiciona todas as listas de personagens
    }

    //As listas que serão removidos do seu item
    private void remove(Personagem personagem) {
        dao.remove(personagem); //Remove as listas do
        adapter.remove(personagem);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagem_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return configuraMenu(item);
    }

    private boolean configuraMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_personagem_menu_remover) {
            new AlertDialog.Builder(this)
                    .setTitle("Removendo Personagem")
                    .setMessage("Deseja remover o personagem?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); //Pegar as informações do menu de contexto
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position); //O item a ser pego para ser removido
                            remove(personagemEscolhido); //Remove os item a qual selecionou
                        }
                    })
                    .setNegativeButton("Não", null) //Se não, não faz nada
                    .show(); //Mostra a interface
        }
        return super.onContextItemSelected(item);
    }

    private void configuraLista() {
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem); //Exibe uma visualização verticalmmente onde cada visualização é exibido abaixo do anterior
        listaDePersonagens(listaDePersonagens);
        configuraItemPorClique(listaDePersonagens); //Clique da Lista de Personagens
        registerForContextMenu(listaDePersonagens); //Registra o contexto da Lista de Personagens
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

    private void listaDePersonagens(ListView listaDePersonagens) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter); //Define os dados por trás da ListView
    }
}
