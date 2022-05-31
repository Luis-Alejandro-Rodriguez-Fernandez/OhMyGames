/* global fecth */

let delCat = document.getElementById("delCat");
let main = document.getElementById("main");
let nav = document.getElementById("nav");

const LIMIT = 9;
let offset = 0;
let paginas = 0;

document.addEventListener("DOMContentLoaded", () => {

    contarCategorias();

    if (delCat.textContent !== "null") {

        if (delCat.textContent === "true") {
            Swal.fire({
                title: "Oh My Games",
                text: "La categoría se ha eliminado correctamente",
                icon: "success",
                confirmButtonText: "Continuar"
            }).then((result) => {
                fetch("?svc=cambiar-estado-del-cat");
            });
        } else {
            Swal.fire({
                title: "Oh My Games",
                text: "La categoría no se pudo eliminar",
                icon: "error",
                confirmButtonText: "Continuar"
            }).then((result) => {
                fetch("?svc=cambiar-estado-del-cat");

            });
        }
    } else {

    }
});

function contarCategorias() {
    fetch("?svc=contar-categorias")
            .then(res => res.json())
            .then(json => {
                if (json.value !== null) {
                    paginas = json.paginas / LIMIT;
                }
                let limit = LIMIT;

                main.innerHTML = "";
                mostrarCategorias(offset, limit);
            });
}

function mostrarCategorias(offset, limit) {
    fetch("?svc=categoria-paginas&offset=" + offset + "&limit=" + limit)
            .then(res => res.json())
            .then(json => {
                main.innerHTML = "";
                if (json.length > 1) {
                    json.forEach((categoria) => {

                        let sect = document.createElement("section");
                        let nom = document.createElement("h3");
                        let div = document.createElement("div");
                        let aDel = document.createElement("a");
                        let aMod = document.createElement("a");

                        main.appendChild(sect);
                        sect.appendChild(nom);
                        sect.appendChild(div);

                        div.appendChild(aMod);
                        div.appendChild(aDel);

                        nom.textContent = categoria.nombre;
                        aMod.textContent = "Modificar";
                        aMod.href = "?cmd=categoria-modificar&id=" + categoria.id;
                        aDel.textContent = "Borrar";
                        aDel.href = "?cmd=categoria-borrar&id=" + categoria.id;

                        nav.innerHTML = "";
                        if (paginas > 1) {
                            let btnI = document.createElement("a");
                            nav.appendChild(btnI);
                            btnI.textContent = "<<";
                            btnI.addEventListener("click", () => {
                                offset = 0;
                                mostrarCategorias(offset, limit);
                            });
                            for (let i = 0; i < paginas; i++) {
                                let btn = document.createElement("a");
                                nav.appendChild(btn);
                                btn.textContent = i + 1;
                                btn.addEventListener("click", () => {
                                    offset = LIMIT * i;
                                    let limit = LIMIT;

                                    mostrarCategorias(offset, limit);
                                });
                            }
                            let btnF = document.createElement("a");
                            nav.appendChild(btnF);
                            btnF.textContent = ">>";
                            btnF.addEventListener("click", () => {
                                let limit = LIMIT;

                                mostrarCategorias((Math.ceil(paginas) - 1) * LIMIT, limit);
                            });
                        } else {
                            nav.style.display = "none";
                        }
                    });
                } else {
                    let sect = document.createElement("section");
                    let nom = document.createElement("h3");
                    let div = document.createElement("div");
                    let aDel = document.createElement("a");
                    let aMod = document.createElement("a");

                    main.appendChild(sect);
                    sect.appendChild(nom);
                    sect.appendChild(div);

                    div.appendChild(aMod);
                    div.appendChild(aDel);

                    nom.textContent = json.nombre;
                    aMod.textContent = "Modificar";
                    aMod.href = "?cmd=categoria-modificar&id=" + json.id;
                    aDel.textContent = "Borrar";
                    aDel.href = "?cmd=categoria-borrar&id=" + json.id;
                    nav.innerHTML = "";
                    if (paginas > 1) {
                        let btnI = document.createElement("a");
                        nav.appendChild(btnI);
                        btnI.textContent = "<<";
                        btnI.addEventListener("click", () => {
                            offset = 0;
                            mostrarCategorias(offset, limit);
                        });
                        for (let i = 0; i < paginas; i++) {
                            let btn = document.createElement("a");
                            nav.appendChild(btn);
                            btn.textContent = i + 1;
                            btn.addEventListener("click", () => {
                                offset = LIMIT * i;
                                let limit = LIMIT;

                                mostrarCategorias(offset, limit);
                            });
                        }
                        let btnF = document.createElement("a");
                        nav.appendChild(btnF);
                        btnF.textContent = ">>";
                        btnF.addEventListener("click", () => {
                            let limit = LIMIT;

                            mostrarCategorias((Math.ceil(paginas) - 1) * LIMIT, limit);
                        });
                    }
                }
            });
}