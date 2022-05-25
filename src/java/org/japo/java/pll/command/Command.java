/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.japo.java.bll.MainBLL;
import org.japo.java.dao.MainDAO;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández - 2001luisalejandro@gmail.com
 */
public abstract class Command implements ICommand {
    // Interfaz de Comunicación con el Contenedor

    protected ServletContext context;

    // Petición del Cliente Encapsulada
    protected HttpServletRequest request;

    // Respuesta al Cliente Encapsulada
    protected HttpServletResponse response;

    // Propiedades del Servidor
    protected Properties prp;

    // Capa de Negocio
    protected MainBLL bll;

    // Inicialización del Comando
    @Override
    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            Properties prp,
            MainBLL bll) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
        this.prp = prp;
        this.bll = bll;
    }

    // Ejecución del Comando
    protected void forward(String cmd) throws ServletException, IOException {
        // Nombre Comando ( Petición ) > Nombre Vista ( Respuesta )
        String vista = String.format("/WEB-INF/views/%s.jsp", cmd.toLowerCase());

        // Contexto + Nombre Vista > Despachador
        RequestDispatcher dispatcher = context.getRequestDispatcher(vista);

        // Despachador + Petición + Respuesta > Redirección a Vista
        dispatcher.forward(request, response);
    }
    
        protected void forwardSvc(String json) throws ServletException, IOException {
        // Establecer Tipode Respuesta
        response.setContentType("application/json");
        
        // Establece Codificación Binaria
        response.setCharacterEncoding("UTF-8");
        
        // Enlazar Salida
        try (PrintWriter pw = response.getWriter()) {
            // JSON > SALIDA
            pw.write(json);
        }
    }

}
