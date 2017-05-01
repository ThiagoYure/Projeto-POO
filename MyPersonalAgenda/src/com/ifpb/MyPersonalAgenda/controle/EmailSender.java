/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author ThigoYure
 */
public class EmailSender {

    public void sendEmail(String emailUser, String nome) throws EmailException {
        Email email = new SimpleEmail();
        email.setDebug(true);
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        // email.setSSLOnConnect(true);
        email.setAuthentication("thigoyure@gmail.com", "Kabuto123456");
        email.setFrom("thigoyure@gmail.com");
        email.setSubject("Hm");
        email.setMsg("Teste");
        email.addTo(emailUser);
        email.setStartTLSEnabled(true);
        email.send();
    }

}
