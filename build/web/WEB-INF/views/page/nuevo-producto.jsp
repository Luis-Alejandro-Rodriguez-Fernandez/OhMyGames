
<%@page import="java.util.List"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="org.japo.java.entities.Categoria"%>
<%@page import="org.japo.java.entities.Desarrolladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
    List<Categoria> cs = (List<Categoria>) request.getAttribute("lista-categorias");
    List<Desarrolladora> ds = (List<Desarrolladora>) request.getAttribute("lista-desarrolladoras");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if (u.getGrupo() != 2) {%>
        <meta http-equiv="refresh" content="0; URL=?cmd=landing" />
        <% }%>
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/nuevo-producto.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
        <title>Insertar - Oh My Games</title>
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <div id="error"><p></p></div>
            <form id="form" action="?svc=nuevo-producto" method="POST">
                <h2>Insertar producto</h2>
                <div>
                    <div class="field">
                        <label for="nombre">Nombre</label>
                        <input type="text" id="nombre"  name="nombre">
                    </div>
                    <div class="pre-des">                    
                        <div class="field">
                            <label for="precio">Precio</label>
                            <input type="number" id="precio" name="precio">
                        </div>            
                        <div class="field">
                            <label for="descuento">Descuento</label>
                            <input type="number" id="descuento" name="descuento">
                        </div>     
                    </div>
                    <div class="field">
                        <label for="categoria">Categoria</label>
                        <select id="categoria" name="categoria">
                            <%for (Categoria c : cs) {%>
                            <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                            <%}%>
                        </select>
                    </div>            
                    <div class="field">
                        <label for="desarrolladora">Desaroolladora</label>
                        <select id="desarrolladora" name="desarrolladora">
                            <%for (Desarrolladora d : ds) {%>
                            <option value="<%=d.getId()%>"><%=d.getNombre()%></option>
                            <%}%>
                        </select>
                    </div>            
                    <div class="field">
                        <label for="tipo">Tipo</label>
                        <select id="tipo" name="tipo">
                            <option value=0>Juego</option>
                            <option value=1>DLC</option>
                        </select>
                    </div>            
                    <div class="field">
                        <label for="date">Lanzamiento</label>    
                        <input type="date" id="date" name="date">
                    </div>             
                    <div class="field">
                        <label for="descripcion">Descripción</label>
                        <textarea id="descripcion" name="descripcion"></textarea>
                    </div>           
                </div>
                <div>
                    <div class="field">
                        <label for="imga">Imagen</label> 
                        <input type="file" id="imga" name="imga">
                        <input id="imgUp" name="imgUp">
                    </div>
                    <img id="imgLoad" src="" alt="Aquí estará tu imagen."/>
                </div>

                <a id="add">Añadir Producto</a>
            </form>
        </main>
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/nuevo-producto.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
