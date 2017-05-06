/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.modelo;

import com.ifpb.MyPersonalAgenda.excecoes.DataInvalidaException;
import com.ifpb.MyPersonalAgenda.excecoes.EmailInvalidoException;
import com.ifpb.MyPersonalAgenda.excecoes.SenhaInvalidaException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author ThigoYure
 */
public class Usuario implements Serializable{

    private String email;
    private String nome;
    private char sexo;
    private LocalDate nascimento;
    private String senha;

    public Usuario() {
        
    }

    public Usuario(String email, String nome, char sexo, LocalDate nascimento, String senha) {
        if(nascimento.isAfter(LocalDate.now())){
            throw new DataInvalidaException("Data de nascimento inválida!");
        }
        this.email = email;
        this.nome = nome;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailInvalidoException {
        if (email.equals("")) {
            throw new EmailInvalidoException();
        }
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        if(nascimento.isAfter(LocalDate.now())){
            throw new DataInvalidaException("Data de nascimento inválida!");
        }
        this.nascimento = nascimento;
    }
    

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws SenhaInvalidaException {
        if (senha.equals("")) {
            throw new SenhaInvalidaException();
        }
        this.senha = senha;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.email);
        hash = 71 * hash + Objects.hashCode(this.nome);
        hash = 71 * hash + this.sexo;
        hash = 71 * hash + Objects.hashCode(this.nascimento);
        hash = 71 * hash + Objects.hashCode(this.senha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.sexo != other.sexo) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.nascimento, other.nascimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Usuario{" + "email=" + email + ", nome=" + nome + ", sexo=" + sexo + ", nascimento=" + nascimento.format(formater) + ", senha=" + senha + '}';
    }

    public boolean autenticar(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

}
