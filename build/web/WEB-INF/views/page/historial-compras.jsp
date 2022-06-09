
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="org.japo.java.entities.Transaccion"%>
<%@page import="org.japo.java.entities.Compra"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
//Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
//List<Transaccion> ts = (List<Transaccion>) request.getAttribute("lista-transacciones");
//List<Compra> cs = (List<Compra>) request.getAttribute("lista-compras");
//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/historial-compras.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
        <title>Historial - Oh My Games</title>
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <div>
                <h3>ID Pedido</h3>
                <h3>Fecha de compra</h3>
                <h3>Importe Total</h3>
            </div>
            <div id="main"></div>
            <nav id="nav"></nav>

        </main>        
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="public/js/historial-compras.js"></script>
        <script type="text/javascript" charset="utf-8" src="public/js/historial-query.js"></script>
    </body>
</html>
