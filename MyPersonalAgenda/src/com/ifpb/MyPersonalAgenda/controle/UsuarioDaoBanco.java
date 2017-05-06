/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import com.ifpb.MyPersonalAgenda.banco.ConnectionFactory;
import com.ifpb.MyPersonalAgenda.excecoes.EmailInvalidoException;
import com.ifpb.MyPersonalAgenda.excecoes.SenhaInvalidaException;
import com.ifpb.MyPersonalAgenda.modelo.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThigoYure
 */
public class UsuarioDaoBanco implements UsuarioDao{
    
    public UsuarioDaoBanco(){
    }

    @Override
    public Usuario read(String email) throws ClassNotFoundException, SQLException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM usuario WHERE email = ?");

        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario();

            try {
                usuario.setEmail(rs.getString("email"));
            } catch (EmailInvalidoException ex) {
                System.out.println(ex.getMessage());
            }
            usuario.setNome(rs.getString("nome"));
            usuario.setNascimento(rs.getDate("nascimento").toLocalDate());
            usuario.setSexo(rs.getString("sexo").charAt(0));
            try {
                usuario.setSenha(rs.getString("senha"));
            } catch (SenhaInvalidaException ex) {
                System.out.println(ex.getMessage());
            }

            con.close();
            return usuario;
        } else {
            con.close();
            return null;
        }

    }

    @Override
    public List<Usuario> list() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM usuario");

        ResultSet rs = stmt.executeQuery();
        List<Usuario> usuarios = new ArrayList<>();

        while (rs.next()) {

            Usuario usuario = new Usuario();
            try {
                usuario.setEmail(rs.getString("email"));
            } catch (EmailInvalidoException ex) {
                System.out.println(ex.getMessage());
            }
            usuario.setNome(rs.getString("nome"));
            usuario.setNascimento(rs.getDate("nascimento").toLocalDate());
            usuario.setSexo(rs.getString("sexo").charAt(0));
            try {
                usuario.setSenha(rs.getString("senha"));
            } catch (SenhaInvalidaException ex) {
                System.out.println(ex.getMessage());
            }
            usuarios.add(usuario);
        }

        con.close();
        return usuarios;

    }

    @Override
    public boolean create(Usuario u) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO usuario (email, nome, sexo,"
            + " nascimento, senha) VALUES (?,?,?,?,?)");
        
        stmt.setString(1, u.getEmail());
        stmt.setString(2, u.getNome());
        stmt.setString(3, ""+u.getSexo());
        stmt.setDate(4, Date.valueOf(u.getNascimento()));
        stmt.setString(5, u.getSenha());
        
        boolean retorno = stmt.executeUpdate()>0;
        con.close();
        return retorno;
        
    }

    @Override
    public boolean delete(String email) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = con.prepareStatement(
            "DELETE FROM usuario WHERE email = ?");
        stmt.setString(1, email);
        
        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;        
        
    }

    @Override
    public boolean update(Usuario u) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "UPDATE usuario SET (nome, sexo, nascimento, senha)"
                    + " = (?,?,?,?) WHERE email = ?");
        
        stmt.setString(1, u.getNome());
        stmt.setString(2, ""+u.getSexo());
        stmt.setDate(3, Date.valueOf(u.getNascimento()));
        stmt.setString(5, u.getSenha());
        stmt.setString(6, u.getEmail());
        
        boolean retorno = stmt.executeUpdate()>0;
        con.close();
        return retorno;
    }
}
