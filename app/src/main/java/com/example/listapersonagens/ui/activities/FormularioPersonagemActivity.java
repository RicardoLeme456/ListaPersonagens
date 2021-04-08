package com.example.listapersonagens.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;

import java.io.Serializable;

//Onde ocorrem as transações
public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoNascimento;
    private EditText campoAltura;
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);




        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);

        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem persoangemSalvo = new Personagem(nome, altura, nascimento);

                dao.salva(persoangemSalvo);
                finish();

                //startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));

                //Toast.makeText(FormularioPersonagemActivity.this, persoangemSalvo.getNome() + " - " + persoangemSalvo.getAltura() + " - " + persoangemSalvo.getNascimento(), Toast.LENGTH_SHORT).show();

                //new Personagem(nome, nascimento, altura);

                persoangemSalvo.setNome(nome);
                persoangemSalvo.setAltura(altura);
                persoangemSalvo.setNascimento(nascimento);
                dao.editar(persoangemSalvo);

                //Toast.makeText(FormularioPersonagemActivity.this,"Botão Funcionado", Toast.LENGTH_SHORT).show();

                Intent dados = getIntent();
                //Passar os parâmetros que estou buscando
                Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
                campoNome.setText(personagem.getNome());
                campoNascimento.setText(personagem.getNascimento());
                campoAltura.setText(personagem.getAltura());
            }
        });
    }
}