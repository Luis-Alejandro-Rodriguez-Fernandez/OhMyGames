package org.japo.java.pll.command.compra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.japo.java.entities.Compra;
import org.japo.java.entities.Transaccion;
import org.japo.java.entities.Usuario;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.pll.command.Command;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandCompraHistorial extends Command{
         @Override
    public void process() throws ServletException, IOException {
        String page = "page/historial-compras";
        if (request.getSession(false).getAttribute("usuario") != null) {
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            if (u.getGrupo() == 1) {

		try{
			List<Transaccion> ts = bll.listarTransacciones(u.getId());
                        
			List<Compra> cs = new ArrayList<Compra>();
                        for(Transaccion t : ts){
                        cs.addAll(bll.listarCompras(t.getId()));
                        }
                       
                        System.out.println(cs.size());
			request.setAttribute("lista-transacciones",ts);
			request.setAttribute("lista-compras",cs);
		}catch(Exception e){
			page ="error/page404";
		}

            }else{
            page ="admin/landing";
            }

        } else {
            page = "admin/login";
        }
        forward(page);

    }
}