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
import java.util.List;

/**
 *
 * @author ThigoYure
 */
public interface AgendaDao {
    
    public Agenda read(String nome) throws ClassNotFoundException, SQLException, IOException;

    public List<Agenda> list(String email) throws ClassNotFoundException, SQLException, IOException;

    public boolean create(Agenda agenda) throws ClassNotFoundException, SQLException, IOException;

    public boolean delete(String nome) throws ClassNotFoundException, SQLException, IOException;

    public boolean update(Agenda agenda) throws ClassNotFoundException, SQLException, IOException;
    
    
}
