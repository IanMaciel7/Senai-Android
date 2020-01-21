package br.com.ianmcv.gerenciadorescolar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.ianmcv.gerenciadorescolar.CadastroActivity.ACTIVITY_CADASTRO_RESPONSE;
import static br.com.ianmcv.gerenciadorescolar.EdicaoActivity.ACTIVITY_EDICAO_RESPONSE;


public class MainActivity extends AppCompatActivity {


    //TAGS
    public static final String TAG_PRINCIPAL = "principal";

    //startActivity
    public static final String ACTIVITY_MAIN_RESPONSE = "ACTIVITY_MAIN_RESPONSE";


    //startActivityForResult
    private static final int ACTIVITY_CADASTRO_REQUEST=0;
    private static final int ACTIVITY_EDICAO_REQUEST = 1;

    private List<Aluno> listaAlunos;
    private GridLayout homeGridLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaAlunos = new ArrayList<Aluno>(Arrays.asList(new Aluno("marcos","marcos@teste.com",12,"10"),new Aluno("leo","leo@teste.com",13,"12")));
        homeGridLayout = findViewById(R.id.home_gridLayout);
        setSingleEvent(homeGridLayout);


    }



    private void setSingleEvent(GridLayout singleEvent) {

        for (int i = 0; i < homeGridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) homeGridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG_PRINCIPAL, "CardView Clicado,index: " + finalI);
                    if (finalI==0){
                        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                        startActivityForResult(intent,ACTIVITY_CADASTRO_REQUEST);
                    }
                    if (finalI==1){
                        Intent intent = new Intent(MainActivity.this, EdicaoActivity.class);
                        intent.putParcelableArrayListExtra(ACTIVITY_MAIN_RESPONSE,(ArrayList)listaAlunos);
                        startActivityForResult(intent, ACTIVITY_EDICAO_REQUEST);
                    }
                    if (finalI==2){
                        Intent intent = new Intent(MainActivity.this, VisualizacaoActivity.class);
                        intent.putParcelableArrayListExtra(ACTIVITY_MAIN_RESPONSE,(ArrayList)listaAlunos);
                        startActivity(intent);
                    }
                    if (finalI==3){
                        Intent intent = new Intent(MainActivity.this, SiteActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ACTIVITY_CADASTRO_REQUEST) {
            if(resultCode == RESULT_OK){
                Aluno aluno = data.getParcelableExtra(ACTIVITY_CADASTRO_RESPONSE);
                Log.i(TAG_PRINCIPAL,aluno.toString());
                listaAlunos.add(aluno);
            }else{
                Log.i(TAG_PRINCIPAL,"não está proto o resultado da Activity");
            }
        }

        if (requestCode == ACTIVITY_EDICAO_REQUEST) {
            if(resultCode == RESULT_OK){
                listaAlunos = data.getParcelableArrayListExtra(ACTIVITY_EDICAO_RESPONSE);
                Log.i(TAG_PRINCIPAL,"saida edicao "+listaAlunos.get(0).getNome());

            }else{
                Log.i(TAG_PRINCIPAL,"não está proto o resultado da Activity");
            }
        }

    }
}
