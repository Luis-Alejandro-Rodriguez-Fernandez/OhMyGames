/* global fetch */

let btnDel = document.getElementById("btn-del");
let main = document.getElementById("main");
let comprar = document.getElementById("comprar");
let importeCont = document.getElementById("importe");
let payCont = document.getElementById("paymentDiv");
let paypalCheck = document.getElementById("paypalInp");
let creditCheck = document.getElementById("credit");
let paypalCont = document.getElementById("paypal-container");
let creditCont = document.getElementById("credit-card");
let errorDiv = document.querySelector("#error");
let errorP = document.querySelector("#error p");
let mainCont = document.querySelector("main");
let aside = document.getElementById("aside");
let comprarCard = document.getElementById("comprarCard");
let tarjeta = document.getElementById("tarjeta");
let mes = document.getElementById("mes");
let year = document.getElementById("year");
let seg = document.getElementById("seg");
let carrito = [];
let importe = 0;
let usuarioNombre;
let usuarioEmail;
let carritoMail;
//Fecha
let d = new Date();
let mesd = d.getMonth();
let yeard = d.getFullYear();
paypalCheck.addEventListener("click", () => {
    paypalCont.style.display = "block";
    creditCont.style.display = "none";
    errorDiv.style.visibility = "hidden";
});
creditCheck.addEventListener("click", () => {
    paypalCont.style.display = "none";
    creditCont.style.display = "block";
    errorDiv.style.visibility = "hidden";
});
document.addEventListener("DOMContentLoaded", () => {
    traerUsuario();
    carritoUsuario();
});
comprar.addEventListener("click", () => {
    comprarCarrito();
    comprar.style.display = "none";
});
comprarCard.addEventListener("click", () => {
    if (
            validarCampoVacio(tarjeta, errorDiv, errorP, "El campo no puede estar vacío") &&
            validarTarjeta(tarjeta.value) &&
            validarCampoVacio(mes, errorDiv, errorP, "El campo no puede estar vacío") &&
            validarMes(mes.value) &&
            validarCampoVacio(year, errorDiv, errorP, "El campo no puede estar vacío") &&
            validarYear(year.value) &&
            validarFecha(mes.value, "20" + year.value) &&
            validarCampoVacio(seg, errorDiv, errorP, "El campo no puede estar vacío") &&
            validarSeg(seg.value)
            ) {
        fetch("?svc=comprar&importe=" + importe)
                .then(res => res.json())
                .then(json => {
                    if (json.value !== false) {
                        if (json.value) {
                            Swal.fire({
                                title: "Oh My Games",
                                text: "La operacion se ha finalizado con éxito",
                                icon: "success",
                                confirmButtonText: "Continuar"
                            }).then((result) => {

                                payCont.style.display = "none";
//                                carritoUsuario();

                                let user = document.getElementById("user_name");
                                let msg = document.getElementById("message");
                                let userMail = document.getElementById("user_email");
                                user.value = usuarioNombre;
                                msg.textContent = carritoMail;
                                userMail.value = usuarioEmail;
                                btn.click();
                            });
                        } else {
                            Swal.fire({
                                title: "Oh My Games",
                                text: "La operacion no pudo completarse, intentelo de nuevo más tarde",
                                icon: "error",
                                confirmButtonText: "Continuar"
                            }).then((result) => {
                                window.location = "?cmd=landing";
                            });
                        }
                    } else {
                        Swal.fire({
                            title: "Oh My Games",
                            text: "Estamos teniendo problemas con el servidor, intentelo de nuevo más tarde",
                            icon: "error",
                            confirmButtonText: "Continuar"
                        }).then((result) => {
                            window.location = "?cmd=landing";
                        });
                    }
                });
    }
});
function borrarProductoCarrito(nombre) {
    fetch("?svc=eleminar-producto-carrito&producto=" + nombre)
            .then(res => res.json())
            .then(json => {
                if (json.vlaue === null) {

                } else {
                    carritoUsuario();
                }
            });
}

function carritoUsuario() {
    main.innerHTML = "<img id='loading' src='public/img/loading.gif'>";
    fetch("?svc=carrito")
            .then(res => res.json())
            .then(json => {
                carrito = json;

                if (json.value !== null) {
                    aside.style.visibility="visible";
//                    mainCont.style.visibility="visible";
                    importe = 0;
                    //Formatear productos mail
                    carritoMail = "";
                    if (carrito.length > 1) {

                        for (let i = 0; i < carrito.length; i++) {
                            if (i === 0) {
                                carritoMail += `- ${carrito[i].producto.nombre} | ${(carrito[i].producto.precio - (carrito[i].producto.precio * carrito[i].producto.descuento / 100)).toFixed(2)} €`;
                            } else if (i === (carrito.length - 1)) {
                                carritoMail += `<br>- ${carrito[i].producto.nombre} | ${(carrito[i].producto.precio - (carrito[i].producto.precio * carrito[i].producto.descuento / 100)).toFixed(2)} €`;
                            } else {
                                carritoMail += "<br>" + `- ${carrito[i].producto.nombre} | ${(carrito[i].producto.precio - (carrito[i].producto.precio * carrito[i].producto.descuento / 100)).toFixed(2)} €`;
                            }
                        }
                        let cont = 0;
                        main.innerHTML = "";
                        json.forEach((prod) => {
                            let section = document.createElement("section");
                            let a = document.createElement("a");
                            let imgDiv = document.createElement("div");
                            let preDiv = document.createElement("div");
                            let img = document.createElement("img");
                            let desc = document.createElement("span");
                            let name = document.createElement("h3");
                            let price = document.createElement("p");
                            let del = document.createElement("del");
                            let x = document.createElement("h5");
                            cont++;
                            main.appendChild(section);
                            section.appendChild(a);
                            section.appendChild(x);
                            a.appendChild(imgDiv);
                            imgDiv.appendChild(img);
                            imgDiv.appendChild(desc);
                            a.appendChild(name);
                            a.appendChild(preDiv);
                            preDiv.appendChild(del);
                            preDiv.appendChild(price);
                            importe += parseFloat((prod.producto.precio - (prod.producto.precio * prod.producto.descuento / 100)).toFixed(2));
                            a.href = "?cmd=producto-consulta&id=" + prod.producto.id;
                            img.src = prod.producto.imagen;
                            desc.textContent = prod.producto.descuento + "%";
                            name.textContent = prod.producto.nombre;
                            price.textContent = (prod.producto.precio - (prod.producto.precio * prod.producto.descuento / 100)).toFixed(2) + " €";
                            del.textContent = (prod.producto.precio).toFixed(2) + "€";
                            x.textContent = "X";
                            x.addEventListener("click", () => {
                                borrarProductoCarrito(prod.producto.nombre);
                            });
                        });


                    } else {
                        main.innerHTML = "";
                        carritoMail = `- ${carrito[0].producto.nombre} | ${(carrito[0].producto.precio - (carrito[0].producto.precio * carrito[0].producto.descuento / 100)).toFixed(2)} €`;

                        let section = document.createElement("section");
                        let name = document.createElement("h3");
                        let price = document.createElement("p");
                        let img = document.createElement("img");
                        let a = document.createElement("a");
                        let del = document.createElement("del");
                        let desc = document.createElement("span");
                        let x = document.createElement("h5");
                        main.appendChild(section);
                        section.appendChild(a);
                        section.appendChild(x);
                        a.appendChild(name);
                        a.appendChild(img);
                        a.appendChild(price);
                        a.appendChild(del);
                        a.appendChild(desc);
                        importe += parseFloat((json[0].producto.precio - (json[0].producto.precio * json[0].producto.descuento / 100)).toFixed(2));
                        a.href = "?cmd=producto-consulta&id=" + json[0].producto.id;
                        img.src = json[0].producto.imagen;
                        name.textContent = json[0].producto.nombre;
                        price.textContent = (json[0].producto.precio - (json[0].producto.precio * json[0].producto.descuento / 100)).toFixed(2) + " €";
                        del.textContent = (json[0].producto.precio).toFixed(2);
                        desc.textContent = json[0].producto.descuento + "%";
                        x.textContent = "X";
                        x.addEventListener("click", () => {
                            borrarProductoCarrito(json[0].producto.nombre);
                        });

                    }
                        paymentMethod();
                        importeCont.textContent = importe.toFixed(2) + " €";
                } else {
                    aside.style.visibility="hidden";
                    main.innerHTML = "";
                    let h3 = document.createElement("h3");
                    main.appendChild(h3);
                    h3.textContent = "No hay productos almacenados en el carrito";
                    comprar.style.display = "none";
                }

            });
}

function comprarCarrito() {
    payCont.style.display = "block";
}

function validarTarjeta(tarjeta) {
    var tarrgx = /^[0-9]{16}$/;
    if (tarrgx.test(tarjeta)) {
        errorDiv.style.visibility = "hidden";
        return true;
    } else {
        errorP.innerHTML = "debe introducir un valor de 16 digitos";
        errorDiv.style.visibility = "visible";
        return false;
    }
}

function validarSeg(seg) {
    var segrgx = /^[0-9]{3,4}$/;
    if (segrgx.test(seg)) {
        errorDiv.style.visibility = "hidden";
        return true;
    } else {
        errorP.innerHTML = "debe introducir un valor de entre 3 o 4 carácteres";
        errorDiv.style.visibility = "visible";
    }
}

function validarMes(mes) {
    var mesrgx = /^(0[1-9]|1[012])$/;
    if (mesrgx.test(mes)) {
        errorDiv.style.visibility = "hidden";
        return true;
    } else {
        errorP.innerHTML = "debe introducir un valor valido para el mes";
        errorDiv.style.visibility = "visible";
        return false;
    }
}

function validarYear(year) {
    var yearrgx = /^(([1-9])[0-9])$/;
    if (yearrgx.test(year)) {
        errorDiv.style.visibility = "hidden";
        return true;
    } else {
        errorP.innerHTML = "debe introducir un valor valido para el año";
        errorDiv.style.visibility = "visible";
        return false;
    }
}

function validarFecha(mes, year) {
    let fechaOK = false;
    if (parseInt(year) > yeard) {
        fechaOK = true;
    } else if (parseInt(year) == yeard) {
        if (parseInt(mes) > mesd) {
            fechaOK = true;
        }
    }
    return fechaOK;
}


function validarCampoVacio(campo, errorDiv, errorP, msj) {
    if (campo.value === "" || campo.value === null) {
        errorDiv.style.visibility = "visible";
        if (msj === null) {
            errorP.textContent = `El campo ${campo.id} es obligatorio`;
        } else {
            errorP.textContent = msj;
        }
        campo.focus();
        return false;
    } else {
        errorDiv.style.visibility = "hidden";
        return true;
    }
}

function traerUsuario() {
    fetch("?svc=traer-usuario").then(res => res.json())
            .then(json => {
                usuarioNombre = json.usuario;
                usuarioEmail = json.email;
            });
}

////Enviar correo
//    document.getElementById('form').addEventListener('submit', function (event) {
//        event.preventDefault();
//
//        console.log("buu");
//
//        const serviceID = 'default_service';
//        const templateID = 'template_u5mxylk';
//
//        emailjs.sendForm(serviceID, templateID, this)
//                .then(() => {
//                    btn.value = 'Send Email';
//                    alert('Sent!');
//                }, (err) => {
//                    btn.value = 'Send Email';
//                    alert(JSON.stringify(err));
//                });
//    });
