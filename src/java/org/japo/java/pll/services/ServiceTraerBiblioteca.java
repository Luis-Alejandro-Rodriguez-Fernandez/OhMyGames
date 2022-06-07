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
import org.japo.java.entities.Producto;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceTraerBiblioteca extends Service {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {

            String json = "";
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            int offset = Integer.parseInt(request.getParameter("offset"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            try {
                List<Producto> fs = bll.listarComprasUsuario(u.getId(),offset,limit);
                int cont = 0;
                for (Producto f : fs) {
                    cont++;
                    if (fs.size() == 1) {
                        json = f.toString();
                    } else if (cont == 1) {
                        json = "[" + f.toString();
                    } else if (cont == fs.size()) {
                        json += "," + f.toString() + "]";
                    } else {
                        json += "," + f.toString();
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
