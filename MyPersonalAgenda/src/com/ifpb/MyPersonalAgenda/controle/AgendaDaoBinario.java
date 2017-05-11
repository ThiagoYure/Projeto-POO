/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import com.ifpb.MyPersonalAgenda.modelo.Agenda;
import com.ifpb.MyPersonalAgenda.modelo.Compromisso;
import com.ifpb.MyPersonalAgenda.modelo.Usuario;
import com.ifpb.MyPersonalAgenda.visao.PaginaInicial;
import static com.ifpb.MyPersonalAgenda.visao.PaginaInicial.usuarioLogado;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Essa classe contém métodos para persistência da entidade Agenda em Arquivo Binário
 * @author ThigoYure
 */
public class AgendaDaoBinario implements AgendaDao {

    private File agendas;
    /**
     * Construtor do DaoBinario da entidade Agenda
     */
    public AgendaDaoBinario() {
        agendas = new File("Agendas.bin");

        if (!agendas.exists()) {
            try {
                agendas.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Falha na conexão com o arquivo",
                        "Mensagem de Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * Busca por uma determinada agenda no Arquivo Binário
     * @param nome nome da agenda
     * @return a agenda que se deseja
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws SQLException 
     */
    @Override
    public Agenda read(String nome) throws ClassNotFoundException, IOException,SQLException {
        List<Agenda> agendasUsers = list(usuarioLogado.getEmail());

        for (Agenda a : agendasUsers) {
            if (a.getNome().equals(nome)) {
                return a;
            }
        }
        return null;
    }
    /**
     * Lista as agendas para um determinado usuario através do email desse usuário
     * @param usuario email do usuario que se deseja obter as agendas
     * @return a lista de agendas para aquele usuario
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public List<Agenda> list(String usuario) throws IOException, ClassNotFoundException, SQLException {
        List<Agenda> agendasList = new ArrayList<>();
        List<Agenda> retorno = new ArrayList<>();
        if (this.agendas.length() > 0) {
            ObjectInputStream input;
            input = new ObjectInputStream(
                    new FileInputStream(this.agendas));
            retorno = (List<Agenda>) input.readObject();
            for (Agenda a : retorno) {
                if (a.getEmailUser().equals(usuarioLogado.getEmail())){
                    agendasList.add(a);
                }
            }
            return agendasList;
        } else {
            return new ArrayList<>();
        }
    }
    /**
     * Insere uma agenda no Arquivo Binário
     * @param agenda a agenda que se deseja inserir no Banco de Dados
     * @return a confirmação da inserção ou não
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    @Override
    public boolean create(Agenda agenda) throws ClassNotFoundException, SQLException, IOException {
        List<Agenda> agendasUsers = list(PaginaInicial.usuarioLogado.getEmail());

        for (Agenda a : agendasUsers) {
            if (a.getNome().equals(agenda.getNome()) && agenda.getEmailUser().equals(a.getEmailUser())) {
                return false;
            }
        }

        agendasUsers.add(agenda);

        atualizarArquivo(agendasUsers);

        return true;
    }
    /**
     * Remove uma determinada agenda do Arquivo Binário através do seu nome
     * @param nome o nome da agenda a ser removida
     * @return a confirmação da remoção ou não
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    @Override
    public boolean delete(String nome) throws ClassNotFoundException, SQLException, IOException {
        List<Agenda> agendasUsers = list(PaginaInicial.usuarioLogado.getEmail());

        for (Agenda a : agendasUsers) {

            if (a.getNome().equals(nome) && a.getEmailUser().equals(read(nome).getEmailUser())) {
                agendasUsers.remove(a);
                atualizarArquivo(agendasUsers);
                return true;
            }
        }
        return false;
    }
    /**
     * Atualiza uma agenda já salva no Arquivo Binário
     * @param agendaNova agenda com os dados novos para substituir a antiga
     * @param agendaAntiga agenda antiga que se deseja atualizar
     * @return confirmação da atualização ou nâo
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    @Override
    public boolean update(Agenda agendaNova, Agenda agendaAntiga) throws ClassNotFoundException, SQLException, IOException {
        List<Agenda> agendasUsers = list(PaginaInicial.usuarioLogado.getEmail());

        for (int i = 0; i < agendasUsers.size(); i++) {
            if (agendasUsers.get(i).getNome().
                    equals(agendaAntiga.getNome()) && agendasUsers.get(i).getEmailUser().equals(agendaAntiga.getEmailUser())) {
                agendasUsers.set(i, agendaNova);
                atualizarArquivo(agendasUsers);

                return true;
            }
        }
        return false;
    }
    /**
     * Atualiza o Arquivo Binário de agenda
     * @param agendasUsers a lista de agendas que deve ser usada para atualizar o arquivo
     * @throws IOException 
     */
    private void atualizarArquivo(List<Agenda> agendasUsers) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(agendas));

        out.writeObject(agendasUsers);
        out.close();
    }

}
