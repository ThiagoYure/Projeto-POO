/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.controle;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Essa classe contém métodos referentes a manipulação e conversão de dados
 * @author ThigoYure
 */
public class GerenciaDados {
 /**
  * Converte uma data do tipo Date para LocalDate
  * @param data a data a ser convertida
  * @return a data já convertida
  */   
    public static LocalDate toLocalDate(Date data) {
        Instant instant = Instant.ofEpochMilli(data.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }
/**
 * Converte uma data no formato LocalDate para Date
 * @param data a data a ser convertida
 * @return a data já convertida
 */    
    public static Date toDate(LocalDate data) {
        if(data == null){
            return null;
        }
        LocalDate localDate = data;
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return date;
    }
}
