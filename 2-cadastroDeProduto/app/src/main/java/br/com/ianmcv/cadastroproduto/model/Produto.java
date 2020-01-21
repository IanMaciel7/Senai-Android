package br.com.ianmcv.cadastroproduto.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Produto implements Parcelable {

    private String nome;
    private int qtd;
    private double valorUnitario;

    public Produto(String descricao, int qtd, double valorUnitario) {
        this.nome = descricao;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
    }


    @NonNull
    @Override
    public String toString() {
        return " Nome: "+ this.nome +"\n Quantidade: "+ this.qtd +"\n Valor Unitario: " +this.getValorUnitarioFormatado();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValorUnitario() {

        return valorUnitario;
    }

    public String getValorUnitarioFormatado(){
        return String.format("%.2f",this.valorUnitario);
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    protected Produto(Parcel in) {
        nome = in.readString();
        qtd = in.readInt();
        valorUnitario = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(qtd);
        dest.writeDouble(valorUnitario);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Produto> CREATOR = new Parcelable.Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };
}
