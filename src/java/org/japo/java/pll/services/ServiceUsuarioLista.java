/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceUsuarioLista extends Service {

    @Override
    public void process() throws ServletException, IOException {
        String json = "";

        try {
            List<Usuario> us = bll.obtenerListaUsuario();
            if (us == null) {
                json = "{\"ok\":false,\"msg\":\"Error General2\"}";
            } else {
                int cont = 0;
                for (Usuario u : us) {
                    if (cont == 0) {
                        json = "[" + u.toString();
                    } else if (cont == us.size() - 1) {
                        json += "," + u.toString() + "]";
                    } else {
                        json += "," + u.toString();
                    }
                    cont++;
                }
            }
        } catch (Exception e) {
            json = "{\"ok\":false,\"msg\":\"Error General1\"}";
        }
        forward(json);
    }

}
