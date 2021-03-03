/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ThigoYure
 */
public class ConnectionFactory {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        
        Class.forName("org.postgresql.Driver");
        
        String url = "jdbc:postgresql://localhost:5432/MyPersonalAgenda";
        String usuario = "postgres";
        String senha = "senha";
        
        return DriverManager.getConnection(url, usuario, senha);
        
    }
    
}
