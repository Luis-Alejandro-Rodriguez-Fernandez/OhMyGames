/* global fecth */

let delDes = document.getElementById("delDes");
let main = document.getElementById("main");
let nav = document.getElementById("nav");

const LIMIT = 9;
let offset = 0;
let paginas = 0;

document.addEventListener("DOMContentLoaded", () => {
    contarDesarrolladoras();

    if (delDes.textContent !== "null") {

        if (delDes.textContent === "true") {
            Swal.fire({
                title: "Oh My Games",
                text: "La desarrolladora se ha eliminado correctamente",
                icon: "success",
                confirmButtonText: "Continuar"
            }).then((result) => {
                fetch("?svc=cambiar-estado-del-des");
            });
        } else {
            Swal.fire({
                title: "Oh My Games",
                text: "La desarrolladora no se pudo eliminar",
                icon: "error",
                confirmButtonText: "Continuar"
            }).then((result) => {
                fetch("?svc=cambiar-estado-del-des");

            });
        }
    } else {

    }
});

function contarDesarrolladoras() {
    fetch("?svc=contar-desarrolladoras")
            .then(res => res.json())
            .then(json => {
                if (json.value !== null) {
                    paginas = json.paginas / LIMIT;
                }
                let limit = LIMIT;

                main.innerHTML = "";
                mostrarDesarrolladoras(offset, limit);
            });
}

function mostrarDesarrolladoras(offset, limit) {
    main.innerHTML = "<img id='loading' src='public/img/loading.gif'>";
    fetch("?svc=desarrolladora-paginas&offset=" + offset + "&limit=" + limit)
            .then(res => res.json())
            .then(json => {
                main.innerHTML = "";
                if (json.length > 1) {
                    json.forEach((desarrolladora) => {

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

                        nom.textContent = desarrolladora.nombre;
                        aMod.textContent = "Modificar";
                        aMod.href = "?cmd=desarrolladora-modificar&id=" + desarrolladora.id;
                        aDel.textContent = "Borrar";
                        aDel.href = "#";
                        aDel.addEventListener("click", () => {
                            Swal.fire({
                                title: 'Quieres eliminar la desarrolladora ' + desarrolladora.nombre + '?',
                                text: "No ser?? posible revertir esta acci??n",
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonColor: '#3085d6',
                                cancelButtonColor: '#d33',
                                confirmButtonText: 'Eliminar'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    window.location = "?cmd=desarrolladora-borrar&id=" + desarrolladora.id;
                                }
                            });
                        });

                        nav.innerHTML = "";
                        if (paginas > 1) {
                            console.log(paginas);
                            let btnI = document.createElement("a");
                            nav.appendChild(btnI);
                            btnI.textContent = "<<";
                            btnI.addEventListener("click", () => {
                                offset = 0;
                                mostrarDesarrolladoras(offset, limit);
                            });
                            for (let i = 0; i < paginas; i++) {
                                let btn = document.createElement("a");
                                nav.appendChild(btn);
                                btn.textContent = i + 1;
                                if ((offset / LIMIT) === i) {
                                    btn.style.backgroundColor = "#00B4D8";
                                }
                                btn.addEventListener("click", () => {
                                    offset = LIMIT * i;
                                    let limit = LIMIT;

                                    mostrarDesarrolladoras(offset, limit);
                                });
                            }
                            let btnF = document.createElement("a");
                            nav.appendChild(btnF);
                            btnF.textContent = ">>";
                            btnF.addEventListener("click", () => {
                                let limit = LIMIT;

                                mostrarDesarrolladoras((Math.ceil(paginas) - 1) * LIMIT, limit);
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
                    aMod.href = "?cmd=desarrolladora-modificar&id=" + json.id;
                    aDel.textContent = "Borrar";
                        aDel.href = "#";
                        aDel.addEventListener("click", () => {
                            Swal.fire({
                                title: 'Quieres eliminar la desarrolladora ' + json.nombre + '?',
                                text: "No ser?? posible revertir esta acci??n",
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonColor: '#3085d6',
                                cancelButtonColor: '#d33',
                                confirmButtonText: 'Eliminar'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    window.location = "?cmd=desarrolladora-borrar&id=" + json.id;
                                }
                            });
                        });

                    nav.innerHTML = "";
                    if (paginas > 1) {
                        console.log(paginas);
                        let btnI = document.createElement("a");
                        nav.appendChild(btnI);
                        btnI.textContent = "<<";
                        btnI.addEventListener("click", () => {
                            offset = 0;
                            mostrarDesarrolladoras(offset, limit);
                        });
                        for (let i = 0; i < paginas; i++) {
                            let btn = document.createElement("a");
                            nav.appendChild(btn);
                            btn.textContent = i + 1;
                            if ((offset / LIMIT) === i) {
                                btn.style.backgroundColor = "#00B4D8";
                            }
                            btn.addEventListener("click", () => {
                                offset = LIMIT * i;
                                let limit = LIMIT;

                                mostrarDesarrolladoras(offset, limit);
                            });
                        }
                        let btnF = document.createElement("a");
                        nav.appendChild(btnF);
                        btnF.textContent = ">>";
                        btnF.addEventListener("click", () => {
                            let limit = LIMIT;
                            mostrarDesarrolladoras((Math.ceil(paginas) - 1) * LIMIT, limit);
                        });
                    }
                }
            });
}