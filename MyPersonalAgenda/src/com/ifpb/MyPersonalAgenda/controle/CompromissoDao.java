/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import com.ifpb.MyPersonalAgenda.modelo.Agenda;
import com.ifpb.MyPersonalAgenda.modelo.Compromisso;
import com.ifpb.MyPersonalAgenda.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author ThigoYure
 */
public interface CompromissoDao {

    public Compromisso readCompromissos(LocalDate data, String hora, String agenda) throws IOException, ClassNotFoundException,SQLException;

    public List<Compromisso> listCompromissos() throws IOException, ClassNotFoundException,SQLException;

    public boolean createCompromissos(Compromisso comp) throws IOException, ClassNotFoundException,SQLException;

    public boolean deleteCompromissos(Compromisso comp) throws IOException, ClassNotFoundException,SQLException;

    public boolean updateCompromissos(Compromisso comp) throws IOException, ClassNotFoundException,SQLException;

    public List<Compromisso> listarCompromissosIntervalo(LocalDate inicio, LocalDate fim) throws ClassNotFoundException, IOException,SQLException;

}
