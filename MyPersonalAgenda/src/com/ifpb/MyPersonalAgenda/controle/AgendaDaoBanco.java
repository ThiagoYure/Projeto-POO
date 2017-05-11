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
 * Essa classe contém métodos para persistência da entidade Agenda em Banco de Dados
 * @author ThigoYure
 */
public class AgendaDaoBanco implements AgendaDao{

    /**
    * Construtor do DaoBanco da entidade Agenda
    */
    public AgendaDaoBanco() {

    }
    /**
     * Busca por uma determinada agenda no Banco de Dados
     * @param nome o nome da agenda
     * @return a agenda solicitada
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    @Override
    public Agenda read(String nome) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM agenda WHERE nome = ? and usuario = ?");

        stmt.setString(1, nome);
        stmt.setString(2, usuarioLogado.getEmail());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Agenda agenda = new Agenda();

            agenda.setNome(rs.getString("nome"));
            agenda.setDescricao(rs.getString("descricao"));
            agenda.setEmailUser(rs.getString("usuario"));
            con.close();
            return agenda;
        } else {
            con.close();
            return null;
        }
    }
    /**
     * Lista as agendas para um determinado usuario através do email desse usuário
     * @param email email do usuário cujas agendas se quer
     * @return a lista de agendas para aquele usuário
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    @Override
    public List<Agenda> list(String email) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM agenda where usuario = ?");

        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();
        List<Agenda> agendas = new ArrayList<>();

        while (rs.next()) {

            Agenda agenda = new Agenda();

            agenda.setNome(rs.getString("nome"));
            agenda.setDescricao(rs.getString("descricao"));
            agenda.setEmailUser(rs.getString("usuario"));

            agendas.add(agenda);
        }

        con.close();
        return agendas;
    }
    /**
     * Insere uma agenda no Banco de Dados
     * @param agenda a agenda que se deseja inserir no Banco de Dados
     * @return a confirmação da inserção ou não
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    @Override
    public boolean create(Agenda agenda) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO agenda (nome, descricao, usuario) VALUES (?,?,?)");

        stmt.setString(1, agenda.getNome());
        stmt.setString(2, agenda.getDescricao());
        stmt.setString(3, agenda.getEmailUser());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
    /**
     * Remove uma determinada agenda do Banco de dados através do seu nome
     * @param nome o nome da agenda a ser removida
     * @return a confirmação da remoção ou não
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    @Override
    public boolean delete(String nome) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM agenda WHERE nome = ? and usuario = ?");
        stmt.setString(1, nome);
        stmt.setString(2, usuarioLogado.getEmail());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
    /**
     * Atualiza uma agenda já salva no Banco de Dados
     * @param agendaNova agenda com os dados novos para substituir a antiga
     * @param agendaAntiga agenda antiga que se deseja atualizar
     * @return confirmação da atualização ou nâo
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    @Override
    public boolean update(Agenda agendaNova, Agenda agendaAntiga) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "UPDATE agenda SET (nome, descricao, usuario)"
                + " = (?,?,?) WHERE nome = ? and usuario = ?");
        stmt.setString(1, agendaNova.getNome());
        stmt.setString(2, agendaNova.getDescricao());
        stmt.setString(3, agendaNova.getEmailUser());
        stmt.setString(4, agendaAntiga.getNome());
        stmt.setString(5, usuarioLogado.getEmail());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
    
}
