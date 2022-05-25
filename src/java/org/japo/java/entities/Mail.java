/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class Mail {

    private Properties properties;
    private Session session;

    public Mail() throws IOException {
//        this.properties = new Properties();
//
//        loadConfig(ruta);
    }

    private void loadConfig(String ruta) throws FileNotFoundException, IOException {
//        InputStream is = new FileInputStream(ruta);
//
//        this.properties.load(is);
//        
//        checkConfig();
    }

    private void checkConfig() {
//        String[] keys = {
//            "smtp.gmail.com",
//            "mail.smtp.port",
//            "mail.smtp.user",
//            "mail.smtp.password",
//            "mail.smtp.starttls.enable",
//            "mail.smtp.auth",
//        };
        
//        for(int i = 0; i < keys.length ;i++){
//            if(this.properties.get(keys[i]) == null){
//                throw new InvalidParameterException("No existe la clave "+keys[i]);
//            }
//        }
    }
    
    public void enviarMail(String asunto, String mensaje, String correo) throws AddressException, MessagingException{
        MimeMessage contenedor = new MimeMessage(session);
                    System.out.println("va");
        contenedor.setFrom( new InternetAddress("ohmygames.company.spain@gmail.com"));
        contenedor.addRecipient(Message.RecipientType.TO,new InternetAddress(correo));
        contenedor.setSubject(asunto);
        contenedor.setText(mensaje);
        Transport t = session.getTransport("smtp");
        
        t.connect("ohmygames.company.spain@gmail.com","35795146aA");
        t.sendMessage(contenedor, contenedor.getAllRecipients());
    }
}
