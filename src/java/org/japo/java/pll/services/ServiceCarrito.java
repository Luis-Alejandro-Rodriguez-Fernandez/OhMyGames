/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Carrito;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceCarrito extends Service {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {

            String json = "";
            Usuario user = (Usuario) request.getSession(false).getAttribute("usuario");
            List<Carrito> cs;
            try {
                cs = bll.listarCarrito(user.getId());
                if (cs == null) {
                    cs = null;
                } else {
                    int cont = 0;
                    for (Carrito c : cs) {
                        if (cont == 0 && cs.size() > 1) {
                            json = "[" + c.toString();
                        } else if (cs.size() == 1) {
                            json = c.toString();
                        } else if (cont == cs.size() - 1) {
                            json += "," + c.toString() + "]";
                        } else {
                            json += "," + c.toString();
                        }
                        cont++;
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
