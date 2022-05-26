<%-- 
    Document   : categorias
    Created on : 26 may. 2022, 21:06:11
    Author     : Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
--%>

<%@page import="org.japo.java.entities.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
List<Categoria> cs = (List<Categoria>) request.getAttribute("categorias");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/listado.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
        <title>Categorias - Oh My Games</title>
    </head>
    <body>        
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <p id="delCat"><%= request.getSession(false).getAttribute("delCat") != null ? request.getSession(false).getAttribute("delCat") : null%></p>
            <a href="?cmd=categoria-nueva">Añadir Categoria</a>
            <%for(Categoria c : cs){%>

            <section>
                <%= c.getNombre() %>
                <article>
                    <a href="?cmd=categoria-modificar&id=<%=c.getId()%>">Mod</a>
                    <a href="?cmd=categoria-borrar&id=<%=c.getId()%>">Borrar</a>
                </article>
            </section>

            <%}%>
        </main>
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/categoria.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
