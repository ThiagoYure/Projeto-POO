/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import com.ifpb.MyPersonalAgenda.banco.ConnectionFactory;
import com.ifpb.MyPersonalAgenda.excecoes.DataInvalidaException;
import com.ifpb.MyPersonalAgenda.modelo.Agenda;
import com.ifpb.MyPersonalAgenda.modelo.Compromisso;
import com.ifpb.MyPersonalAgenda.modelo.Usuario;
import static com.ifpb.MyPersonalAgenda.visao.PaginaInicial.usuarioLogado;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThigoYure
 */
public class AgendaDaoBanco implements AgendaDao{


    public AgendaDaoBanco() {

    }

    @Override
    public Agenda read(String nome) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM agenda WHERE nome = ? and emailUsuario = ?");

        stmt.setString(1, nome);
        stmt.setString(2, usuarioLogado.getEmail());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Agenda agenda = new Agenda();

            agenda.setNome(rs.getString("nome"));
            agenda.setDescricao(rs.getString("descricao"));
            agenda.setEmailUser(rs.getString("emailusuario"));
            con.close();
            return agenda;
        } else {
            con.close();
            return null;
        }
    }

    @Override
    public List<Agenda> list(String email) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM agenda where emailUsuario = ?");

        stmt.setString(1, usuarioLogado.getEmail());

        ResultSet rs = stmt.executeQuery();
        List<Agenda> agendas = new ArrayList<>();

        while (rs.next()) {

            Agenda agenda = new Agenda();

            agenda.setNome(rs.getString("nome"));
            agenda.setDescricao(rs.getString("descricao"));
            agenda.setEmailUser(rs.getString("emailusuario"));

            agendas.add(agenda);
        }

        con.close();
        return agendas;
    }

    @Override
    public boolean create(Agenda agenda) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO agenda (nome, descricao, emailusuario) VALUES (?,?,?)");

        stmt.setString(1, agenda.getNome());
        stmt.setString(2, agenda.getDescricao());
        stmt.setString(3, agenda.getEmailUser());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }

    @Override
    public boolean delete(String nome) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM agenda WHERE nome = ? and emailUsuario = ?");
        stmt.setString(1, nome);
        stmt.setString(2, usuarioLogado.getEmail());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }

    @Override
    public boolean update(Agenda agenda) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "UPDATE agenda SET (nome, descricao, emailusuario)"
                + " = (?,?,?) WHERE nome = ? and emailUsuario = ?");
        stmt.setString(1, agenda.getNome());
        stmt.setString(2, agenda.getDescricao());
        stmt.setString(3, agenda.getEmailUser());
        stmt.setString(4, usuarioLogado.getNome());
        stmt.setString(5, usuarioLogado.getEmail());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
    
}
