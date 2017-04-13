/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.controle;

/**
 *
 * @author ThigoYure
 */
public interface Dao <T> {
    
    boolean create(T o);
    T read(String o);
    boolean update(T o);
    boolean delete(T o);
    
}
