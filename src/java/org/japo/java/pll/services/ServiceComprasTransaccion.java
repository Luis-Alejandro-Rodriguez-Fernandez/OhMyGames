/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Compra;
import org.japo.java.entities.Desarrolladora;
import org.japo.java.entities.Transaccion;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceComprasTransaccion extends Service{

    @Override
    public void process() throws ServletException, IOException {
         String json = "";

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            List<Compra> ps = bll.listarComprasTransaccion(id);
            if (ps == null) {
                json = "{\"ok\":false,\"msg\":\"Error General1\"}";
            } else {
                int cont = 0;
                for (Compra p : ps) {
                    cont++;
                    if (cont == 1 && ps.size() == 1) {
                        json = p.toString();
                    } else if (cont == 1) {
                        json = "[" + p.toString();
                    } else if (cont == ps.size()) {
                        json += "," + p.toString() + "]";
                    } else {
                        json += "," + p.toString();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(json);
            json = "{\"ok\":false,\"msg\":\"Error General\"}";
        }
        forward(json.equals("") ? "{\"value\":null}" : json);
    }
    
}
