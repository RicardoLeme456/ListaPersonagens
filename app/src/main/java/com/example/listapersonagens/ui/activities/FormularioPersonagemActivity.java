package com.example.listapersonagens.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

//Extender as informações e puxar da Superclasse
public class FormularioPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagen"; //O titulo do app
    public static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagen"; //O titulo do app
    private EditText campoNome;        //Declarando os Edit Texts do campo nome, altura e nascimento
    private EditText campoNascimento;
    private EditText campoAltura;
    private final PersonagemDAO dao = new PersonagemDAO(); //Criar uma nova classe
    private Personagem personagem; //Pegando a classe Personagem

    //Criação do menu salvar no formulário
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar,menu); //Pegar os dados editados para ser salvos
        return super.onCreateOptionsMenu(menu); //Retorna os dados fornecidos para a classe
    }

    //Nos respectivos itens selecionados
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId(); //Pegar os itens através dos seus id's
        if(itemId == R.id.activity_formulario_personagem_menu_salvar){ //Se o id do item for aquele informado o método sera chamado
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

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

    //Onde os textos serão colocados
    private void preencherCampos() {
        campoNome.setText(personagem.getNome());
        campoNascimento.setText(personagem.getNascimento());
        campoAltura.setText(personagem.getAltura());
    }

    private void configuraçãoBotaoAddPersonagem() {
        Button botaoSalvar = findViewById(R.id.button_salvar); //Método usado no lugar do OnClick
        botaoSalvar.setOnClickListener(new View.OnClickListener() { //Faz uma chamaga para fazer um instanciamento
            @Override
            public void onClick(View v) { //Criação da Superclasse
                finalizarFormulario(); //Durante o clique finaliza o formulario
            }
        });
    }

    private void finalizarFormulario() {
        preencherPersonagem();
        //Colocar a Validação
        if (personagem.IdValido()) {
            dao.editar(personagem); //Editar o  dao personagem
            finish(); //formulario editar finalizado
        }else {
            dao.salva(personagem); //Salva o dao personagem
        }
        finish(); //Formulario salva finalizado
    }



    private void inicializacaoCampos() {
        //Pegando as informações
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);

        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN"); //Esqueleto da seleção de altura
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, smfAltura); //A forma como vai ser visualizado no seu app
        campoAltura.addTextChangedListener(mtwAltura); //Ao fazer alguma alteração ele sera escutado

        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("N/NN/NNNN"); //Esqueleto da seleção de nascimento
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoNascimento, smfNascimento); //A forma como vai ser visualizado no seu app
        campoNascimento.addTextChangedListener(mtwNascimento); //Ao fazer alguma alteração ele sera escutado
    }

    private void preencherPersonagem() {

        //Quamdo criar o OnClick ele vai receber as strings
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        //Onde os campos serão colocados
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
    }
}