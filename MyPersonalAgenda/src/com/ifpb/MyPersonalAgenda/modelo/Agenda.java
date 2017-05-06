/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.modelo;

import com.ifpb.MyPersonalAgenda.controle.CompromissoDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author ThigoYure
 */
public class Agenda implements Serializable {

    private String nome;
    private String emailUser;
    private String descricao;
    private ArrayList<Compromisso> compromissos;

    public Agenda() {
    }
    
    

    public Agenda(String nome, String emailUser,String descricao) {
        this.nome = nome;
        this.emailUser = emailUser;
        this.descricao = descricao;
        this.compromissos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Compromisso> getCompromissos() {
        return compromissos;
    }

    public void setCompromissos(ArrayList<Compromisso> compromissos) {
        this.compromissos = compromissos;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.emailUser);
        hash = 67 * hash + Objects.hashCode(this.descricao);
        hash = 67 * hash + Objects.hashCode(this.compromissos);
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
        final Agenda other = (Agenda) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.emailUser, other.emailUser)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.compromissos, other.compromissos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agenda{" + "nome=" + nome + ", emailUser=" + emailUser + ", descricao=" + descricao + '}';
    }

   
    public Compromisso readCompromisso(LocalDate data, String hora) {
        for(Compromisso c:compromissos){
            if(c.getData().equals(data)&&c.getHora().equals(hora)){
                return c;
            }
        }
        return null;
    }

  
    public List<Compromisso> listCompromisso() {
        return compromissos;
    }


    public boolean createCompromisso(Compromisso comp) {
        return compromissos.add(comp);
    }


    public boolean deleteCompromisso(Compromisso comp) {
        return compromissos.remove(comp);
    }


    public boolean updateCompromisso(Compromisso comp) {
        
        for(int i = 0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(comp.getData())&&compromissos.get(i).getHora().equals(comp.getHora())){
                compromissos.set(i, comp);
                return true;
            }
        }
        return false;
    }
    
    public List<Compromisso> listarCompromissosIntervalo(LocalDate inicio,LocalDate fim){
        List<Compromisso> compromissosIntervalo = new ArrayList<>();
        for(int i = 0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().isAfter(inicio)&&compromissos.get(i).getData().isBefore(fim)){
                compromissosIntervalo.add(compromissos.get(i));
            }
        }
        return compromissosIntervalo;
        
    }
}
