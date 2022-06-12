<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.japo.java.entities.Producto"%>
<%@page import="org.japo.java.entities.Desarrolladora"%>
<%@page import="org.japo.java.entities.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="org.japo.java.entities.Usuario"%>

<%
Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
Producto p = (Producto) request.getAttribute("producto");
List<Categoria> cs = (List<Categoria>) request.getAttribute("lista-categorias");
List<Desarrolladora> ds = (List<Desarrolladora>)request.getAttribute("lista-desarrolladoras");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/nuevo-producto.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
        <title>Modificar - Oh My Games</title>
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <div id="error"><p></p></div>
            <form id="form" action="?svc=modificar-producto" method="POST">
                <div>
                    <div class="field">
                        <label for="nombre">Nombre</label>
                        <input type="text" id="nombre"  name="nombre" value="<%=p.getNombre()%>">
                    </div>
                    <div class="field">
                        <label for="precio">Precio</label>
                        <input type="number" id="precio" name="precio" value="<%=p.getPrecio()%>">
                    </div>   
                    <div class="field">
                        <label for="descuento">Descuento</label>
                        <input type="number" id="descuento" name="descuento" value="<%=p.getDescuento()%>">
                    </div>  
                    <div class="field">
                        <label for="categoria">Categoria</label>
                        <select id="categoria" name="categoria">
                            <%for(Categoria c : cs){
                        if(c.getId() == p.getCategoria().getId()){%>
                            <option value="<%=c.getId()%>" selected><%=c.getNombre()%></option>
                            <%}else{%>
                            <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                            <%}%>
                            <%}%>
                        </select>
                    </div>
                    <div class="field">
                        <label for="desarrolladora">Desaroolladora</label>
                        <select id="desarrolladora" name="desarrolladora">
                            <%for(Desarrolladora d : ds){
                        if(d.getId() == p.getDesarrolladora().getId()){%>
                            <option value="<%=d.getId()%>" selected><%=d.getNombre()%></option>
                            <%}else{%>
                            <option value="<%=d.getId()%>"><%=d.getNombre()%></option>
                            <%}%>
                            <%}%>
                        </select>
                    </div>  
                    <div class="field">
                        <label for="tipo">Tipo</label>
                        <select id="tipo" name="tipo">
                            <%if(p.isTipo()){%>
                            <option value=0>Juego</option>
                            <option value=1 selected>DLC</option>
                            <%}else{%>
                            <option value=0 selected>Juego</option>
                            <option value=1 >DLC</option>
                            <%}%>
                        </select>
                    </div>           
                    <div class="field">
                        <label for="date">Lanzamiento</label>    
                        <input type="date" id="date" name="date" value="<%=sdf.format(p.getLanzamiento())%>">
                    </div>
                    <div class="field">
                        <label for="descripcion">Descripción</label>
                        <textarea id="descripcion" name="descripcion" ><%=p.getDescripcion()%></textarea>
                    </div>      
                </div>
                <div>
                    <div class="field">
                        <input type="text" name="id" id="id-prod" value="<%=p.getId()%>"/>
                        <label for="imga">Imagen</label> 
                        <input type="file" id="imga" name="imga">
                    </div>                    
                    <img id="imgLoad" src="<%=p.getImagen()%>" alt="<%=p.getNombre()%>"/>
                </div>
                <a id="modBtn">Modificar Producto</a>
                <input id="imgUp" name="imgUp" value="<%=p.getImagen()%>"/>
                <%
                System.out.println(p.getImagen());
                %>
            </form>
        </main>
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/modificar-producto.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
