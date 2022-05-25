<%@page import="org.japo.java.entities.Desarrolladora"%>
<%@page import="org.japo.java.entities.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="org.japo.java.entities.Producto"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
HttpSession sesion = request.getSession(false);
Usuario user = (Usuario) sesion.getAttribute("usuario");
List<Categoria> categorias = (List<Categoria>) request.getAttribute("lista-categorias");
List<Desarrolladora> desarrolladoras = (List<Desarrolladora>) request.getAttribute("lista-desarrolladoras");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Oh My Games</title>
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/landing.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <nav id="filter">
            <select id="cat" name="cat">
                <option selected value=0>Todas</option>
                <%for(Categoria c : categorias){%>
                <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                <%}%>
            </select>
            <select id="des" name="des">
                <option selected value=0>Todas</option>
                <%for(Desarrolladora d : desarrolladoras){%>
                <option value=<%=d.getId()%>><%=d.getNombre()%></option>
                <%}%>
            </select>
            <select id="tipo">
                <option selected value=2> Juegos y DLC</option>
                <option value=0> Juegos</option>
                <option value=1> DLC</option>
            </select>
            <div id="precio">
                <input type="number" id="min" value="0" min="0" max="99"/>
                <input type="number" id="max" value="100" min="1" max="100"/>
                <button id="btn-pre"><img src="public/img/lupa.png" alt="lupa"/></button>
            </div>
        </nav>
            <button id="test" hidden="true" >Test</button>
        <main>
            <div id="cards"></div>
        </main>
        <nav id="pag"></nav>
            <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/landing.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
