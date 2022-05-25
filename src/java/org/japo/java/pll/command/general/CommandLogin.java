/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.general;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.japo.java.bll.MainBLL;
import org.japo.java.entities.Usuario;
import org.japo.java.pll.command.Command;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandLogin extends Command{

    // Duración de Sesión - 1800 seg ( 30 min - default )
    // 300seg = 5 minutos de duración
    private static final int DURACION_SESION = 3600;

    // Capa de Negocio
    // Propiedades del Servidor
    private Properties prp;

    // Capa de Negocio
    private MainBLL bll = new MainBLL(prp);

    @Override
    public void process() throws ServletException, IOException {
        // JSP
        String page;

        try {
            // Obtener Operación
            String op = request.getParameter("op");

            // Capas de Negocio
            // Request > Sesión
            HttpSession sesion = request.getSession(false);

            // Procesar Operación
            if (sesion == null) {
                // Acceso Denegado - Acceso directo por URL
                page = "admin/login";
            } else if (sesion.getAttribute("usuario") != null) {
                // Sesión + Usuario Existente - Aceso permitido
                page = "acceso-concedido";
            } else if (op == null || op.equals("captura")) {
                // Credenciales > Request
                page = "admin/login";
            } else if (op.equals("proceso")) {
                // Request > Credenciales
                String user = request.getParameter("user");
                String pass = request.getParameter("pass");
                    System.out.println(user);
                    System.out.println(pass);

                // Validar Credencial
                if (user == null || !Usuario.validarUsername(user)) {
                    // Credencial Incorrecta - Nombre de Usuario Incorrecto
                    page = "credencial-erronea";
                } else if (pass == null || !Usuario.validarPassword(pass)) {
                    // Credencial Incorrecta - Contraseña Incorrecta
                    page = "credencial-erronea";
                } else {
                    // Nombre Usuario + BD > Objeto Usuario
                    Usuario usuario = bll.obtenerUsuarioUs(user);
                    System.out.println(usuario);
                    // Validar Objeto Usuario
                    if (usuario == null) {
                        // Credencial Incorrecta - Usuario NO Registrado
                        page = "credencial-erronea";
                    } else if (!pass.equals(usuario.getPassword())) {
                        // Credencial Incorrecta - Contraseña NO coincide
                        page = "credencial-erronea";
                    } else {
                        // Elimina Sesión Previa
                        sesion.invalidate();

                        // Crear Nueva Sesión
                        sesion = request.getSession(true);

                        // Establecer duracion sesion ( Segundos )
                        sesion.setMaxInactiveInterval(DURACION_SESION);

                        // Usuario > Sesión
                        sesion.setAttribute("usuario", usuario);

                        // JSP Aviso
                        page = "redirect/redirect-landing";
//                        String sesid = sesion.getId();
                    }
                }
            } else {
                // Formato de URL incorrecto
                page = "error/page404";
            }
        } catch (Exception e) {
            // Recurso NO disponible
            page = "error/page404";
        }

        // Redirección JSP
        forward(page);
    }
}
