/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import com.ifpb.MyPersonalAgenda.banco.ConnectionFactory;
import com.ifpb.MyPersonalAgenda.modelo.Agenda;
import com.ifpb.MyPersonalAgenda.modelo.Compromisso;
import static com.ifpb.MyPersonalAgenda.visao.PaginaInicial.usuarioLogado;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThigoYure
 */
public class CompromissoDaoBanco implements CompromissoDao {
    
    AgendaDaoBanco agendaDao = new AgendaDaoBanco();
    List<Agenda> agendas;

    public CompromissoDaoBanco() throws ClassNotFoundException, SQLException, IOException {
        agendas = agendaDao.list(usuarioLogado.getEmail());
    }

    @Override
    public Compromisso readCompromissos(LocalDate data, String hora, String agenda) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM compromisso WHERE data =? and hora = ? and agenda = ? and usuario = ?");

        stmt.setDate(1, Date.valueOf(data));
        stmt.setString(2, hora);
        stmt.setString(3, agenda);
        stmt.setString(4, usuarioLogado.getEmail());
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            Compromisso comp = new Compromisso();
            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getString("hora"));
            comp.setDescricao(rs.getString("descricao"));
            comp.setAgenda(rs.getString("agenda"));
            comp.setLocal(rs.getString("local"));
            
            con.close();
            return comp;
        }else{
            con.close();
            return null;
        }
    }

    @Override
    public List<Compromisso> listCompromissos() throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createCompromissos(Compromisso comp) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteCompromissos(Compromisso comp) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateCompromissos(Compromisso comp) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Compromisso> listarCompromissosIntervalo(LocalDate inicio, LocalDate fim) throws ClassNotFoundException, IOException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM compromisso where emailUsuario = ? and data between ? and ?");

        stmt.setString(1, usuarioLogado.getEmail());
        stmt.setDate(2, java.sql.Date.valueOf(inicio));
        stmt.setDate(3, java.sql.Date.valueOf(fim));

        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compromissos = new ArrayList<>();

        while (rs.next()) {

            Compromisso compromisso = new Compromisso();

            compromisso.setData(rs.getDate("data").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            compromisso.setHora(rs.getString("hora"));
            compromisso.setLocal(rs.getString("local"));
            compromisso.setDescricao(rs.getString("descricao"));
            

            compromissos.add(compromisso);
        }

        con.close();
        return compromissos;
    }
    
}
