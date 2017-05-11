/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.controle;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author ThigoYure
 */
public class Email {

    public void sendEmail() throws EmailException {

        SimpleEmail email = new SimpleEmail();
        //Utilize o hostname do seu provedor de email
        System.out.println("alterando hostname...");
        email.setHostName("smtp.gmail.com");
        //Quando a porta utilizada não é a padrão (gmail = 465)
        email.setSmtpPort(25);
        //Adicione os destinatários
        email.addTo("thigoyure@gmail.com", "Thiago");
        //Configure o seu email do qual enviará
        email.setFrom("mypersonalgendapoo@gmail.com", "My Personal Agenda");
        //Adicione um assunto
        email.setSubject("Test message");
        //Adicione a mensagem do email
        email.setMsg("This is a simple test of commons-email");
        //Para autenticar no servidor é necessário chamar os dois métodos abaixo
        System.out.println("autenticando...");
        email.setAuthentication("mypersonalgendapoo@gmail.com", "mypersonalagenda");
        System.out.println("enviando...");
        email.send();
        email.setSSL(true);
        
        email.setDebug(true);
        System.out.println("Email enviado!");
    }

}
