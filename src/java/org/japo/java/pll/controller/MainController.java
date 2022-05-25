/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.japo.java.bll.MainBLL;
import org.japo.java.libraries.UtilesServlet;
import org.japo.java.pll.command.ICommand;
import org.japo.java.pll.services.IService;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
@WebServlet(name = "MainController", urlPatterns = {"", "/public/*"})
public class MainController extends HttpServlet {

    // Propiedades del Servidor
    private Properties prp;

    // Capa de Negocio
    private MainBLL bll;

    @Override
    public void init() throws ServletException {
        super.init();

        // Cargar Propiedades
//        prp = UtilesServlet.importarPropiedadesServlet();
        // Instanciar Capa de Negocio
        bll = new MainBLL(prp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getPathInfo().equals("/")) {
            // servir recurso dinámico
            servirElementos(request, response);
        } else {

            //servir recurso estático
            servirEstatico(request, response);
        }
    }

    private void servirElementos(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String svcName = request.getParameter("svc");

        if (svcName != null) {
            // Nombre del comando
            String[] items = svcName.split("-");

            // Concatenador
            StringBuilder sb = new StringBuilder();

            // Items > Camel Case
            for (String i : items) {
                sb.append(Character.toUpperCase(i.charAt(0)));
                sb.append(i.substring(1).toLowerCase());
            }

            // Nombre de Servicio > Servicio ( Interfaz )
            IService svc = UtilesServlet.obtenerServicio(sb.toString());

            // ServletContext + Peticion + Resuesta > Inicializar Comando
            svc.init(getServletContext(), request, response, prp, bll);

            // Procesa Comando
            svc.process();
        } else {
            //ID Sesion
//        String hash = request.getSession().getId();
            String cmdName;
            // Petición > Nombre de Comando (Kebab Case)
            if (request.getParameter("cmd") == null) {
                cmdName = "landing";
            } else {
                cmdName = request.getParameter("cmd");
            }

//        System.out.println(cmdName);
            // Nombre de Comando > Comando ( Interfaz )
            ICommand cmd = UtilesServlet.obtenerComando(cmdName);
            //Numero Aleatorio
//        long numero = rnd.nextLong();
            //Numero Aleatorio > Sesion
//        request.getSession().setAttribute("numero", numero);
            // ServletContext + Peticion + Resuesta > Inicializar Comando
            cmd.init(getServletContext(), request, response, prp, bll);

            // Procesa Comando
            cmd.process();
        }

    }

    private void servirEstatico(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //ID Sesion
//        String hash = request.getSession().getId();
//        String hash = request.getSession().getAttribute("numero").toString();

        //Nombre Recurso
        String recursoIni = request.getPathInfo();
//        recursoIni = recursoIni.replace("/" + hash, "");
//        recursoIni = recursoIni.replace("/", "");

        String recursoFin = "/WEB-INF/static" + recursoIni;

        String ruta = request.getPathTranslated();
        //Adaptar ruta
        ruta = ruta.replace("\\", "/");

//        ruta = ruta.replace("/" + hash, "");
//        ruta = ruta.replace("/", "");
        ruta = ruta.replace(recursoIni, recursoFin);
//        System.out.println(ruta);
        // Nombre de Recurso > Fichero
        File fichero = new File(ruta);

        //Realizar Transferencias
        try (
                 FileInputStream in = new FileInputStream(fichero);  ServletOutputStream out = response.getOutputStream();) {
            //Buffer de Transferencia
            byte[] buffer = new byte[(int) fichero.length()];

            //Fichero > Buffer
            in.read(buffer);

            //Buffer > Salida
            out.write(buffer);
        } catch (Exception e) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
