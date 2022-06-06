
<%@page import="org.japo.java.entities.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/perfilmod.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
        <title>Modificar - Oh My Games</title>
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <a href="?cmd=usuario-perfil" class="volver">🠔 Volver</a>
            <div id="error"><p></p></div>
            <form id="form" method="POST" action="?cmd=usuario-actualizar">
                <h2>Modificar perfil</h2>
                <div>
                    <div class="field">
                        <label for="user">Usuario</label>
                        <input type="text" id="Usuario" value="<%=u.getUser()%>" name="user">
                    </div>
                    <div class="field">
                        <label for="email">Correo electrónico</label>
                        <input type="text" id="Email" value="<%=u.getEmail()%>" name="email">
                    </div>
                    <div class="field">
                        <label for="passO">Contraseña</label>
                        <input type="password" id="Contraseña" value="" name="pass">
                    </div>
                    <div class="field">
                        <label for="passN">Repetir contraseña</label>
                        <input type="password" id="Contraseña2" name="pass2">
                    </div>
                </div>
                <div id="img">
                    <div class="field">
                        <label for="imga">Imagen</label> 
                        <input type="file" id="imga" name="imga">
                        <input id="imgUp" name="imgUp" value="">
                    </div>
                    <img id="imgLoad" src="<%=u.getAvatar()%>" alt="Aquí estará tu imagen."/>
                </div>
                <div>
                    <a id="modU">Guardar</a>
                    <a id="reset">Reiniciar</a>
                </div>

            </form>
        </main>        
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/moduser.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
