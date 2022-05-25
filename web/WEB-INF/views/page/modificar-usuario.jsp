
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
        <link rel="stylesheet" href="public/css/perfil.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
        <title>Modificar - Oh My Games</title>
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <div id="tab">
                <nav>
                    <a href="?cmd=usuario-perfil">Volver</a>
                </nav>
                <div>
                    <fom id="" method="POST" action="?svc=actualizar-user">
                    <a id="reset">Reiniciar</a>
                    <section id="mod-div">
                        <div id="error"><p></p></div>
                        <div id="field">
                            <label for="user">Usuario</label>
                            <input type="text" id="Usuario" value="<%=u.getUser()%>" name="user">
                        </div>
                        <div id="field">
                            <label for="email">Correo electrónico</label>
                            <input type="text" id="Email" value="<%=u.getEmail()%>" name="email">
                        </div>
                        <div id="field">
                            <label for="passO">Contraseña</label>
                            <input type="password" id="Contraseña" value="" name="passO">
                        </div>
                        <div id="field">
                            <label for="passN">Repetir contraseña</label>
                            <input type="password" id="Contraseña2" name="passN">
                        </div>
                        <div>
                            <div class="field">
                                <label for="imga">Imagen</label> 
                                <input type="file" id="imga" name="imga">
                                <input id="imgUp" name="imgUp">
                            </div>
                            <img id="imgLoad" src="" alt="Aquí estará tu imagen."/>
                        </div>
                    </section>  
                    <a id="modU">Guardar</a>
                    </fom>
                </div>                
            </div>
        </main>        
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/moduser.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
