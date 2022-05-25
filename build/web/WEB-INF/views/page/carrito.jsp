<%-- 
    Document   : carrito
    Created on : 25 abr. 2022, 13:18:05
    Author     : Luis Alejandro Rodríguez Fernández - luisalejandro.rodriguez.alum@iescamp.es
--%>
<%@page import="org.japo.java.entities.Carrito"%>
<%@page import="java.util.List"%>

<%
    List<Carrito> cs = (List<Carrito>) request.getAttribute("lista-carrito");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
        <link rel="stylesheet" href="public/css/general.css"/>
        <link rel="stylesheet" href="public/css/carrito.css"/>
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />
        <title>Carrito - Oh My Games</title>
    </head>
    <body>
        <jsp:include page="../../partials/header.jsp"/>
        <main>
            <div id="main"></div>

            <%if (cs != null || cs.size() != 0) {%>
            <div id="aside">
                <p id="importe"></p>
                <p id="comprar">Comprar</p>

                <div id="paymentDiv">
                    <div id="ratio">
                        <div>
                            <input type="radio" id="paypalInp" name="pay">
                            <label for="paypalInp">Paypal</label>
                        </div>

                        <div>
                            <input type="radio" id="credit" name="pay">
                            <label for="credit">Tarjeta de credito</label>
                        </div>

                    </div>
                    <div id="paypal-container">
                        <p id="paypal-button-container"></p>
                    </div>
                    <div id="error"><p></p></div>  
                    <div id="credit-card">
                        <div class="fieldset">
                            <label for="tarjeta">Nº Tarjeta</label>
                            <input type="text" id="tarjeta" name="tarjeta"  />
                        </div>
                        <div class="fieldset">
                            <label for="mes">Caducidad</label>
                            <div>
                                <input type="text" id="mes" name="mes"  placeholder="mm" />
                                <input
                                    type="text"
                                    id="year"
                                    name="year"
                                    placeholder="aa"
                                    />
                            </div>
                        </div>
                        <div class="fieldset">
                            <label for="seg">Cod. Seguridad</label>
                            <input type="text" id="seg" name="seg"  />
                        </div>
                        <p id="comprarCard">Comprar</p>
                    </div>
                </div>

            </div>
            <%}%>
        </main>
        <jsp:include page="../../partials/footer.jsp"/>
        <script src="public/js/carrito.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.all.min.js"></script>
        <script src="https://www.paypal.com/sdk/js?client-id=ARjOwjDpAm4rYz977jEvwBVOVa5Bu0aVqbrfDhGDYZzl7VpTdIuPI1Q7pykvaVvf6xb2gqQsF4qPSAfZ&currency=EUR&disable-funding=credit,card,sofort"></script>
        <script>
            function paymentMethod() {
                //Paypal
                let pp = document.getElementById("paypal-button-container");
                if (pp !== null) {
                    let  impPayment = (importe).toFixed(2) + "";
                    paypal.Buttons({
                        createOrder: function (data, actions) {
                            // Set up the transaction
                            return actions.order.create({
                                purchase_units: [{
                                        amount: {
                                            value: impPayment
                                        }
                                    }]
                            });
                        },

                        onApprove: function (data, actions) {
                            fetch("?svc=comprar&importe=" + impPayment).then(res => res.json())
                                    .then(json => {
                                        if (json.value !== false) {
                                            Swal.fire({
                                                title: "Oh My Games",
                                                text: "La operacion se ha finalizado con éxito",
                                                icon: "success",
                                                confirmButtonText: "Continuar"
                                            }).then((result) => {
                                                carritoUsuario();
                                            });
                                        } else {
                                            Swal.fire({
                                                title: "Oh My Games",
                                                text: "La operacion no pudo completarse, intentelo de nuevo más tarde",
                                                icon: "error",
                                                confirmButtonText: "Continuar"
                                            }).then((result) => {
                                            });
                                        }
                                    });
                        }
                    }).render("#paypal-button-container");
                }
            }

        </script>
    </body>
</html>
