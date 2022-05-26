<%-- 
    Document   : header
    Created on : 2 abr. 2022, 20:55:15
    Author     : Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
--%>
<%@page import="org.japo.java.entities.Usuario"%>
<%
    HttpSession sesion = request.getSession(false);
    Usuario user = (Usuario) sesion.getAttribute("usuario");
    int res = 0;
    try {
        res = Integer.parseInt(sesion.getAttribute("insert").toString());
    } catch (Exception e) {
        res = 0;
    }
    int mod = 0;
    try {
        mod = Integer.parseInt(sesion.getAttribute("modify").toString());
    } catch (Exception e) {
        mod = 0;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <a href="?cmd=landing"><img id="logo" src="public/img/logo.png" alt="logo"/></a>
    <div>
        <form id="barra" method="POST" action="?cmd=producto-buscado">
            <input id="finder" name="finder" type="text" autocomplete="off"/>
            <img id="find" src="public/img/lupa.png" alt="lupa"/>
        </form>
        <ul id="list-look"></ul>
    </div>

    <div>
        <%if (user != null) {%>
        <p id="user-id"><%=user.getId()%></p>
        <p id="user-user"><%=user.getUser()%></p>
        <p id="menu-h">☰</p>
        <p id="menu-x">X</p>
        <p id="insert"><%=res%></p>
        <p id="mod"><%=mod%></p>
        <div id="menu">
            <ul>  
                <li><a href="?cmd=usuario-perfil">Perfil</a></li>
                    <%if (user.getGrupo() == 2) {%>
                <li><a href="?cmd=producto-insertar">Añadir producto</a></li>
                <li><a href="?cmd=categoria">Administrar categorias</a></li>
                <li><a href="?cmd=desarrolladora">Administrar desarrolladoras</a></li>
                    <%}%>
                    <%if (user.getGrupo() == 1) {%>
                <li><a href="?cmd=usuario-carrito">Ver carrito</a></li>
                <li><a href="?cmd=compra-historial">Ver mis pedidos</a></li>
                    <%}%>
                <li><a href="?cmd=logout">Cerrar sesión</a></li>
            </ul>
        </div>
        <%} else {%>
        <a id="login" href="?cmd=login">Login</a>
        <a href="?cmd=signup">Sign up</a>
        <%}%>
    </div>
</header>
<style>

    /*------------- HEADER -----------------*/

    header{
        position: fixed;
        top:0;
        left:0;
        z-index: 1000;

        width: 100%;
        height: 4em;
        font-size: 1em;

        background-color: black;
        color: white;

        display: flex;
        align-items: center;
        justify-content: space-around;

        border-bottom: 1px solid rgba(255, 250, 250, 0.7);
    }

    #logo { 
        width: 115px;
        position: relative;
        top:-8px;
    }

    header a {
        text-decoration: none;
        color: white;
    }

    #barra{
        display: flex;
        justify-content: space-between;
        align-items: center;
    }


    header input {
        border-radius: .4em;
        border: none;

        padding: .3rem;
        margin: .5em;

        width: 30rem;
    }

    #list-look {
        width: 30rem;
        list-style: none;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        text-align: justify;

        background-color: #1b1b1b; 

        position: absolute;
        top: 2.1em;
        z-index: 10;
        display: none;
    }

    #list-look li {
        width: 100%;

        text-align: center;

        padding: 1em 0 1.5em 0;
        /*margin-top: .2em;*/

        border-bottom: 1px solid rgba(255, 250, 250, 0.7);
    }

    #list-look li:hover {
        background-color: #0a0a0a;
        transition: 1s;
    }

    /*------------------- NAV LATERAL -------------------*/
    #menu{
        position: fixed;
        bottom:0;
        right: 0;
        height: calc(100% - 60px);
        background-color: #1b1b1b;
        z-index: 100;
        transition: all 0.3s ease-in-out;
        width: 0;
    }

    #menu ul {
        overflow: hidden;
        visibility: hidden;
    }

    #menu ul li{
        line-height: 60px;
    }

    #menu ul li a {
        display: block;
        width:100%;
        margin:0;
        height: 60px;
        padding:  0 10px;
        text-transform: uppercase;
        white-space:nowrap;
        transition: all 0.3s ease-in-out;
    }

    #menu ul li a:hover{
        background-color: #0a0a0a;
        transition: 1s;
    }

    #barra a, #barra img{
        display:flex;
        justify-content: center;
        align-items: center;
        background-color: white;
        width: 20px;
        padding: 2px;
        border-radius: 6px
    }


    #finder{
        z-index: 11;
    }

    #menu-x{
        display: none;
    }

    #user-id{
        visibility: hidden;
    }

    #insert{
        visibility: hidden;
    }

    #mod{
        visibility: hidden;
    }

    #login{
        margin-right: 20px;
    }

    header a:first-of-type{
        margin-left: 50px;
    }

    header div+div{
        margin-right: 50px;
        display: flex;
    }

    #menu-h, #menu-x {
        margin-left:25px;
        cursor: pointer;
    }
</style>
<script>
    let finder = document.getElementById("finder");
    let listLook = document.getElementById("list-look");
    let user = document.getElementById("user-id");
    let menu = document.getElementById("menu");
    let menuH = document.getElementById("menu-h");
    let menuX = document.getElementById("menu-x");
    let barra = document.getElementById("barra");
    let find = document.getElementById("find");
    let toggleNavStatus = false;
    let admin = false;

    //Menú
    if (menu !== null && menuH !== null && menuX !== null) {
        menuH.addEventListener("click", () => {
            menuChange();
        });

        menuX.addEventListener("click", () => {
            menuChange();
        });
    }

    //Filtrar Buscqueda Descripciones
    finder.addEventListener("input", () => {
        burcarPorNombre(finder.value);
    });

//    finder.addEventListener("blur",()=>{
//        listLook.style.display = "none";
//    });

    find.addEventListener("click", () => {
        barra.submit();
    });


    function burcarPorNombre(description) {
        listLook.innerHTML = "";
        if (description.length > 2) {
            fetch("?svc=filtrar-producto&name=" + description)
                    .then(res => res.json())
                    .then(json => {
                        if (json.value !== null) {
                            listLook.style.display = "block";
                            json.forEach((producto) => {
                                let li = document.createElement("li");
                                let a = document.createElement("a");
                                listLook.appendChild(a);
                                a.appendChild(li);
                                a.href = "?cmd=producto-consulta&id=" + producto.id;
                                li.textContent = producto.nombre + " - " + producto.precio + "€";
                            });
                        } else {
                            listLook.style.display = "block";
                            let li = document.createElement("li");
                            listLook.appendChild(li);
                            ;
                            li.textContent = "No hay resultados";
                        }
                    }).catch((error) => {
            });
        }

    }

    function menuChange() {
        let menuUl = document.querySelector("#menu ul");
        if (toggleNavStatus === false) {
            menuUl.style.visibility = "visible";
            menu.style.width = "200px";
            toggleNavStatus = true;
            menuX.style.display = "block";
            menuH.style.display = "none";
        } else {
            menuUl.style.visibility = "hidden";
            menu.style.width = "0";
            toggleNavStatus = false;
            menuX.style.display = "none";
            menuH.style.display = "block";
        }
    }

    function consultarAdmin() {
        fetch("?svc=consultar-grupo")
                .then(res => res.json())
                .then(json => {
                    if (json.admin) {
                        admin = true;
                    } else {
                        admin = false;
                    }
                });
    }
    ;
</script>