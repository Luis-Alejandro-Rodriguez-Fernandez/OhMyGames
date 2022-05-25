/* 
 * Copyright 2021 José A. Pacheco Ondoño - japolabs@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.japo.java.bll.MainBLL;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public abstract class Service implements IService {

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
    
    // Inicialización del Servicio
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

    // Ejecución del Servicio
    protected void forward(String json) throws ServletException, IOException {
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
    
        protected void forwardCmd(String cmd) throws ServletException, IOException {
        // Nombre Comando ( Petición ) > Nombre Vista ( Respuesta )
        String vista = String.format("/WEB-INF/views/%s.jsp", cmd.toLowerCase());

        // Contexto + Nombre Vista > Despachador
        RequestDispatcher dispatcher = context.getRequestDispatcher(vista);

        // Despachador + Petición + Respuesta > Redirección a Vista
        dispatcher.forward(request, response);
    }
}
