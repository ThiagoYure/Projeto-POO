/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import com.ifpb.MyPersonalAgenda.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ThigoYure
 */
public interface UsuarioDao {

    public Usuario read(String email) throws ClassNotFoundException, SQLException, IOException;

    public List<Usuario> list() throws ClassNotFoundException, SQLException, IOException;

    public boolean create(Usuario usuario) throws ClassNotFoundException, SQLException, IOException;

    public boolean delete(String email) throws ClassNotFoundException, SQLException, IOException;

    public boolean update(Usuario usuario) throws ClassNotFoundException, SQLException, IOException;

}
