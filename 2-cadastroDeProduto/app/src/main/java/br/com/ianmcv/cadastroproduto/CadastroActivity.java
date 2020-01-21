package br.com.ianmcv.cadastroproduto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.ContentViewCallback;

import br.com.ianmcv.cadastroproduto.model.Produto;

public class CadastroActivity extends AppCompatActivity {

    static final String ACTIVITY_CADASTRO_RESPONSE = "produto";
    private Button botaoCadastrar;
    private EditText nome;
    private EditText qtd;
    private EditText valorUnitario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        nome = findViewById(R.id.nome);
        qtd  = findViewById(R.id.quantidade);
        valorUnitario = findViewById(R.id.valorUnitario);
        botaoCadastrar = findViewById(R.id.botaoSalvarCadastro);


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nome.getText().toString().isEmpty() && !qtd.getText().toString().isEmpty() && !valorUnitario.getText().toString().isEmpty()){
                    Toast.makeText(CadastroActivity.this, "Botão clicado para salvar", Toast.LENGTH_SHORT).show();

                    String nomeCast = nome.getText().toString();
                    Integer qtdCast = Integer.parseInt(qtd.getText().toString());
                    Double valorUnitarioCast = Double.parseDouble(valorUnitario.getText().toString());

                    Produto produto = new Produto(nomeCast,qtdCast,valorUnitarioCast);
                    Intent intent = new Intent(CadastroActivity.this,MainActivity.class);
                    Log.i("produto_obj","entrada:"+nomeCast);
                    intent.putExtra(ACTIVITY_CADASTRO_RESPONSE,produto);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(CadastroActivity.this, "Por Favor insira os dados nos campos acima", Toast.LENGTH_SHORT).show();
                    Log.i("produto_obj","nulo");
                    return;
                }

            }
        });

    }




}
