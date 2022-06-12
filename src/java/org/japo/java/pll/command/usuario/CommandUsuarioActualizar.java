/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import org.japo.java.entities.Usuario;
import org.japo.java.pll.command.Command;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandUsuarioActualizar extends Command {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {
            String page = "redirect/redirect-perfil";
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            String email = request.getParameter("email");
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String pass2 = request.getParameter("pass2");
            String img = request.getParameter("imgUp");
            System.out.println(img);
            boolean mod = false;
            System.out.println(pass.equals("") + " xd");
            try {
                mod = bll.modificarUsuarioAll(u.getId(), user, email,
                        pass.isEmpty() || pass.equals("") || pass.equals("") ? u.getPassword() : pass,
                        img.equals("undefined") ? u.getAvatar() : img);

                if (mod) {

                    Usuario ua = bll.obtenerUsuarioUs(user);
                    request.getSession(false).setAttribute("usuario", ua);
                }
            } catch (Exception e) {

            }

            forward(page);
        }
    }
}
