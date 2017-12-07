package com.servico.roomorm;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by João Victor Firmino on 03/12/2017.
 */
@Entity(tableName = "contatos")
public class UserOrm implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nome")
    private String nome;

    @ColumnInfo(name = "sobrenome")
    private String sobrenome;


    @ColumnInfo(name = "telefone")
    private String telefone;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "cpf")
    private String cpf;

    public UserOrm(String nome, String sobrenome, String telefone, String email, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + '\n' +
                "Sobrenome: " + sobrenome + '\n' +
                "Telefone: " + telefone + '\n' +
                "E-mail: " + email + '\n' +
                "CPF: " + cpf ;
    }
}
