/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import com.ifpb.MyPersonalAgenda.modelo.Agenda;
import com.ifpb.MyPersonalAgenda.modelo.Compromisso;
import static com.ifpb.MyPersonalAgenda.visao.PaginaInicial.usuarioLogado;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.rmi.Naming.list;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Essa classe contém métodos para persistência da entidade Compromisso em Arquivo Binário
 * @author ThigoYure
 */
public class CompromissoDaoBinario implements CompromissoDao {

    File compromissos;
    /**
    * Construtor do DaoBinario da entidade Compromisso
    */
    public CompromissoDaoBinario() {
        compromissos = new File("Compromissos.bin");

        if (!compromissos.exists()) {
            try {
                compromissos.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Falha na conexão com o arquivo",
                        "Mensagem de Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * Busca por um determinado compromisso no Arquivo Binário
     * @param data a data do compromisso a buscar
     * @param hora a data do compromisso a buscar
     * @param agenda a agenda do compromisso a buscar
     * @return o compromisso solicitado
     * @throws ClassNotFoundException
     * @throws SQLException 
     * @throws IOException
     */
    @Override
    public Compromisso readCompromissos(LocalDate data, String hora, String agenda) throws IOException, ClassNotFoundException,SQLException {
        List<Compromisso> compromissos = listCompromissos(agenda);

        for (Compromisso c : compromissos) {
            if (c.getData().equals(data) && c.getHora().equals(hora) && c.getAgenda().equals(agenda) && c.getUsuario().equals(usuarioLogado.getEmail())) {
                return c;
            }
        }
        return null;
    }
    /**
     * Lista todos os compromissos do usuario logado no sistema
     * @return a lista de todos os compromissos do usuario logado 
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public List<Compromisso> listCompromissos() throws IOException, ClassNotFoundException,SQLException {
        List<Compromisso> compromissos = new ArrayList<>();
        List<Compromisso> retorno = new ArrayList<>();
        if (this.compromissos.length() > 0) {
            ObjectInputStream input;
            input = new ObjectInputStream(
                    new FileInputStream(this.compromissos));
            retorno = (List<Compromisso>) input.readObject();

            if (retorno != null) {
                for (Compromisso c : retorno) {
                    if (c.getUsuario().equals(usuarioLogado.getEmail()));
                    compromissos.add(c);
                }
            }
            return compromissos;
        } else {
            return new ArrayList<>();
        }
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
    public List<Compromisso> listCompromissos(String agenda) throws IOException, ClassNotFoundException,SQLException {
        List<Compromisso> compromissos = new ArrayList<>();
        List<Compromisso> retorno = new ArrayList<>();
        if (this.compromissos.length() > 0) {
            ObjectInputStream input;
            input = new ObjectInputStream(
                    new FileInputStream(this.compromissos));
            retorno = (List<Compromisso>) input.readObject();

            if (retorno != null) {
                for (Compromisso c : retorno) {
                    if (c.getUsuario().equals(usuarioLogado.getEmail()) && c.getAgenda().equals(agenda));
                    compromissos.add(c);
                }
            }
            return compromissos;
        } else {
            return new ArrayList<>();
        }
    }
    /**
     * Insere um compromisso no Arquivo Binário
     * @param comp o compromisso a ser inserido
     * @return a confirmação da inserção ou não
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean createCompromissos(Compromisso comp) throws IOException, ClassNotFoundException,SQLException {
        List<Compromisso> compromissos = listCompromissos();

        for (Compromisso c : compromissos) {
            if (c.getData().equals(comp.getData()) && c.getHora().equals(comp.getHora()) && c.getAgenda().equals(comp.getAgenda()) && c.getUsuario().equals(usuarioLogado.getEmail())) {
                return false;
            }
        }

        compromissos.add(comp);

        atualizarArquivo(compromissos);

        return true;
    }
    /**
     * Remove um compromisso do Arquivo Binário
     * @param comp o compromisso a ser removido
     * @return a cnfirmação da remoção ou não
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean deleteCompromissos(Compromisso comp) throws IOException, ClassNotFoundException,SQLException {
        List<Compromisso> compromissos = listCompromissos();

        for (Compromisso c : compromissos) {

            if (c.getData().equals(comp.getData()) && c.getHora().equals(comp.getHora()) && c.getAgenda().equals(comp.getAgenda()) && c.getUsuario().equals(usuarioLogado.getEmail())) {
                compromissos.remove(c);
                atualizarArquivo(compromissos);
                return true;
            }
        }
        return false;
    }
    /**
     * Atualiza um compromisso no Arquivo Binário
     * @param compNovo novo compromisso a ser inserido no lugar do antigo
     * @param compAntigo compromisso antigo a ser substituido pelo novo
     * @return a confirmação da atualização ou não
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean updateCompromissos(Compromisso compNovo, Compromisso compAntigo) throws ClassNotFoundException, IOException, SQLException {
        List<Compromisso> compromissos = listCompromissos();

        for (int i = 0; i < compromissos.size(); i++) {
            if (compromissos.get(i).getData().equals(compAntigo.getData()) && compromissos.get(i).getHora().equals(compAntigo.getHora()) && compromissos.get(i).getAgenda().equals(compAntigo.getAgenda()) && compromissos.get(i).getUsuario().equals(usuarioLogado.getEmail())) {
                compromissos.set(i, compNovo);
                atualizarArquivo(compromissos);

                return true;
            }
        }
        return false;
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
    public List<Compromisso> listarCompromissosIntervalo(LocalDate inicio, LocalDate fim, String agenda) throws ClassNotFoundException, IOException,SQLException {
        List<Compromisso> compromissosIntervalo = new ArrayList<>();
        List<Compromisso> compromissos = listCompromissos();

        if (agenda == "Todas") {
            for (int i = 0; i < compromissos.size(); i++) {
                if ((compromissos.get(i).getData().isAfter(inicio)||compromissos.get(i).getData().equals(inicio)) && (compromissos.get(i).getData().isBefore(fim)||compromissos.get(i).getData().equals(fim))) {
                    compromissosIntervalo.add(compromissos.get(i));
                }
            }
            return compromissosIntervalo;
        }
        else{
            for (int i = 0; i < compromissos.size(); i++) {
                if (((compromissos.get(i).getData().isAfter(inicio)||(compromissos.get(i).getData().equals(inicio)) &&( compromissos.get(i).getData().isBefore(fim)||compromissos.get(i).getData().equals(fim)))&&compromissos.get(i).getAgenda().equals(agenda))) {
                    compromissosIntervalo.add(compromissos.get(i));
                }
            }
            return compromissosIntervalo;
        }
    }
    
    /**
     * Atualiza o Arquivo Binário de compromissos
     * @param compromissos lista de compromissos a ser usada para atualizar o arquivo
     * @throws IOException 
     */
    private void atualizarArquivo(List<Compromisso> compromissos) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(this.compromissos));

        out.writeObject(compromissos);
        out.close();
    }

}
