/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Desarrolladora;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceAñadirDesarrolladora extends Service {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {

            String json;
            boolean ok = true;

            try {
                boolean cat;
                String nombre = request.getParameter("nombre");
                List<Desarrolladora> cs = bll.listarDesarrolladoras();

                System.out.println(ok);
                for (Desarrolladora c : cs) {
                    if (nombre.equalsIgnoreCase(c.getNombre().trim())) {
                        ok = false;
                    }
                }
                if (ok) {
                    cat = bll.añadirDesarolladora(nombre);
                    System.out.println(cat + "aqui");
                    json = "{\"des\":" + cat + "}";
                } else {
                    json = "{\"des\":" + null + "}";
                }

            } catch (Exception e) {
                json = "{\"ok\":false,\"msg\":\"Error General\"}";
            }

            forward(json);
        }
    }

}
