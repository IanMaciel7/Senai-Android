package br.com.ianmcv.telalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private TextWatcher cpfMask_TextWatcher;
    private EditText cpf_editText;
    private EditText nome_editText;
    private EditText email_editText;
    private Button botao_button;
    private boolean isUpdating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        botao_button = findViewById(R.id.botaoCadastro_button);
        cpf_editText = findViewById(R.id.cpf_editView);
        nome_editText = findViewById(R.id.nome_editView);
        email_editText = findViewById(R.id.email_editView);

        botao_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastroActivity.this,"cadastrado",Toast.LENGTH_LONG).show();
                cpf_editText.setText("");
                nome_editText.setText("");
                email_editText.setText("");
            }
        });

        cpf_editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String textoFiltrado = filtrando(s);

                final boolean numeroEntre0e3 = textoFiltrado.length() <= 3;
                final boolean numeroEntre3e6 = textoFiltrado.length() > 3 & textoFiltrado.length() < 7;
                final boolean numeroEntre6e9 = textoFiltrado.length() > 6 & textoFiltrado.length() < 10;
                final boolean numeroEntre10e12 = textoFiltrado.length() > 9 & textoFiltrado.length() < 12;

                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                if  (numeroEntre0e3){
                    Log.i("cadastro", "IF do número entre 0 e 3");
                    return;
                }else{
                    if  (numeroEntre3e6){
                        Log.i("cadastro", "IF do número entre 3 e 6");
                        textoFiltrado = textoFiltrado.substring(0, 3) + '.' +
                                textoFiltrado.substring(3);
                    }
                    if (numeroEntre6e9){
                        Log.i("cadastro", "IF do número entre 6 e 10");
                        textoFiltrado = textoFiltrado.substring(0, 3) + '.' +
                                textoFiltrado.substring(3, 6) + '.' +
                                textoFiltrado.substring(6);
                    }
                    if (numeroEntre10e12){
                        Log.i("cadastro", "IF do número entre 10 e 12");
                        textoFiltrado = textoFiltrado.substring(0, 3) + '.' +
                                textoFiltrado.substring(3, 6) + '.' +
                                textoFiltrado.substring(6, 9) + '-' +
                                textoFiltrado.substring(9);
                    }
                }

                // Seta a flag para evitar chamada infinita
                isUpdating = true;
                // seta o novo texto
                cpf_editText.setText(textoFiltrado);
                // seta a posição do cursor
                cpf_editText.setSelection(cpf_editText.getText().length());

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    public static String filtrando(CharSequence s) {
        return s.toString().replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }


}
