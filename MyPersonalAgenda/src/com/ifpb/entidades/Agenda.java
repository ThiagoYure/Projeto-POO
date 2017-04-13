/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.entidades;

import com.ifpb.controle.CompromissoDao;
import com.ifpb.controle.Dao;
import com.ifpb.controle.GerenciaDados;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Essa classe contém dados e métodos referentes a agenda e o CRUD de compromisso
 * @author ThigoYure
 */
public class Agenda implements CompromissoDao{
    private String nomeAgenda;
    private List<Compromisso> compromissos;
/**
 * Construtor da agenda
 * @param nomeAgenda o nome da agenda
 */
    public Agenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
        compromissos = new ArrayList<>();
    }

    public String getNomeAgenda() {
        return nomeAgenda;
    }

    public void setNomeAgenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nomeAgenda);
        hash = 59 * hash + Objects.hashCode(this.compromissos);
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
        if (!Objects.equals(this.nomeAgenda, other.nomeAgenda)) {
            return false;
        }
        if (!Objects.equals(this.compromissos, other.compromissos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agenda{" + "nomeAgenda=" + nomeAgenda + ", compromissos=" + compromissos + '}';
    }
/**
 * Adciona um compromisso na lista de compromissos da agenda
 * @param comp compromisso a ser inserido
 * @return a confirmação de inserção ou não
 */
    @Override
    public boolean create(Compromisso comp) {
        for(int i=0;i<compromissos.size();i++){
            if(compromissos.get(i).getDataCompromisso().equals(comp.getDataCompromisso())&&compromissos.get(i).getHoraCompromisso().equals(comp.getHoraCompromisso())){
                return false;
            }
        } 
        if(comp.getDataCompromisso()==null){
            return false;
        }
        return compromissos.add(comp);
    }
/**
 * Lê um compromisso da lista de compromissos da agenda baseado em uma data e hora específicas
 * @param dataCompromisso a data do compromisso a ser lido
 * @param horaCompromisso a hora do compromisso a ser lido
 * @return o compromisso solicitado
 */
    @Override
    public Compromisso read(LocalDate dataCompromisso, LocalTime horaCompromisso) {
        for(int i=0;i<compromissos.size();i++){
            if(compromissos.get(i).getDataCompromisso().equals(dataCompromisso)&&compromissos.get(i).getHoraCompromisso().equals(horaCompromisso)){
                return compromissos.get(i);
            }
        }
        return null;
    }
/**
 * Atualiza um compromisso dentro da lista de compromissos da agenda
 * @param comp o compromisso que substituirá o antigo compromisso
 * @return confirmação de atualização ou não
 */
    @Override
    public boolean update(Compromisso comp) {
        for(int i=0;i<compromissos.size();i++){
            if(compromissos.get(i).getDataCompromisso().equals(comp.getDataCompromisso())&&compromissos.get(i).getHoraCompromisso().equals(comp.getHoraCompromisso())){
                if(comp.getDataCompromisso()==null){
                    return false;
                }
                compromissos.set(i, comp);
                return true;
            }
        } 
        return false;
    }
/**
 * Deleta um compromisso da lista de compromissos da agenda
 * @param comp compromisso a ser deletado
 * @return a confirmação da remoção ou não
 */
    @Override
    public boolean delete(Compromisso comp) {
        return compromissos.remove(comp);
    }
/**
 * Lista todos os compromissos da lista de compromissos da agenda
 * @return a lista de compromissos da agenda
 */    
    public List<Compromisso> listar(){
        return compromissos;
    }
    
}
