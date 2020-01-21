package br.com.ianmcv.gerenciadorescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static br.com.ianmcv.gerenciadorescolar.MainActivity.TAG_PRINCIPAL;


public class CadastroActivity extends AppCompatActivity {

    private Button botao;

    public static final String ACTIVITY_CADASTRO_RESPONSE = "ACTIVITY_CADASTRO_RESPONSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        botao = findViewById(R.id.botaoCadastro_button);

        botao.setOnClickListener(new View.OnClickListener() {

            EditText nomeEditText;
            EditText emailEditText;
            EditText idadeEditText;
            EditText cpfEditText;

            String nome;
            String email;
            String idade;
            String cpf;

            @Override
            public void onClick(View v) {

                Log.i(TAG_PRINCIPAL,"botão clicado");

                nomeEditText = findViewById(R.id.nome_editView);
                emailEditText = findViewById(R.id.email_editView);
                idadeEditText = findViewById(R.id.idade_editView);
                cpfEditText = findViewById(R.id.cpf_editView);

                nome = nomeEditText.getText().toString();
                email = emailEditText.getText().toString();
                idade = idadeEditText.getText().toString();
                cpf = cpfEditText.getText().toString();

                if (!(nome.isEmpty() | email.isEmpty() | idade.isEmpty() | cpf.isEmpty()) ) {
                    Log.i(TAG_PRINCIPAL,"objeto sendo criado");
                    Aluno aluno = new Aluno(nome,email,Integer.parseInt(idade),cpf);
                    Intent intent = new Intent(CadastroActivity.this,MainActivity.class);
                    intent.putExtra(ACTIVITY_CADASTRO_RESPONSE,aluno);
                    setResult(RESULT_OK,intent);
                    finish();

                }else {
                    Toast.makeText(CadastroActivity.this,"Ops!,todos os campos precisão ser preenchido",Toast.LENGTH_SHORT).show();
                    Log.i("cadastro","campo em branco");
                }


            }
        });

    }
}
