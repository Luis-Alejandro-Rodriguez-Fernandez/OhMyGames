<%-- 
    Document   : page404
    Created on : 12 may. 2022, 21:58:22
    Author     : Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404 - Oh My Games</title>
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/error.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <h1>404</h1>
            <h2>No encontramos lo que busca.</h2>
            <h3>Inténtelo de nuevo.</h3>
        </main>
        <jsp:include page="../../partials/footer.jsp"/>
    </body>
</html>
