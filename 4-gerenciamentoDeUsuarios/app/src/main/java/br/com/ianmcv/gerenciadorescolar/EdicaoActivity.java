package br.com.ianmcv.gerenciadorescolar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


import static br.com.ianmcv.gerenciadorescolar.MainActivity.ACTIVITY_MAIN_RESPONSE;
import static br.com.ianmcv.gerenciadorescolar.MainActivity.TAG_PRINCIPAL;

public class EdicaoActivity extends AppCompatActivity {

    public static final String ACTIVITY_EDICAO_RESPONSE = "ACTIVITY_EDICAO_RESPONSE";

    private AutoCompleteTextView buscarCpfAutoCompleteTextView;
    private EditText nomeEditText;
    private EditText idadeEditText;
    private EditText emailEditText;
    private EditText cpfEditText;

    private Button nomeButton;
    private Button idadeButton;
    private Button emailButton;
    private Button cpfButton;
    private Button excluirButton;

    private List<Aluno> listaAlunos;
    private List listaAlunosCpf;

    private Intent intent;
    private AlertDialog.Builder alertDialog;
    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao);

        buscarCpfAutoCompleteTextView = findViewById(R.id.edicao_buscarCpf_autoCompleteTextView);
        nomeEditText = findViewById(R.id.edicao_nome_editText);
        idadeEditText = findViewById(R.id.edicao_idade_editText);
        emailEditText = findViewById(R.id.edicao_email_editText);
        cpfEditText = findViewById(R.id.edicao_cpf_editText);

        nomeButton = findViewById(R.id.edicao_nome_button);
        idadeButton = findViewById(R.id.edicao_idade_button);
        emailButton = findViewById(R.id.edicao_email_button);
        cpfButton = findViewById(R.id.edicao_cpf_button);
        excluirButton = findViewById(R.id.edicao_excluir_button);


        Bundle data = getIntent().getExtras();
        listaAlunos = data.getParcelableArrayList(ACTIVITY_MAIN_RESPONSE);
        listaAlunosCpf = new ArrayList<String>();

        for (Aluno alunoDaVez : listaAlunos) {
            listaAlunosCpf.add((alunoDaVez.getCpf()));
            Log.i(TAG_PRINCIPAL, "aluno adicionado na lista cpf " + alunoDaVez.getCpf());
        }

        //colocando os cpf dentro do autoComplete
        buscarCpfAutoCompleteTextView.setAdapter(new ArrayAdapter<String>(EdicaoActivity.this, android.R.layout.simple_list_item_1, listaAlunosCpf));


        buscarCpfAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.i(TAG_PRINCIPAL, "campo de busca cpf foi alterado,número atual do campo é " + s.toString());

                for (final Aluno aluno : listaAlunos) {
                    if (aluno.getCpf().equals(s.toString())) {

                        nomeEditText.setText(aluno.getNome());
                        idadeEditText.setText(String.valueOf(aluno.getIdade()));
                        emailEditText.setText(aluno.getEmail());
                        cpfEditText.setText(aluno.getCpf());

                        nomeButton.setEnabled(true);
                        idadeButton.setEnabled(true);
                        emailButton.setEnabled(true);
                        cpfButton.setEnabled(true);
                        excluirButton.setEnabled(true);

                        nomeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                exibirDialogNome(aluno);
                            }
                        });

                        idadeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                exibirDialogIdade(aluno);
                            }
                        });

                        emailButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                exibirDialogEmail(aluno);
                            }
                        });

                        cpfButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                exibirDialogCpf(aluno);
                            }
                        });

                        excluirButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                exibirDialogExcluir(aluno);
                            }
                        });

                        break;
                    } else {
                        nomeEditText.setText("");
                        idadeEditText.setText("");
                        emailEditText.setText("");
                        cpfEditText.setText("");

                        nomeButton.setEnabled(false);
                        idadeButton.setEnabled(false);
                        emailButton.setEnabled(false);
                        cpfButton.setEnabled(false);
                        excluirButton.setEnabled(false);

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void exibirDialogExcluir(final Aluno aluno) {
        alertDialog = new AlertDialog.Builder(EdicaoActivity.this);
        inflater = getLayoutInflater();
        alertDialog.setTitle("Excluir aluno").setMessage("Tem certeza que deseja excluir");
        alertDialog.setPositiveButton("excluir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int index = buscarIdAlunoNaLista(aluno,listaAlunos);
                listaAlunos.remove(index);
                intent = new Intent(EdicaoActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra(ACTIVITY_EDICAO_RESPONSE,(ArrayList)listaAlunos);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        alertDialog.setNegativeButton("fechar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // fechar
            }
        });
        alertDialog.show();
    }

    private void exibirDialogCpf(final Aluno aluno) {
        alertDialog = new AlertDialog.Builder(EdicaoActivity.this);
        inflater = getLayoutInflater();
        final View dialogCpf = inflater.inflate(R.layout.dialog_edicao_cpf, null);
        alertDialog.setView(dialogCpf).setTitle("Editar CPF");
        alertDialog.setPositiveButton("salvar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // salvar novo cpf
                EditText editText= dialogCpf.findViewById(R.id.dialog_edicao_cpf_editText);
                String str = editText.getText().toString();
                Log.i(TAG_PRINCIPAL,"str: "+str);

                int index = buscarIdAlunoNaLista(aluno,listaAlunos);
                listaAlunos.get(index).setCpf(str);
                intent = new Intent(EdicaoActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra(ACTIVITY_EDICAO_RESPONSE,(ArrayList)listaAlunos);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
        alertDialog.setNegativeButton("fechar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // fechar
            }
        });
        alertDialog.show();
    }

    private void exibirDialogEmail(final Aluno aluno) {
        alertDialog = new AlertDialog.Builder(EdicaoActivity.this);
        inflater = getLayoutInflater();
        final View dialogEmail = inflater.inflate(R.layout.dialog_edicao_email, null);
        alertDialog.setView(dialogEmail).setTitle("Editar email");
        alertDialog.setPositiveButton("salvar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                EditText editText= dialogEmail.findViewById(R.id.dialog_edicao_email_editText);
                String str = editText.getText().toString();
                Log.i(TAG_PRINCIPAL,"str: "+str);

                int index = buscarIdAlunoNaLista(aluno,listaAlunos);
                listaAlunos.get(index).setEmail(str);
                intent = new Intent(EdicaoActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra(ACTIVITY_EDICAO_RESPONSE,(ArrayList)listaAlunos);
                Log.i(TAG_PRINCIPAL, "nome entrada edicao " + listaAlunos.get(0).getNome());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        alertDialog.setNegativeButton("fechar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // fechar
            }
        });
        alertDialog.show();
    }

    private void exibirDialogIdade(final Aluno aluno) {
        alertDialog = new AlertDialog.Builder(EdicaoActivity.this);
        inflater = getLayoutInflater();
        final View dialogIdade =inflater.inflate(R.layout.dialog_edicao_idade, null);
        alertDialog.setView(dialogIdade).setTitle("Editar idade");
        alertDialog.setPositiveButton("salvar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // salvar novo nome
                EditText editText= dialogIdade.findViewById(R.id.dialog_edicao_idade_editText);
                String str = editText.getText().toString();
                Log.i(TAG_PRINCIPAL,"str: "+str);

                int index = buscarIdAlunoNaLista(aluno,listaAlunos);
                listaAlunos.get(index).setIdade((Integer.valueOf(str)));
                intent = new Intent(EdicaoActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra(ACTIVITY_EDICAO_RESPONSE,(ArrayList)listaAlunos);
                Log.i(TAG_PRINCIPAL, "nome entrada edicao " + listaAlunos.get(0).getNome());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        alertDialog.setNegativeButton("fechar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // fechar
            }
        });
        alertDialog.show();
    }

    private void exibirDialogNome(final Aluno aluno) {
        alertDialog = new AlertDialog.Builder(EdicaoActivity.this);
        inflater = getLayoutInflater();
        final View dialogNome = inflater.inflate(R.layout.dialog_edicao_nome, null);
        alertDialog.setView(dialogNome).setTitle("Editar nome");
        alertDialog.setPositiveButton("salvar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // salvar novo nome
                EditText editText= dialogNome.findViewById(R.id.dialog_edicao_nome_editText);
                String str = editText.getText().toString();
                Log.i(TAG_PRINCIPAL,"str: "+str);

                int index = buscarIdAlunoNaLista(aluno,listaAlunos);
                listaAlunos.get(index).setNome(str);
                intent = new Intent(EdicaoActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra(ACTIVITY_EDICAO_RESPONSE,(ArrayList)listaAlunos);

                setResult(RESULT_OK, intent);
                finish();

            }
        });
        alertDialog.setNegativeButton("fechar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // fechar
            }
        });
        alertDialog.show();

    }

    public int buscarIdAlunoNaLista(Aluno alunoParaComparacao,List<Aluno> lista){
        for (int i=0;i<lista.size();i++){
            if(alunoParaComparacao.getCpf().equals(lista.get(i).getCpf())){
                return i;
            }
        }

        return -1;
    }

}


