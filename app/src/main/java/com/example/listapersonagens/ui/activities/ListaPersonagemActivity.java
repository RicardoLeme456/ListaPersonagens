package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    //Mátodo sendo usado somente nessa classe
    private final PersonagemDAO dao = new PersonagemDAO();

    //Criação do Layout
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);

        //Setando o título
        setTitle("Lista de Personagens");
        //Evitar de digitar uma listagem de personagens
        dao.salva(new Personagem("Ken", "02101985", "1,80"));
        dao.salva(new Personagem("Ryu", "02101985", "1,80"));



        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu"));

        //Chamar uma Activity para mandar para uma outra localidade
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });




        /*TextView primeiroPersonagem = findViewById(R.id.textView);
        TextView segundoPersonagem = findViewById(R.id.textView2);
        TextView terceiroPersonagem = findViewById(R.id.textView3);
        primeiroPersonagem.setText(personagem.get(0));
        segundoPersonagem.setText(personagem.get(1));
        terceiroPersonagem.setText(personagem.get(2));*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        List<Personagem> personagens = dao.todos();
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                //Pegar as informações
                Personagem personagemEscolhido = personagens.get(posicao);
                //Fazer uma chamada entre uma listagem e entre um formulário
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                //transacionar as informações que vão para o formulário
                vaiParaFormulario.putExtra("Personagem", personagemEscolhido);
                startActivity(vaiParaFormulario);

            }
        });
    }
}
