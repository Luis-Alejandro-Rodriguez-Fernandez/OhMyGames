<%-- 
    Document   : producto-navegar
    Created on : 15 may. 2022, 20:50:20
    Author     : Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String name = request.getAttribute("name").toString();

if(name.isEmpty()){
%>
<meta http-equiv="Refresh" content="0; URL=?cmd=landing" />
<%
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Búsqueda - Oh My Games</title>
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/busqueda.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <a href="?cmd=landing">🠔 Volver</a>
            <p>Has buscado: "<span id="name"><%=name%></span>"</p>
            <div id="cards">
            </div>
        </main>
        <nav id="pag"></nav>
            <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/producto-buscado.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
