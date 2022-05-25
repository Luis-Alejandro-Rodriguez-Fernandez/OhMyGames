
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="org.japo.java.entities.Favorito"%>
<%@page import="org.japo.java.entities.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
    Producto p = (Producto) request.getAttribute("producto");
    Favorito favorito = (Favorito) request.getAttribute("favorito");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=p.getNombre()%> - Oh My Games</title>
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/producto.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <section>
                <p id="prod-id"><%=p.getId()%></p>
                <div>
                    <h2><%=p.getNombre()%></h2>
                    <%if (u != null && u.getGrupo() == 2) {%>
                            <a href="?cmd=producto-modificar&id=<%=p.getId()%>">Modificar producto</a>
                            <%}%>
                </div>
                <div>
                    <div>
                        <img src="<%=p.getImagen()%>" alt="<%=p.getNombre()%>"/>
                        <span id="des"><%=p.getDescuento() + "%"%></span>
                        <div>
                            <%if (u == null || u.getGrupo() == 1) {%>
                            <img id="car" src="public/img/carrito.png"/>
                            <%}%>
                            <%if (favorito != null) {%>
                            <img id="fav" src="public/img/fav.png" alt="Favorito"/>
                            <%} else {%>
                            <img id="fav" src="public/img/nofav.png" alt="No Favorito"/>
                            <%}%>
                        </div>
                    </div>
                    <div>
                        <p id="cat"><%=p.getCategoria().getDescription()%></p>
                        <p id="des"><%=p.getDesarrolladora().getDescription()%></p>
                        <p id="desc"><%=p.getDescripcion()%></p>
                        <div>
                            <del id="pre-or"><%=
                                String.format("%.2f €",p.getPrecio())
                                %></del>
                            <p id="pre-def"><%=String.format("%.2f €",p.getPrecio() - (p.getPrecio() * (p.getDescuento() / 100.00)))%></p>
                        </div>
                    </div>

                </div>
            </section>
        </main>
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/producto.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
