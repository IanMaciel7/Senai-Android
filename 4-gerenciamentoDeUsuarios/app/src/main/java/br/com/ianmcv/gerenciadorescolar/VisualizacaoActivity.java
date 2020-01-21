package br.com.ianmcv.gerenciadorescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import static br.com.ianmcv.gerenciadorescolar.MainActivity.ACTIVITY_MAIN_RESPONSE;
import static br.com.ianmcv.gerenciadorescolar.MainActivity.TAG_PRINCIPAL;


public class VisualizacaoActivity extends AppCompatActivity {

    List<Aluno> listaAlunos;
    ListView listaAlunos_ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao);

        listaAlunos_ListView = findViewById(R.id.listaAlunos_ListView);


        Bundle data = getIntent().getExtras();

        listaAlunos = data.getParcelableArrayList(ACTIVITY_MAIN_RESPONSE);
        Log.i(TAG_PRINCIPAL,"lista de alunos pega,tamanho: "+listaAlunos.size());



       CustomAdapter customAdapter = new CustomAdapter();
       listaAlunos_ListView.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listaAlunos.size() ;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if( convertView == null ){
                convertView = getLayoutInflater().inflate(R.layout.list_alunos, parent, false);
            }

            ImageView foto_imageView =convertView.findViewById(R.id.visualizacao_foto_imageView);
            TextView nome_textView = convertView.findViewById(R.id.visualizacao_nome_textView);
            TextView idade_textView = convertView.findViewById(R.id.visualizacao_idade_textView);
            TextView email_textView = convertView.findViewById(R.id.visualizacao_email_textView);
            TextView cpf_textView = convertView.findViewById(R.id.visualizacao_cpf_textView);

            //foto_imageView.setImageResource(listaAlunos.get(position).getImagem());
            nome_textView.setText(listaAlunos.get(position).getNome());
            idade_textView.setText(String.valueOf(listaAlunos.get(position).getIdade()));
            email_textView.setText(listaAlunos.get(position).getEmail());
            cpf_textView.setText(String.valueOf(listaAlunos.get(position).getCpf()));

            return convertView;
        }
    }


}
