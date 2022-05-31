<%-- 
    Document   : signup
    Created on : 4 abr. 2022, 21:22:43
    Author     : Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup - Oh My Games</title>
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/form.css"/>
        <link rel="stylesheet" href="public/css/signup.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <div id="error"><p></p></div>
            <form id="form" method="POST" action="?cmd=signup&op=proceso">
                <h2>Registro</h2>
                <div class="field">
                    <label for="user">usuario</label>
                    <input type="text" id="Usuario" name="user">
                </div>
                <div class="field">
                    <label for="email">Correo electrónico</label>
                    <input type="text" id="Email" name="email">
                </div>
                <div class="field">
                    <label for="pass">Contraseña</label>
                    <input type="password" id="Contraseña" name="pass">
                </div>
                <div class="field">
                    <label for="pass2">Repetir contraseña</label>
                    <input type="password" id="Contraseña2" name="pass2">
                </div>
                <a id="btn">Enviar</a>
            </form>
        </main>
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/signup.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
