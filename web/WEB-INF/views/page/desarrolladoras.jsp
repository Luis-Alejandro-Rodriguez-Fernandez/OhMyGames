<%-- 
    Document   : desarrolladoras
    Created on : 26 may. 2022, 21:06:34
    Author     : Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
--%>

<%@page import="org.japo.java.entities.Desarrolladora"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
List<Desarrolladora> ds = (List<Desarrolladora>) request.getAttribute("desarrolladoras");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/listado.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
        <title>Desarrolladoras - Oh My Games</title>
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <p id="delDes"><%= request.getSession(false).getAttribute("delDes") != null ? request.getSession(false).getAttribute("delDes") : null%></p>
            <a href="?cmd=desarrolladora-nueva">Añadir Desarrolladora</a>
            <div id="main"></div>
            <nav id="nav"></nav>
        </main>
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/desarrolladora.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
