/* global fetch*/
let cards = document.getElementById("cards");
let name = document.getElementById("name");
//Cap 32 units
const LIMIT = 12;
let offset = 0;
let paginas = 0;

document.addEventListener("DOMContentLoaded",()=>{
    contarProductos(); 
});

//TODO CHAGE ONLY NAME
function mostrarProductos(offset, limit, name) {
    fetch("?svc=productos-paginados-nombre&offset=" + offset + "&limit=" + limit
            + "&name=" +name.textContent)
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
                        let cardDiv = document.createElement("div");
                        let delDiv = document.createElement("div");
                        let name = document.createElement("h3");
                        let price = document.createElement("p");
                        let img = document.createElement("img");
                        let a = document.createElement("a");
                        let del = document.createElement("del");
                        let em = document.createElement("p");
                        let desc = document.createElement("span");

                        //Enganche de elementos
                        cards.appendChild(a);

                        a.appendChild(card);
                        card.appendChild(img);
                        card.appendChild(cardDiv);
                        cardDiv.appendChild(name);
                        cardDiv.appendChild(price);
                        cardDiv.appendChild(delDiv);
                        delDiv.appendChild(desc);
                        delDiv.appendChild(del);
                        delDiv.appendChild(em);

                        //Personalizacion de elementos
                        a.href = "?cmd=producto-consulta&id=" + producto.id;
                        card.setAttribute("class", "card");
                        img.src = producto.imagen;
                        name.textContent = producto.nombre;
                        price.textContent = (producto.precio - (producto.precio * producto.descuento / 100)).toFixed(2) + " €";
                        del.textContent = producto.precio + " ";
                        em.textContent = " €";
                        desc.textContent = producto.descuento + "%";

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

//TODO CHAGE ONLY NAME
function contarProductos() {
    fetch("?svc=contar-productos-nombre&name="+name.textContent)
            .then(res => res.json())
            .then(json => {
                if (json.value !== null) {
                    paginas = json.paginas / LIMIT;
                }else{
//                    paginas = 1;
                }
                let limit = LIMIT;
                if (admin && offset === 0) {
                    limit = 11;
                }

                cards.innerHTML = "";
                mostrarProductos(offset, limit, name);
            });
}