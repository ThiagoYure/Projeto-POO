/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.controle;

import com.ifpb.entidades.Agenda;
import com.ifpb.entidades.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Essa classe contém métodos para realização do CRUD e controle do usuário 
 * @author ThigoYure
 */
public class UsuarioDaoList implements Dao<Usuario>,Autenticavel{
    
    private List<Usuario> usuarios;
/**
 * Construtor do CRUD de usuario
 */
    public UsuarioDaoList() {
        usuarios = new ArrayList<>();
    }
/**
 * Adciona um usuário a lista de usuários do Dao de usuario
 * @param u usuario a ser adcionado
 * @return a confirmação da inserção ou não
 */
    @Override
    public boolean create(Usuario u) {
        for(int i=0;i<usuarios.size();i++){
            if(usuarios.get(i).getEmail().equals(u.getEmail())){
                return false;
            }
        }
        if(u.getNascimento()==null){
            return false;
        }
        return usuarios.add(u);
    }
/**
 * Lê um usuário da lista de usuários do Dao de usuário
 * @param email email do usuário que se quer ler
 * @return o usuário requerido
 */
    @Override
    public Usuario read(String email) {
        for(int i=0;i<usuarios.size();i++){
            if(usuarios.get(i).getEmail().equals(email)){
                return usuarios.get(i);
            }
        }
        return null;
    }
/**
 * Atualiza um usuário da lista de usuários do Dao de usuário
 * @param u usuário que substituirá o antigo usuário
 * @return a confirmação da atualização ou não
 */
    @Override
    public boolean update(Usuario u) {
        for(int i=0;i<usuarios.size();i++){
            if(usuarios.get(i).getEmail().equals(u.getEmail())){
                if(u.getNascimento()==null){
                   return false; 
                }
                usuarios.set(i, u);
                return true; 
            }
        } 
        return false;
    }
/**
 * Deleta um usuário da lista de usuários do Dao de usuário
 * @param u usuário a ser deletado
 * @return a confirmação da remoção ou não
 */
    @Override
    public boolean delete(Usuario u) {
        return usuarios.remove(u);
    }
/**
 * Verifica se um usuário existe na lista de usuários do Dao de usuário
 * @param email email do usuário a ser verificado
 * @param senha senha do usuário a ser verificado
 * @return a confirmação da existência ou não
 */
    @Override
    public boolean auntenticar(String email, String senha) {
        for(int i=0;i<usuarios.size();i++){
            if((usuarios.get(i).getEmail().equals(email))&&(usuarios.get(i).getSenha().equals(senha))){
                return true;
            }
        } 
        return false;
    }
/**
 * Listar os usuário da lista de usuários do Dao de usuário
 * @return a lista de usuários do Dao de usuário
 */    
    public List<Usuario> listar(){
        return usuarios;
    }

 
}
