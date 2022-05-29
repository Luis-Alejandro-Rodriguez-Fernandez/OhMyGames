/* global fetch */

let cards = document.getElementById("cards");
let pag = document.getElementById("pag");
let cat = document.getElementById("cat");
let des = document.getElementById("des");
let tipo = document.getElementById("tipo");
let min = document.getElementById("min");
let max = document.getElementById("max");
let btnPre = document.getElementById("btn-pre");
let usuario = document.getElementById("user-id");
let insert = document.getElementById("insert");
let mod = document.getElementById("mod");
let formMail = document.getElementById("formMail");

//Cap 32 units
const LIMIT = 12;
let offset = 0;
let paginas = 0;

//LIMITED INPUTS
min.addEventListener("keypress", (event) => {
    if(event.key === "e"){
        event.preventDefault();
    }
});

min.addEventListener("input", () => {
    if (parseInt(min.value) >= 99) {
        min.value = 99;
    } else if (parseInt(min.value) <= 0) {
        min.value = 0;
    }
});

min.addEventListener("blur", () => {
    if(min.value =="" || min.value == null){
    min.value = 0;
    }
});

max.addEventListener("keypress", (event) => {
    if(event.key === "e"){
        event.preventDefault();
    }
});

max.addEventListener("input", () => {
    if (parseInt(max.value) >= 100) {
        max.value = 100;
    } else if (parseInt(max.value) <= 1) {
        max.value = 1;
    }
});

max.addEventListener("blur", () => {
    if(max.value =="" || max.value == null){
    max.value = 100;
    }
});

document.addEventListener("DOMContentLoaded", () => {
    if (usuario !== null) {
        consultarAdmin();
    }

    if (insert !== null && insert.textContent == 1) {
        Swal.fire({
            title: "Oh My Games",
            text: "Producto insertado correctamente",
            icon: "success",
            confirmButtonText: "Continuar"
        }).then((result) => {
            fetch("?svc=cambiar-estado-insert");
        });
    } else if (insert !== null && insert.textContent == 2) {
        Swal.fire({
            title: "Oh My Games",
            text: "Error en la inserción del producto",
            icon: "error",
            confirmButtonText: "Continuar"
        }).then((result) => {
            fetch("?svc=cambiar-estado-insert");
        });
    }

    if (mod !== null && mod.textContent == 1) {
        Swal.fire({
            title: "Oh My Games",
            text: "Producto modificado correctamente",
            icon: "success",
            confirmButtonText: "Continuar"
        }).then((result) => {
            fetch("?svc=cambiar-estado-modify");
        });
    } else if (mod !== null && mod.textContent == 2) {
        Swal.fire({
            title: "Oh My Games",
            text: "Error en la modificación del producto",
            icon: "error",
            confirmButtonText: "Continuar"
        }).then((result) => {
            fetch("?svc=cambiar-estado-modify");
        });
    }

    contarProductos();
});

cat.addEventListener("change", () => {
    contarProductos();
});
des.addEventListener("change", () => {
    contarProductos();
});
tipo.addEventListener("change", () => {
    contarProductos();
});
btnPre.addEventListener("click", () => {
    contarProductos();
});



function mostrarProductos(offset, limit, cat, des, tipo, min, max) {
    fetch("?svc=productos-paginados&offset=" + offset + "&limit=" + limit
            + "&cat=" + cat.value + "&des=" + des.value + "&tipo=" + tipo.value
            + "&min=" + min.value + "&max=" + max.value)
            .then(res => res.json())
            .then(json => {
                if (json.value !== null) {
                    cards.innerHTML = "";

                    //Admin
                    if (admin && offset === 0) {
                        let card = document.createElement("div");
                        let a = document.createElement("a");
                        let ico = document.createElement("h4");
                        let p = document.createElement("p");

                        cards.appendChild(a);
                        a.appendChild(card);
                        card.appendChild(ico);
                        card.appendChild(p);

                        a.href = "?cmd=producto-insertar";
                        ico.textContent = "+";
                        a.setAttribute("id", "admin-add");
                        card.setAttribute("class", "card");
                        p.textContent = "Añadir prducto";
                    }

                    //Lista de productos
                    json.forEach((producto) => {

                        //Creacion de elementos
                        let card = document.createElement("div");
                        let cardDiv1 = document.createElement("div");
                        let cardDiv2 = document.createElement("div");
                        let prcDiv = document.createElement("div");
                        let delDiv = document.createElement("div");
                        let name = document.createElement("h3");
                        let price = document.createElement("p");
                        let img = document.createElement("img");
                        let desc = document.createElement("span");
                        let a = document.createElement("a");
                        let del = document.createElement("del");
                        let em = document.createElement("p");

                        //Enganche de elementos
                        cards.appendChild(a);

                        a.appendChild(card);
                        card.appendChild(cardDiv1);
                        card.appendChild(cardDiv2);
                        cardDiv1.appendChild(img);
                        cardDiv1.appendChild(desc);
                        cardDiv2.appendChild(name);
                        cardDiv2.appendChild(prcDiv);
                        prcDiv.appendChild(delDiv);
                        delDiv.appendChild(del);
                        delDiv.appendChild(em);
                        prcDiv.appendChild(price);

                        //Personalizacion de elementos
                        a.href = "?cmd=producto-consulta&id=" + producto.id;
                        card.setAttribute("class", "card");
                        img.src = producto.imagen;
                        desc.textContent = producto.descuento + "%";
                        name.textContent = producto.nombre;
                        del.textContent = (producto.precio).toFixed(2) + " ";
                        em.textContent = " €";
                        price.textContent = (producto.precio - (producto.precio * producto.descuento / 100)).toFixed(2) + " €";

                        delDiv.style.display = "flex";
                        del.style.marginRight = "5px";

                    });

                    //Paginacion
                    pag.innerHTML = "";
                    if (paginas > 1) {
                        let btnI = document.createElement("a");
                        pag.appendChild(btnI);
                        btnI.textContent = "<<";
                        btnI.addEventListener("click", () => {
                            offset = 0;
                            let limit = LIMIT;
                            if (admin && offset === 0) {
                                limit = 11;
                            }
                            mostrarProductos(offset, limit, cat, des, tipo, min, max);
                        });
                        for (let i = 0; i < paginas; i++) {
                            let btn = document.createElement("a");
                            pag.appendChild(btn);
                            btn.textContent = i + 1;
                            btn.addEventListener("click", () => {
                                offset = LIMIT * i;
                                let limit = LIMIT;
                                if (admin && offset === 0) {
                                    limit = 11;
                                }
                                mostrarProductos(offset, limit, cat, des, tipo, min, max);
                            });
                        }
                        let btnF = document.createElement("a");
                        pag.appendChild(btnF);
                        btnF.textContent = ">>";
                        btnF.addEventListener("click", () => {
                            let limit = LIMIT;
                            if (admin && offset === 0) {
                                limit = 11;
                            }
                            mostrarProductos((Math.ceil(paginas) - 1) * LIMIT, limit, cat, des, tipo, min, max);
                        });
                    }
                } else {
                    pag.innerHTML = "";
                    cards.textContent = "No hay resultados";
                }
            });
}

function contarProductos() {
    fetch("?svc=contar-productos&cat=" + cat.value + "&des=" + des.value
            + "&tipo=" + tipo.value + "&min=" + min.value + "&max=" + max.value)
            .then(res => res.json())
            .then(json => {
                if (json.value !== null) {
                    paginas = json.paginas / LIMIT;
                }
                let limit = LIMIT;
                if (admin && offset === 0) {
                    limit = 11;
                }

                cards.innerHTML = "";
                mostrarProductos(offset, limit, cat, des, tipo, min, max);
            });
}


//let test = document.getElementById("test");
//
//test.addEventListener("click",()=>{
//

//   
////   formMail.appendChild(user);
////   formMail.appendChild(msg);
////   formMail.appendChild(userMail);
//   
////   user.setAttribute("id","user_name");
////   msg.setAttribute("id","message");
////   userMail.setAttribute("id","user_email");
//   
////   formMail.style.position="absolute";
////   formMail.style.zIndex="-15";
////   formMail.style.visibility="hidden";
//   

//   
//   enviarEmail();
//   
//});
