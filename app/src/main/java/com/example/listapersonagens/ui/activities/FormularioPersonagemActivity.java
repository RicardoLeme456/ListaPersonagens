package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;

//Extender as informações e puxar da Superclasse
public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoNascimento;
    private EditText campoAltura;
    private final PersonagemDAO dao = new PersonagemDAO(); //Criar uma nova classe
    private Personagem Personagem;

    //Buscar a Superclasse que esta na IDE
    @Override
    //Buscar as informações da Superclasse
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Criação da Activity para fazer as ações
        setContentView(R.layout.activity_formulario_personagem); //Setar um contexto para abrir uma view, tipo o lugar ou a posição especificada que ela vai estar
        //Definir o titulo associado a esta atividade no cabeçalho
        setTitle("Formulário de Personagen");
        inicializacaoCampos();
        configuraçãoBotaoAddPersonagem();

        //Puxa as informações e traz de volta
        Intent dados = getIntent();
        if(dados.hasExtra("Personagem")) {
            Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");  //Passar os parâmetros que estou buscando
            campoNome.setText(personagem.getNome());
            campoNascimento.setText(personagem.getNascimento());
            campoAltura.setText(personagem.getAltura());
        }else {

            Personagem = new Personagem();
        }
    }

    private void configuraçãoBotaoAddPersonagem() {
        Button botaoSalvar = findViewById(R.id.button_salvar); //Método usado no lugar do OnClick
        botaoSalvar.setOnClickListener(new View.OnClickListener() { //Faz uma chamaga para fazer um instanciamento
            @Override
            public void onClick(View v) { //Criação da Superclasse
                //Quamdo criar o OnClick ele vai receber as strings
                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                //Armazenar as informações e com isso cria a classe Personagem
                Personagem persoangemSalvo = new Personagem(nome, altura, nascimento);

                //Guardar as informações ou gravar as informações
                dao.salva(persoangemSalvo);
                finish();

                persoangemSalvo.setNome(nome);
                persoangemSalvo.setAltura(altura);
                persoangemSalvo.setNascimento(nascimento);
                dao.editar(persoangemSalvo); //Ele vai salvando as informações
            }
        });
    }

    private void inicializacaoCampos() {
        //Pegando as informações
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);
    }
}