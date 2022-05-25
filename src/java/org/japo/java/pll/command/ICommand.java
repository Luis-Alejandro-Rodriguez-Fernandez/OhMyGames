/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command;

import java.io.IOException;
import java.util.Properties;
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
public interface ICommand {
    
    void init(
            ServletContext context,
            HttpServletRequest request,
            HttpServletResponse response,
            Properties prp,
            MainBLL bll);

    void process() throws ServletException, IOException;
}
