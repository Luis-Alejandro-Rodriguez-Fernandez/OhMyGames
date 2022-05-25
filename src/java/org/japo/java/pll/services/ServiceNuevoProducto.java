/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceNuevoProducto extends Service {

    @Override
    public void process() throws ServletException, IOException {

        String json = "";
        int insert = 0;
        if (request.getSession(false).getAttribute("usuario") != null) {
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            if (u.getGrupo() == 2) {
                try {

                    String nombre = request.getParameter("nombre");
                    double precio = Double.parseDouble(request.getParameter("precio"));
                    int descuento = Integer.parseInt(request.getParameter("descuento"));
                    int tipo = Integer.parseInt(request.getParameter("tipo"));
                    int categoria = Integer.parseInt(request.getParameter("categoria"));
                    int desarrolladora = Integer.parseInt(request.getParameter("desarrolladora"));
                    String descripcion = request.getParameter("descripcion");
                    String img = request.getParameter("imgUp");
                    String date = request.getParameter("date");

                    boolean res = bll.insertarProducto(nombre, precio, descuento, tipo, categoria, desarrolladora, descripcion, img, date);

                    if (res) {
                        insert = 1;
                    } else {
                        insert = 2;
                    }
                } catch (Exception e) {
                    insert = 2;
                    json = "{\"acces\":false,\"res\":false,\"status\":\"error\"}";
                }
            } else {
                insert = 0;
                json = "{\"acces\":false,\"res\":false}";
            }
        } else {
            forwardCmd("admin/login");
        }
        request.getSession(false).setAttribute("insert", insert);
        forwardCmd("redirect/redirect-landing");
    }
}
