/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import com.ifpb.MyPersonalAgenda.modelo.Agenda;
import com.ifpb.MyPersonalAgenda.modelo.Compromisso;
import static com.ifpb.MyPersonalAgenda.visao.PaginaInicial.usuarioLogado;
import java.io.IOException;
import static java.rmi.Naming.list;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ThigoYure
 */
public class CompromissoDaoBinario implements CompromissoDao {

    AgendaDaoBinario agendaDao = new AgendaDaoBinario();
    List<Agenda> agendas;

    public CompromissoDaoBinario() throws IOException, ClassNotFoundException {
        agendas = agendaDao.list(usuarioLogado.getEmail());
    }

    @Override
    public Compromisso readCompromissos(LocalDate data, String hora, String agenda) {
        for (Agenda a : agendas) {
            if (a.getNome().equals(agenda)) {
                return a.readCompromisso(data, hora);
            }
        }
        return null;
    }

    @Override
    public List<Compromisso> listCompromissos()throws IOException,ClassNotFoundException {
        List<Compromisso> compromissos = new ArrayList<>();

        for (Agenda a : agendas) {
            compromissos.addAll(a.listCompromisso());
        }

        return compromissos;
    }

    @Override
    public boolean createCompromissos(Compromisso comp)throws IOException,ClassNotFoundException {
        return agendaDao.read(comp.getAgenda()).createCompromisso(comp);
    }

    @Override
    public boolean deleteCompromissos(Compromisso comp)throws IOException,ClassNotFoundException {
        return agendaDao.read(comp.getAgenda()).deleteCompromisso(comp);
    }

    @Override
    public boolean updateCompromissos(Compromisso comp) throws ClassNotFoundException, IOException {
        return agendaDao.read(comp.getAgenda()).updateCompromisso(comp);
    }
    @Override
    public List<Compromisso> listarCompromissosIntervalo(LocalDate inicio, LocalDate fim) throws ClassNotFoundException,  IOException {
        List<Compromisso> compromissosIntervalo = new ArrayList<>();
        List<Compromisso> compromissos = new ArrayList<>();
        
        for(Agenda a: agendas){
            compromissos.addAll(a.listCompromisso());
        }
        for(int i = 0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().isAfter(inicio)&&compromissos.get(i).getData().isBefore(fim)){
                compromissosIntervalo.add(compromissos.get(i));
            }
        }
        return compromissosIntervalo;
    }

}
