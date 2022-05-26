<%-- 
    Document   : desarrolladoras
    Created on : 26 may. 2022, 21:06:34
    Author     : Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
--%>

<%@page import="org.japo.java.entities.Desarrolladora"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
List<Desarrolladora> ds = (List<Desarrolladora>) request.getAttribute("desarrolladas");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/historial-compras.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
        <title>Desarrolladoras - Oh My Games</title>
    </head>
    <body>
        <a href="?cmd=desarrolladora-añadir">Añadir Categoria</a>
        <%for(Desarrolladora d : ds){%>
           
        <section>
            <%= d.getNombre() %>
            <article>
                <a href="?cmd=desarrolladora-modificar&id=<%=d.getId()%>"></a>
                <a href="?cmd=desarrolladora-borrar&id=<%=d.getId()%>"></a>
            </article>
        </section>

        <%}%>
    </body>
</html>
