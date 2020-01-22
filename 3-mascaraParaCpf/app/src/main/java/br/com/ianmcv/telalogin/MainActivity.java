package br.com.ianmcv.telalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usuario_editText;
    EditText senha_editText;
    Button botaoLogin_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario_editText = findViewById(R.id.usuario_editText);
        senha_editText = findViewById(R.id.senha_editText);
        botaoLogin_Button = findViewById(R.id.botaoLogin_button);

        botaoLogin_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("login","USUARIO: "+usuario_editText.getText().toString());
                Log.i("login","SENHA: "+senha_editText.getText().toString());
                if (usuario_editText.getText().toString().equals("senai") & senha_editText.getText().toString().equals("senai")){
                    Intent intentCadastro = new Intent(MainActivity.this,CadastroActivity.class);
                    startActivity(intentCadastro);
                }else{
                    Toast.makeText(MainActivity.this,"Ops! login ou senha incorreta", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
