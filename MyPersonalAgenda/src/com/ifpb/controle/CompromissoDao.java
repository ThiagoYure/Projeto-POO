/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.controle;

import com.ifpb.entidades.Compromisso;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author ThigoYure
 */
public interface CompromissoDao {
    
    boolean create(Compromisso comp);
    Compromisso read(LocalDate dataCompromisso, LocalTime horaCompromisso);
    boolean update(Compromisso comp);
    boolean delete(Compromisso comp);
    
}
