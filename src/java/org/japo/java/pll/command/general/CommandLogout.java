/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.general;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.japo.java.entities.Usuario;
import org.japo.java.pll.command.Command;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandLogout extends Command{

    @Override
    public void process() throws ServletException, IOException {
// JSP
       // JSP
        String page = "redirect/redirect-landing";

        // request > Sesión Actual
        HttpSession sesion = request.getSession(false);

        // Validar Sesión Actual
        if (sesion != null) {
            // Sesión > Usuario
            Usuario u = (Usuario) sesion.getAttribute("usuario");

            System.out.println(u);
            // Validar Usuario
            if (u != null) {
            // Cerrar Sesión Actual
            sesion.invalidate();
            }
        }

        // Redirección JSP
        forward(page);
    }
    
}
