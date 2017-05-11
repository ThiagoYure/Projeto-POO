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
 * Essa classe contém métodos para persistência da entidade Compromisso em Banco de Dados
 * @author ThigoYure
 */
public class CompromissoDaoBanco implements CompromissoDao {

    AgendaDaoBanco agendaDao = new AgendaDaoBanco();
    /**
    * Construtor do DaoBanco da entidade Compromisso
    */
    public CompromissoDaoBanco(){

    }
    /**
     * Busca por um determinado compromisso no Banco de Dados
     * @param data a data do compromisso a buscar
     * @param hora a data do compromisso a buscar
     * @param agenda a agenda do compromisso a buscar
     * @return o compromisso solicitado
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
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

        if (rs.next()) {
            Compromisso comp = new Compromisso();
            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getString("hora"));
            comp.setDescricao(rs.getString("descricao"));
            comp.setAgenda(rs.getString("agenda"));
            comp.setLocal(rs.getString("local"));

            con.close();
            return comp;
        } else {
            con.close();
            return null;
        }
    }
    /**
     * Lista todos os compromissos do usuario logado no sistema
     * @return a lista de todos os compromissos do usuario logado 
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public List<Compromisso> listCompromissos() throws IOException, ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM compromisso WHERE usuario = ?");

        stmt.setString(1, usuarioLogado.getEmail());
        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compromissos = new ArrayList<>();
        while (rs.next()) {

            Compromisso comp = new Compromisso();

            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getString("hora"));
            comp.setDescricao(rs.getString("descricao"));
            comp.setAgenda(rs.getString("agenda"));
            comp.setLocal(rs.getString("local"));

            compromissos.add(comp);

        }
        con.close();
        return compromissos;

    }
    /**
     * Lista todos os compromisso de uma determinada agenda do usuário logado
     * @param agenda nome da agenda do usuario
     * @return a lista de compromissos para aquela agenda do usuario
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public List<Compromisso> listCompromissos(String agenda) throws IOException, ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM compromisso WHERE usuario = ? and agenda = ?");

        stmt.setString(1, usuarioLogado.getEmail());
        stmt.setString(2, agenda);
        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compromissos = new ArrayList<>();
        while (rs.next()) {

            Compromisso comp = new Compromisso();

            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getString("hora"));
            comp.setDescricao(rs.getString("descricao"));
            comp.setAgenda(rs.getString("agenda"));
            comp.setLocal(rs.getString("local"));

            compromissos.add(comp);

        }
        con.close();
        return compromissos;

    }
    /**
     * Insere um compromisso no Banco de Dados
     * @param comp o compromisso a ser inserido
     * @return a confirmação da inserção ou não
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean createCompromissos(Compromisso comp) throws IOException, ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO compromisso (data,hora,descricao,local,agenda,usuario) VALUES (?,?,?,?,?,?)");
        stmt.setDate(1, Date.valueOf(comp.getData()));
        stmt.setString(2, comp.getHora());
        stmt.setString(3, comp.getDescricao());
        stmt.setString(4, comp.getLocal());
        stmt.setString(5, comp.getAgenda());
        stmt.setString(6, usuarioLogado.getEmail());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
    /**
     * Remove um compromisso do Banco de Dados
     * @param comp o compromisso a ser removido
     * @return a cnfirmação da remoção ou não
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean deleteCompromissos(Compromisso comp) throws IOException, ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM compromisso WHERE data = ? and hora = ? and agenda = ? and usuario = ?");

        stmt.setDate(1, Date.valueOf(comp.getData()));
        stmt.setString(2, comp.getHora());
        stmt.setString(3, comp.getAgenda());
        stmt.setString(4, usuarioLogado.getEmail());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
    /**
     * Atualiza um compromisso no Banco de Dados
     * @param compNovo novo compromisso a ser inserido no lugar do antigo
     * @param compAntigo compromisso antigo a ser substituido pelo novo
     * @return a confirmação da atualização ou não
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean updateCompromissos(Compromisso compNovo, Compromisso compAntigo) throws IOException, ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "UPDATE compromisso SET (data,hora,descricao,local,agenda,usuario)"
                + " = (?,?,?,?,?,?) WHERE data = ? and hora = ? and agenda = ? and usuario = ?");
        stmt.setDate(1, Date.valueOf(compNovo.getData()));
        stmt.setString(2, compNovo.getHora());
        stmt.setString(3, compNovo.getDescricao());
        stmt.setString(4, compNovo.getLocal());
        stmt.setString(5, compNovo.getAgenda());
        stmt.setString(6, usuarioLogado.getEmail());
        stmt.setDate(7, Date.valueOf(compAntigo.getData()));
        stmt.setString(8, compAntigo.getHora());
        stmt.setString(9, compAntigo.getAgenda());
        stmt.setString(10, usuarioLogado.getEmail());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
    /**
     * Lista compromissos de um determinado intervalo de datas em uma determinada agenda
     * @param inicio data inicial do intervalo
     * @param fim data final do intervalo
     * @param agenda nome da agenda a que os compromissos devem pertencer
     * @return a lista de compromissos da agenda para o intervalo
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws SQLException 
     */
    @Override
    public List<Compromisso> listarCompromissosIntervalo(LocalDate inicio, LocalDate fim, String agenda) throws ClassNotFoundException, IOException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt;
        if (agenda.equals("Todas")){
            stmt = con.prepareStatement(
                    "SELECT * FROM compromisso where usuario = ? and data between ? and ?");

            stmt.setString(1, usuarioLogado.getEmail());
            stmt.setDate(2, java.sql.Date.valueOf(inicio));
            stmt.setDate(3, java.sql.Date.valueOf(fim));
        }
        else{
            stmt = con.prepareStatement(
                    "SELECT * FROM compromisso where agenda = ? and usuario = ? and data between ? and ?");

            stmt.setString(1, agenda);
            stmt.setString(2, usuarioLogado.getEmail());
            stmt.setDate(3, java.sql.Date.valueOf(inicio));
            stmt.setDate(4, java.sql.Date.valueOf(fim));
        }
        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compromissos = new ArrayList<>();

        while (rs.next()) {

            Compromisso compromisso = new Compromisso();

            compromisso.setData(rs.getDate("data").toLocalDate());
            compromisso.setHora(rs.getString("hora"));
            compromisso.setLocal(rs.getString("local"));
            compromisso.setDescricao(rs.getString("descricao"));

            compromissos.add(compromisso);
        }

        con.close();
        return compromissos;
    }

}
