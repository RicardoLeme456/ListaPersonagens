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

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

//Extender as informações e puxar da Superclasse
public class FormularioPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagen";
    public static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagen";
    private EditText campoNome;
    private EditText campoNascimento;
    private EditText campoAltura;
    private final PersonagemDAO dao = new PersonagemDAO(); //Criar uma nova classe
    private Personagem personagem;

    //Buscar a Superclasse que esta na IDE
    @Override
    //Buscar as informações da Superclasse
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Criação da Activity para fazer as ações
        setContentView(R.layout.activity_formulario_personagem); //Setar um contexto para abrir uma view, tipo o lugar ou a posição especificada que ela vai estar
        //Definir o titulo associado a esta atividade no cabeçalho
        inicializacaoCampos();
        configuraçãoBotaoAddPersonagem();
        carregaPersonagem();


    }

    private void configuraçãoBotaoAddPersonagem() {
        Button botaoSalvar = findViewById(R.id.button_salvar); //Método usado no lugar do OnClick
        botaoSalvar.setOnClickListener(new View.OnClickListener() { //Faz uma chamaga para fazer um instanciamento
            @Override
            public void onClick(View v) { //Criação da Superclasse
                finalizarFormulario();
            }
        });
    }

    private void finalizarFormulario() {
        preencherPersonagem();
        //Colocar a Validação
        if (personagem.IdValido()) {
            dao.editar(personagem);
            finish();
        }else {
            dao.salva(personagem);
        }
        finish();
    }

    private void carregaPersonagem() {
        //Puxa as informações e traz de volta
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);  //Passar os parâmetros que estou buscando
            preencherCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    private void preencherCampos() {
        campoNome.setText(personagem.getNome());
        campoNascimento.setText(personagem.getNascimento());
        campoAltura.setText(personagem.getAltura());
    }

    private void inicializacaoCampos() {
        //Pegando as informações
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);
    }

    private void preencherPersonagem() {

        //Quamdo criar o OnClick ele vai receber as strings
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
    }
}