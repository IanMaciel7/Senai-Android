package br.com.ianmcv.cadastroproduto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.ianmcv.cadastroproduto.model.Produto;

public class SomaActivity extends AppCompatActivity {

    public static final String ACTIVITY_MAIN_REQUEST  = "soma";
    private TextView somaTotal_textView;
    private ListView produtosSimplificado_listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soma);

        somaTotal_textView = findViewById(R.id.somaTotal_textview);
        produtosSimplificado_listView = findViewById(R.id.produtosSimplificado_listView);

        Bundle data = getIntent().getExtras();
        List<Produto> listProdutos = data.getParcelableArrayList(ACTIVITY_MAIN_REQUEST);
        List<String> produtosSimplificados = new ArrayList<>();

        int total =0;
        for (Produto produto : listProdutos){
            total += produto.getValorUnitario();
            produtosSimplificados.add("nome: "+produto.getNome()+"\n"+"valor: "+produto.getValorUnitarioFormatado());
        }
        somaTotal_textView.setText(String.valueOf(total));
        produtosSimplificado_listView.setAdapter(new ArrayAdapter(SomaActivity.this,android.R.layout.simple_list_item_1,produtosSimplificados));

    }







}
