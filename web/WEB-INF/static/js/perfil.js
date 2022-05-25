/* global fetch  */
let btnPerfil = document.getElementById("btn-perfil");
let btnFav = document.getElementById("btn-fav");
let btnBib = document.getElementById("btn-bib");
let favSection = document.getElementById("favs");
let bibSection = document.getElementById("bib");
let perfilSection = document.getElementById("perfil");


document.addEventListener("DOMContentLoaded", () => {
    traerFavoritos();
    traerBiblioteca();
});

btnPerfil.addEventListener("click", () => {
    perfilSection.style.display = "block";
    favSection.style.display = "none";
    bibSection.style.display = "none";
});
btnFav.addEventListener("click", () => {
    perfilSection.style.display = "none";
    bibSection.style.display = "none";
    favSection.style.display = "grid";
});
btnBib.addEventListener("click", () => {
    perfilSection.style.display = "none";
    bibSection.style.display = "block";
    favSection.style.display = "none";
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


function traerFavoritos() {
    fetch("?svc=traer-favoritos")
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
                            let h3 = document.createElement("h3");
                            let del = document.createElement("del");
                            let price = document.createElement("p");
                            let desc = document.createElement("span");
                            let X = document.createElement("h4");

                            favSection.appendChild(card);
                            card.appendChild(a);
                            a.appendChild(img);
                            card.appendChild(desc);
                            a.appendChild(h3);
                            a.appendChild(del);
                            a.appendChild(price);
                            card.appendChild(X);

                            //Personalizacion de elementos
                            a.href = "?cmd=producto-consulta&id=" + fav.id;
                            card.setAttribute("class", "card");
                            img.src = fav.imagen;
                            h3.textContent = fav.nombre;
                            price.textContent = (fav.precio - (fav.precio * fav.descuento / 100)).toFixed(2) + " €";
                            del.textContent = fav.precio + "€";
                            desc.textContent = fav.descuento + "%";
                            X.textContent = "X";
                            X.addEventListener("click", () => {
                                quitarFavorito(fav.id);
                            });
                        });

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

function traerBiblioteca() {
    fetch("?svc=traer-biblioteca")
            .then(res => res.json())
            .then(json => {
                if (json.value !== null) {
                    bibSection.innerHTML = "";
                    try {
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
                            a.href = "?cmd=producto-consulta&id=" + bib.id;
                            card.setAttribute("class", "card");
                            img.src = bib.imagen;
                            h3.textContent = bib.nombre;

                        });

                    } catch (e) {
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
                } else {
                    bibSection.textContent = "No hay resultados";
                }
            });
}


