
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
        <title><%=u.getUser()%> - Oh My Games</title>
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <div id="tab">
                <section id="perfil">
                    <div>
                        <h2>Perfil</h2>
                        <a id="modBtn" href="?cmd=usuario-modificar">Modificar Cuenta</a>
                    </div>
                    <div>
                        <img src="<%=u.getAvatar()%>" alt="<%=u.getUser()%>"/>
                        <div>
                            <h2>@<%=u.getUser()%></h2>
                            <h3><%=u.getEmail()%></h3>
                        </div>
                    </div>
                </section>
                <%if (u == null || u.getGrupo() == 1) {%>
                <div id="nav">
                    <a id="btn-fav" class="select">Favoritos</a>
                    <a id="btn-bib">Mi biblioteca</a>
                </div>
                <div>
                    <section id="favs"></section>  
                    <section id="bib"></section>  
                </div>   
                <%}%>
            </div>
        </main>        
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/perfil.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
