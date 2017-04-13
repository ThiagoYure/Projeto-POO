/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.visão;

import com.ifpb.controle.GerenciaDados;
import com.ifpb.controle.UsuarioDaoList;
import com.ifpb.entidades.Agenda;
import com.ifpb.entidades.Compromisso;
import com.ifpb.entidades.Usuario;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javafx.util.converter.LocalDateStringConverter;

/**
 *
 * @author ThigoYure
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UsuarioDaoList dao = new UsuarioDaoList();
        dao.create(new Usuario("Thiago","45/02/2015","masculino","thigoyure@gmail.com","Uva"));
        dao.create(new Usuario("Ricarte","28/02/2015","masculino","Ricarte@gmail.com","Bananna"));
        System.out.println(dao.read("Ricarte@gmail.com"));
        System.out.println(dao.listar());
        dao.delete(new Usuario("Thiago","12/02/2015","masculino","thigoyure@gmail.com","Uva"));
        System.out.println(dao.listar());
        dao.update(new Usuario("Ricarte","29/02/2015","masculino","Ricarte@gmail.com","Manga"));
        System.out.println(dao.listar());
        dao.listar().get(0).create(new Agenda("Esportes"));
        dao.listar().get(0).create(new Agenda("Prevaricação"));
        dao.listar().get(0).listar().get(0).create(new Compromisso("30/02/2017", LocalTime.now(),"Jogar no leblon","Leblon"));
        System.out.println(dao.listar().get(0).listar());
        
        
        
        
    }
    
}
