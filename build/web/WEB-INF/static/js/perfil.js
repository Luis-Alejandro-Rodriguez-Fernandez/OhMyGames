/* global fetch  */
let btnFav = document.getElementById("btn-fav");
let btnBib = document.getElementById("btn-bib");
let favSection = document.getElementById("favs");
let bibSection = document.getElementById("bib");
let perfilSection = document.getElementById("perfil");

const LIMITBIB = 6;
let offsetBib = 0;
let paginasBib = 0;
const LIMITFAV = 10;
let offsetFav = 0;
let paginasFav = 0;

document.addEventListener("DOMContentLoaded", () => {
    contarFavoritos();
    contarBiblioteca();
});

btnFav.addEventListener("click", () => {
    bibSection.style.display = "none";
    favSection.style.display = "grid";

    btnFav.className = "select";
    btnBib.className = "";
});

btnBib.addEventListener("click", () => {
    bibSection.style.display = "grid";
    favSection.style.display = "none";

    btnBib.className = "select";
    btnFav.className = "";
});



function quitarFavorito(prod) {
    if (user !== null) {
        fetch(`?svc=quitar-favorito&prod=${prod}`)
                .then(res => res.json())
                .then(json => {
                    traerFavoritos();
                    btnFav.click();
                });
    }
}

function contarFavoritos() {
    fetch("?svc=contar-favoritos")
            .then(res => res.json())
            .then(json => {

                if (json.value !== null) {
                    paginasFav = json.paginas / LIMITFAV;
                }
                let limit = LIMITFAV;
                favSection.innerHTML = "";
                traerFavoritos(offsetFav, limit);
            });
}

function traerFavoritos(offsetFav, limitFav) {
    fetch("?svc=traer-favoritos&offset=" + offsetFav + "&limit=" + limitFav)
            .then(res => res.json())
            .then(json => {
                if (json.value !== null) {
                    favSection.innerHTML = "";
                    try {
                        favSection.innerHTML = "";
                        json.forEach((fav) => {

                            let a = document.createElement("a");
                            let card = document.createElement("div");
                            let img = document.createElement("img");
                            let X = document.createElement("h4");

                            favSection.appendChild(card);
                            card.appendChild(a);
                            a.appendChild(img);
                            a.appendChild(X);

                            //Personalizacion de elementos
                            a.href = "?cmd=producto-consulta&id=" + fav.id;
                            card.setAttribute("class", "card");
                            img.src = fav.imagen;
                            X.textContent = "X";
                            X.addEventListener("click", () => {
                                quitarFavorito(fav.id);
                            });
                        });
                        //paginacion
                        nav = document.createElement("nav");
                        console.log(paginasFav);
                        favSection.appendChild(nav);
                        if (paginasFav > 1) {
                            let btnI = document.createElement("a");
                            nav.appendChild(btnI);
                            btnI.textContent = "<<";
                            btnI.addEventListener("click", () => {
                                let limitFav = LIMITFAV;
                                offsetFav = 0;
                                traerFavoritos(offsetFav, limitFav);
                            });
                            for (let i = 0; i < paginasFav; i++) {
                                let btn = document.createElement("a");
                                nav.appendChild(btn);
                                btn.textContent = i + 1;

                                if ((offsetFav / LIMITFAV) === i) {
                                    btn.style.backgroundColor = "#00B4D8";
                                }
                                btn.addEventListener("click", () => {
                                    offsetFav = LIMITFAV * i;
                                    let limitFav = LIMITFAV;

                                    traerFavoritos(offsetFav, limitFav);
                                });
                            }
                            let btnF = document.createElement("a");
                            nav.appendChild(btnF);
                            btnF.textContent = ">>";
                            btnF.addEventListener("click", () => {
                                let offsetFav = LIMITFAV;

                                traerFavoritos((Math.ceil(paginasFav) - 1) * LIMITFAV, offsetFav);
                            });
                        } else {
                            nav.style.display = "none";
                        }
                    } catch (e) {
                        favSection.innerHTML = "";

                        let a = document.createElement("a");
                        let card = document.createElement("div");
                        let img = document.createElement("img");
                        let h3 = document.createElement("h3");
                        let del = document.createElement("del");
                        let price = document.createElement("p");
                        let desc = document.createElement("span");
                        let X = document.createElement("h4");

                        favSection.appendChild(card);
                        card.appendChild(a);
                        a.appendChild(img);
                        a.appendChild(h3);
                        a.appendChild(del);
                        a.appendChild(price);
                        a.appendChild(desc);
                        card.appendChild(X);

                        //Personalizacion de elementos
                        a.href = "?cmd=producto-consulta&id=" + json.id;
                        card.setAttribute("class", "card");
                        img.src = json.imagen;
                        name.textContent = json.nombre;
                        price.textContent = (json.precio - (json.precio * json.descuento / 100)).toFixed(2) + " €";
                        del.textContent = json.precio;
                        desc.textContent = json.descuento + "%";
                        X.textContent = "X";
                        X.addEventListener("click", () => {
                            quitarFavorito(json.id);
                        });

                    }
                } else {
                    favSection.textContent = "No hay resultados";
                }
            });
}

function contarBiblioteca() {
    fetch("?svc=contar-biblioteca")
            .then(res => res.json())
            .then(json => {
                if (json.value !== null) {
                    paginasBib = json.paginas / LIMITBIB;
                }
                let limit = LIMITBIB;

                bibSection.innerHTML = "";
                traerBiblioteca(offsetBib, limit);
            });
}

function traerBiblioteca(offsetBib, limitBib) {
    fetch("?svc=traer-biblioteca&offset=" + offsetBib + "&limit=" + limitBib)
            .then(res => res.json())
            .then(json => {
                console.log(json)
                if (json.value !== null) {
                    bibSection.innerHTML = "";
                    try {
                        if(json.length > 1){
                        bibSection.innerHTML = "";
                        json.forEach((bib) => {

                            let a = document.createElement("a");
                            let card = document.createElement("div");
                            let img = document.createElement("img");
                            let h3 = document.createElement("h3");


                            bibSection.appendChild(card);
                            card.appendChild(a);
                            a.appendChild(img);
                            a.appendChild(h3);


                            //Personalizacion de elementos
                            console.log("?cmd=producto-consulta&id=" + bib.id)

                            a.href = "?cmd=producto-consulta&id=" + bib.id;
                            card.setAttribute("class", "card");
                            img.src = bib.imagen;
                            h3.textContent = bib.nombre;


                        });
                        //paginacion
                        nav = document.createElement("nav");
                        console.log(paginasBib);
                        bibSection.appendChild(nav);
                        if (paginasBib > 1) {
                            let btnI = document.createElement("a");
                            nav.appendChild(btnI);
                            btnI.textContent = "<<";
                            btnI.addEventListener("click", () => {
                                let limitBib = LIMITBIB;
                                offsetBib = 0;
                                traerBiblioteca(offsetBib, limitBib);
                            });
                            for (let i = 0; i < paginasBib; i++) {
                                let btn = document.createElement("a");
                                nav.appendChild(btn);
                                btn.textContent = i + 1;

                                if ((offsetBib / LIMITBIB) === i) {
                                    btn.style.backgroundColor = "#00B4D8";
                                }
                                btn.addEventListener("click", () => {
                                    offsetBib = LIMITBIB * i;
                                    let limitBib = LIMITBIB;

                                    traerBiblioteca(offsetBib, limitBib);
                                });
                            }
                            let btnF = document.createElement("a");
                            nav.appendChild(btnF);
                            btnF.textContent = ">>";
                            btnF.addEventListener("click", () => {
                                let limit = LIMITBIB;

                                traerBiblioteca((Math.ceil(paginasBib) - 1) * LIMITBIB, limit);
                            });
                        } else {
                            nav.style.display = "none";
                        }
                    }else{
                      bibSection.innerHTML = "";

                        let a = document.createElement("a");
                        let card = document.createElement("div");
                        let img = document.createElement("img");
                        let h3 = document.createElement("h3");


                        bibSection.appendChild(card);
                        card.appendChild(a);
                        a.appendChild(img);
                        a.appendChild(h3);


                        //Personalizacion de elementos
                        a.href = "?cmd=producto-consulta&id=" + json.id;
                        card.setAttribute("class", "card");
                        img.src = json.imagen;
                        h3.textContent = json.nombre;
   
                    }
                    } catch (e) {
                                           bibSection.textContent = "No hay resultados";
                                           console.log(e)
                    }
                } else {
                    bibSection.textContent = "No hay resultados";
                }
            });
}


