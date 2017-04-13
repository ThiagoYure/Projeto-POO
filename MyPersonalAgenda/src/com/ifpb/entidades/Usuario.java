/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.entidades;

import com.ifpb.controle.Dao;
import com.ifpb.controle.GerenciaDados;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Essa classe contém dados e métodos referentes ao usuário e o CRUD de agenda
 * @author ThigoYure
 */
public class Usuario implements Dao<Agenda>{
    private String nome;
    private LocalDate nascimento;
    private String sexo;
    private String senha;
    private String email;
    private List<Agenda> agendas;
/**
 * Construtor do usuário
 * @param nome o nome do usuário
 * @param nascimento a data de nascimento do usuário
 * @param sexo o sexo do usuário
 * @param email o email do usuário
 * @param senha a senha do usuário
 */

    public Usuario(String nome, String nascimento, String sexo, String email,String senha) {
        this.nome = nome;
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        formater.setLenient(false); 
        try {
           Date dataNascimento = formater.parse(nascimento);
           if(LocalDate.now().isAfter(GerenciaDados.toLocalDate(dataNascimento))){
                this.nascimento = GerenciaDados.toLocalDate(formater.parse(nascimento));
           }else{
                System.out.println("Data Inválida");
           }
           this.sexo = sexo;
           this.senha = senha;
           this.email = email;
           agendas = new ArrayList<>();    
        } catch (ParseException ex) {
            System.out.println("Data Inválida");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        formater.setLenient(false); 
        try {
           Date dataNascimento = formater.parse(nascimento);
           if(LocalDate.now().isAfter(GerenciaDados.toLocalDate(dataNascimento))){
             this.nascimento = GerenciaDados.toLocalDate(formater.parse(nascimento));
           }else{
             System.out.println("Data Inválida");
           }
        } catch (ParseException ex) {
            System.out.println("Data Inválida");;
        }
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public String getNomeAgenda() {
        return email;
    }

    public void setNomeAgenda(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.nascimento);
        hash = 97 * hash + Objects.hashCode(this.sexo);
        hash = 97 * hash + Objects.hashCode(this.senha);
        hash = 97 * hash + Objects.hashCode(this.email);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.nascimento, other.nascimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", nascimento=" + nascimento + ", sexo=" + sexo + ", senha=" + senha + ", email=" + email + '}';
    }
/**
 * Insere uma agenda na lista de agendas do usuário
 * @param a agenda a ser inserida
 * @return a confirmação da inserção ou não
 */
    @Override
    public boolean create(Agenda a) {
        for(int i=0;i<agendas.size();i++){
            if(agendas.get(i).getNomeAgenda().equals(a.getNomeAgenda())){
                return false;
            }
        }  
        return agendas.add(a);
    }
/**
 * Lê uma agenda da lista de agendas do usuário
 * @param nome o nome da agenda
 * @return a agenda que possui o nome requerido
 */
    @Override
    public Agenda read(String nome) {
        for(int i=0;i<agendas.size();i++){
            if(agendas.get(i).getNomeAgenda().equals(nome)){
                return agendas.get(i);
            }
        }
        return null;
    }
/**
 * Atualiza uma agenda da lista de agendas do usuário
 * @param a a agenda que substituirá a antiga agenda
 * @return a confirmação da atualização ou não
 */
    @Override
    public boolean update(Agenda a) {
        for(int i=0;i<agendas.size();i++){
            if(agendas.get(i).getNomeAgenda().equals(a.getNomeAgenda())){
                agendas.set(i, a);
                return true; 
            }
        } 
        return false;
    }
/**
 * Deleta uma agenda da lista de agendas do usuário
 * @param a a agenda a ser deletada
 * @return a confirmação da remoção ou não
 */
    @Override
    public boolean delete(Agenda a) {
        return agendas.remove(a);
    }
    
    public List<Agenda> listar(){
        return agendas;
    }
/**
 * Lista os compromissos de um usuário em um determinado intervalo de tempo recebido
 * @param inicio a data de inicio do intervalo
 * @param fim a data final do intervalo
 * @return a lista de compromissos do intervalo
 */
    public List<Compromisso> listarCompromissos(LocalDate inicio,LocalDate fim){
        List<Compromisso> lista = new ArrayList<>();
        for(int i=0;i<agendas.size();i++){
            for(int j=0;j<agendas.get(i).listar().size();j++){
                if((agendas.get(i).listar().get(j).getDataCompromisso().isEqual(inicio)||agendas.get(i).listar().get(j).getDataCompromisso().isAfter(inicio))&&(agendas.get(i).listar().get(j).getDataCompromisso().isEqual(fim)||agendas.get(i).listar().get(j).getDataCompromisso().isBefore(fim))){
                    lista.add(agendas.get(i).listar().get(j));
                }
            }
        }
        if(lista.isEmpty()){
            return null;
        }
        return lista;
    }

    
}
