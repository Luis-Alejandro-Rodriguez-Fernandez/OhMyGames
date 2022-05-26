/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import org.japo.java.entities.Usuario;
import javax.servlet.ServletException;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceActualizarUser extends Service {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {
            String json;
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            String email = request.getParameter("email");
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String pass2 = request.getParameter("pass2");
            String img = request.getParameter("imgUp");
            System.out.println(img);
            boolean mod = false;
            try {
                if (pass.isEmpty() && img == null || img.isEmpty()) {
                    mod = bll.modificarUsuario(u.getId(), user, email);
                } else if(img == null || img.isEmpty()){
                    if(pass.equals(pass2)){
                    mod = bll.modificarUsuarioPass(u.getId(), user, email, pass);
                    }
                } else if(pass.isEmpty()){
                    mod = bll.modificarUsuarioImg(u.getId(), user, email, img);
                }else{
                    mod = bll.modificarUsuarioAll(u.getId(), user, email, pass, img);
                }
                if (mod) {
                    json = "{\"res\" : true}";
                    Usuario ua = bll.obtenerUsuarioUs(user);
                    request.getSession(false).setAttribute("usuario", ua);
                } else {
                    json = "{\"res\" : false}";
                }
            } catch (Exception e) {
                json = "{\"res\" : false}";
            }

            forward(json);
        }
        forwardCmd("page/perfil");
    }

}
