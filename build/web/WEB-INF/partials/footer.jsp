<%-- 
    Document   : footer
    Created on : 2 abr. 2022, 20:55:46
    Author     : Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer>
    <div>
        <a href="https://twitter.com/" target="_blank"><img src="public/img/twitter.png" alt="Twitter"/></a>
        <a href="https://www.instagram.com/" target="_blank"><img src="public/img/instagram.png" alt="Instagram"/></a>
    </div>
    <div>
        <h3>© Copyright 2022.</h3>
        <h3>Luis Alejandro Rodríguez Fernández.</h3>
    </div>
</footer>
<style>
    footer{
        background-color: var(--azuloscuro01);
        border-top: 1px solid var(--bordeblanco);
        color: white;
        
        font-size: 12px;
        height: 60px;
        
        display: flex;
        justify-content: space-around;
        align-items: center;
        text-align: center;
    }
    
    footer > div img {
        width: 30px;
        height: 30px;
    }

    footer h3 {
        font-weight: bold;
    }

    footer a {
        margin: 0 16px;
    }
</style>