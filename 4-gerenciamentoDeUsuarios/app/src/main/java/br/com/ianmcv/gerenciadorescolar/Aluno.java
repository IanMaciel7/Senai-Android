package br.com.ianmcv.gerenciadorescolar;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

class Aluno implements Parcelable  {

    private String nome;
    private String email;
    private int idade;
    private String cpf;

    public Aluno(String nome, String email, int idade , String cpf) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.cpf = cpf;
    }


    protected Aluno(Parcel in) {
        nome = in.readString();
        email = in.readString();
        idade = in.readInt();
        cpf = in.readString();
    }

    public static final Creator<Aluno> CREATOR = new Creator<Aluno>() {
        @Override
        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        @Override
        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Nome:"+getNome()+"| Email: "+getEmail()+"| Idade: "+getIdade() + "| Cpf: "+getCpf();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeInt(idade);
        dest.writeString(cpf);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
