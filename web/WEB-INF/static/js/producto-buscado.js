/* global fetch*/
let cards = document.getElementById("cards");
let name = document.getElementById("name");
let pag = document.getElementById("pag");
//Cap 32 units
const LIMIT = 12;
let offset = 0;
let paginas = 0;

document.addEventListener("DOMContentLoaded", () => {
    contarProductos();
});

//TODO CHAGE ONLY NAME
function mostrarProductos(offset, limit, name) {
    fetch("?svc=productos-paginados-nombre&offset=" + offset + "&limit=" + limit
            + "&name=" + name.textContent)
            .then(res => res.json())
            .then(json => {
                cards.innerHTML = "";
                console.log(paginas);
                if (json.length > 1) {
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
                        name.textContent = producto.nombre;
                        price.textContent = (producto.precio - (producto.precio * producto.descuento / 100)).toFixed(2) + " €";
                        del.textContent = (producto.precio).toFixed(2) + " ";
                        em.textContent = " €";
                        desc.textContent = producto.descuento + "%";

                        delDiv.style.display = "flex";
                        del.style.marginRight = "5px";

                        pag.innerHTML = "";
                        if (paginas > 1) {
                            let btnI = document.createElement("a");
                            pag.appendChild(btnI);
                            btnI.textContent = "<<";
                            btnI.addEventListener("click", () => {
                                offset = 0;
                                let limit = LIMIT;

                                mostrarProductos(offset, limit, name);
                            });
                            for (let i = 0; i < paginas; i++) {
                                let btn = document.createElement("a");
                                pag.appendChild(btn);
                                btn.textContent = i + 1;
                                if ((offset / LIMIT) === i) {
                                    btn.style.backgroundColor = "red";
                                }
                                btn.addEventListener("click", () => {
                                    offset = LIMIT * i;
                                    let limit = LIMIT;

                                    mostrarProductos(offset, limit, name);
                                });
                            }
                            let btnF = document.createElement("a");
                            pag.appendChild(btnF);
                            btnF.textContent = ">>";
                            btnF.addEventListener("click", () => {
                                let limit = LIMIT;

                                mostrarProductos((Math.ceil(paginas) - 1) * LIMIT, limit, name);
                            });
                        } else {
                            pag.style.display = "none";
                        }
                    });
                } else {
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
                    a.href = "?cmd=producto-consulta&id=" + json.id;
                    card.setAttribute("class", "card");
                    img.src = json.imagen;
                    name.textContent = json.nombre;
                    price.textContent = (json.precio - (json.precio * json.descuento / 100)).toFixed(2) + " €";
                    del.textContent = json.precio + " ";
                    em.textContent = " €";
                    desc.textContent = json.descuento + "%";

                    delDiv.style.display = "flex";
                    del.style.marginRight = "5px";

                }

            });
}

//TODO CHAGE ONLY NAME
function contarProductos() {
    fetch("?svc=contar-productos-nombre&name=" + name.textContent)
            .then(res => res.json())
            .then(json => {
                if (json.value !== null) {
                    paginas = json.paginas / LIMIT;
                } else {
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