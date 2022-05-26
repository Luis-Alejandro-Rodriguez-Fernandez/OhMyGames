<%-- 
    Document   : añadir-categoria
    Created on : 26 may. 2022, 21:47:29
    Author     : Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/form.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
        <title>Categorias - Oh My Games</title>
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <a href="?cmd=categoria">volver</a>
            <form id="form">
                <div>
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre">
                </div>
                <a id="btn">Añadir</a>
            </form>
        </main>
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/añadir-categoria.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
