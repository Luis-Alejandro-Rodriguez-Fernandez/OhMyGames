/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Usuario;
import org.japo.java.entities.Carrito;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceComprar extends Service {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {

            String json = "";
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            Date d = new Date();
            double importe = Double.parseDouble(request.getParameter("importe"));
            int id = (int) (Math.random() * (99999999 - 10000000 + 1) - 10000000);
            int con = 0;

            try {
                boolean transaction = bll.insertarTransaccion(id, u.getId(), d, importe);
                if (transaction) {
                    List<Carrito> cs = bll.listarCarrito(u.getId());

                    for (Carrito c : cs) {
                        boolean comp = bll.insertarCompra(id, c.getProducto().getId(), (c.getProducto().getPrecio() - (c.getProducto().getPrecio() * c.getProducto().getDescuento()) / 100));
                        if (comp) {
                            con++;
                            bll.eliminarCarrito(u.getId(), c.getProducto().getId());
                        }
                    }

                    if (con == cs.size()) {
                        json = "{\"value\":true}";
                    } else {
                        json = "{\"value\":false}";
                    }

                }
            } catch (Exception e) {
                json = "{\"value\":false}";
            }
            forward(json.equals("") ? "{\"value\":null}" : json);
        } else {
            forwardCmd("admin/login");
        }
    }
}
