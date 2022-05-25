/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.producto;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Producto;
import org.japo.java.pll.command.Command;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandProductoBuscado extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String page = "page/producto-buscado";
        try {
            String name = request.getParameter("finder");
                request.setAttribute("name", name);
            
        } catch (Exception e) {
            page = "error/page404";
        }
        System.out.println(page);
        forward(page);
    }

}
