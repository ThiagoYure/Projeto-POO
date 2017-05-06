/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import com.ifpb.MyPersonalAgenda.modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ThigoYure
 */
public class UsuarioDaoBinario implements UsuarioDao {

    private File usuarios;

    public UsuarioDaoBinario() {
        usuarios = new File("Usuarios.bin");

        if (!usuarios.exists()) {
            try {
                usuarios.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Falha na conexão com o usuarios",
                        "Mensagem de Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public Usuario read(String email) throws ClassNotFoundException, SQLException, IOException {
        List<Usuario> users = list();

        for (Usuario u : users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> list() throws ClassNotFoundException, SQLException, IOException {
        if (usuarios.length() > 0) {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(usuarios));

            return (List<Usuario>) input.readObject();

        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean create(Usuario usuario) throws ClassNotFoundException, SQLException, IOException {

        List<Usuario> users = list();

        for (Usuario user : users) {
            if (user.getEmail().equals(usuario.getEmail())) {
                return false;
            }
        }

        users.add(usuario);

        atualizarArquivo(users);

        return true;
    }

    @Override
    public boolean delete(String email) throws ClassNotFoundException, SQLException, IOException {
        List<Usuario> users = list();

        for (Usuario u : users) {

            if (u.getEmail().equals(email)) {
                users.remove(u);
                atualizarArquivo(users);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Usuario usuario) throws ClassNotFoundException, SQLException, IOException {
        List<Usuario> users = list();
        
        if (usuario.getNascimento().isAfter(LocalDate.now())) {
                return false;
        }

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getEmail().
                    equals(usuario.getEmail())) {
                users.set(i, usuario);

                atualizarArquivo(users);

                return true;
            }

        }
        return false;
    }

    private void atualizarArquivo(List<Usuario> users) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(usuarios));

        out.writeObject(users);
        out.close();
    }

}
