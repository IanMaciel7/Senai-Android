package br.com.ianmcv.cadastroproduto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ianmcv.cadastroproduto.model.Produto;

public class MainActivity extends AppCompatActivity {

    private static final String ACTIVITY_SOMA_RESPONSE = "soma";
    private static final int ACTIVITY_CADASTRO_REQUEST = 1;
    private ArrayAdapter arrayAdapter;
    private ListView produtos_listView;
    private FloatingActionButton botao_floatingActionButton;
    private List listProdutos;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        produtos_listView = findViewById(R.id.list_produtos);
        botao_floatingActionButton = findViewById(R.id.botaoCadastrar);


        Produto p1 = new Produto("tv",1,350.00);
        Produto p2 = new Produto("fogão",1,400.00);
        Produto p3 = new Produto("computador",1,700.00);

        listProdutos = new ArrayList<Produto>(Arrays.asList(p1,p2,p3));


        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listProdutos);
        produtos_listView.setAdapter(arrayAdapter);

        botao_floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Botão clicado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),CadastroActivity.class);
                startActivityForResult(intent,1);

            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ACTIVITY_CADASTRO_REQUEST) {
            if(resultCode == RESULT_OK){
                Produto produto = data.getParcelableExtra("produto");
                Log.i("produto_obj","saida:"+produto.getNome());
                listProdutos.add(produto);
                arrayAdapter.notifyDataSetChanged();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.item1:
                Log.i("produto_obj","item clicado");
                Intent intent = new Intent(MainActivity.this,SomaActivity.class);
                intent.putParcelableArrayListExtra(ACTIVITY_SOMA_RESPONSE,(ArrayList)listProdutos);
                startActivity(intent);

                return true;
            default:
        }
        return false;
    }
}
