/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import javax.servlet.ServletException;
import org.japo.java.entities.Mail;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceCorreo extends Service {

    @Override
    public void process() throws ServletException, IOException {
        String res;
        System.out.println("llega");
        try {
            Mail m = new Mail();
            m.enviarMail("test", "Esto es un test de compra", "2001luisalejandro@gmail.com");
            res = "{\"res\": true}";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res = "{\"res\": false}";
        }
        forward(res);
    }

}
