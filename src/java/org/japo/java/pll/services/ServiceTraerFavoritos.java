/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Favorito;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceTraerFavoritos extends Service {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {

            String json = "";
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            try {
                List<Favorito> fs = bll.listarFavorito(u.getId());
                int cont = 0;
                for (Favorito f : fs) {
                    cont++;
                    if (fs.size() == 1) {
                        json = f.getProducto().toString();
                    } else if (cont == 1) {
                        json = "[" + f.getProducto().toString();
                    } else if (cont == fs.size()) {
                        json += "," + f.getProducto().toString() + "]";
                    } else {
                        json += "," + f.getProducto().toString();
                    }
                }
            } catch (Exception e) {
            }
            forward(json.equals("") ? "{\"value\":null}" : json);
        } else {
            forwardCmd("admin/login");
        }
    }

}
