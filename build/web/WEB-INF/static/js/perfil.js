/* global fetch  */
let btnFav = document.getElementById("btn-fav");
let btnBib = document.getElementById("btn-bib");
let favSection = document.getElementById("favs");
let bibSection = document.getElementById("bib");
let perfilSection = document.getElementById("perfil");


document.addEventListener("DOMContentLoaded", () => {
    traerFavoritos();
    traerBiblioteca();
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


