/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.entidades;

import com.ifpb.controle.GerenciaDados;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * Essa classe possui dados e métodos referentes ao compromisso
 * @author ThigoYure
 */
public class Compromisso {
    private LocalDate dataCompromisso;
    private LocalTime horaCompromisso;
    private String descricao;
    private String local;

/**
 * Construtor de compromisso
 * @param dataCompromisso a data do compromisso
 * @param horaCompromisso a hora do compromisso
 * @param descricao a descrição do compromisso
 * @param local o local do compromisso
 */
    public Compromisso(String dataCompromisso, LocalTime horaCompromisso, String descricao, String local) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        formater.setLenient(false);
        try {
           Date data = formater.parse(dataCompromisso);
           if(LocalDate.now().isBefore(GerenciaDados.toLocalDate(data))){
             this.dataCompromisso = GerenciaDados.toLocalDate(formater.parse(dataCompromisso));
           }else{
             System.out.println("Data Inválida");
           }
           this.horaCompromisso = horaCompromisso;
           this.descricao = descricao;
           this.local = local;
        } catch (ParseException ex) {
            System.out.println("Data inválida");
        }
    }

    public LocalDate getDataCompromisso() {
        return dataCompromisso;
    }

    public void setDataCompromisso(String dataCompromisso) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        formater.setLenient(false);
        try {
           Date data = formater.parse(dataCompromisso);
           if(LocalDate.now().isBefore(GerenciaDados.toLocalDate(data))){
             this.dataCompromisso = GerenciaDados.toLocalDate(formater.parse(dataCompromisso));
           }else{
             System.out.println("Data Inválida");
           }
        } catch (ParseException ex) {
           System.out.println("Data Inválida"); 
        }
    }

    public LocalTime getHoraCompromisso() {
        return horaCompromisso;
    }

    public void setHoraCompromisso(LocalTime horaCompromisso) {
        this.horaCompromisso = horaCompromisso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.dataCompromisso);
        hash = 41 * hash + Objects.hashCode(this.horaCompromisso);
        hash = 41 * hash + Objects.hashCode(this.descricao);
        hash = 41 * hash + Objects.hashCode(this.local);
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
        final Compromisso other = (Compromisso) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        if (!Objects.equals(this.dataCompromisso, other.dataCompromisso)) {
            return false;
        }
        if (!Objects.equals(this.horaCompromisso, other.horaCompromisso)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Compromisso{" + "dataCompromisso=" + dataCompromisso + ", horaCompromisso=" + horaCompromisso + ", descricao=" + descricao + ", local=" + local + '}';
    }
    
    
    
}
