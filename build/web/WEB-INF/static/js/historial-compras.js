/* global fetch */

let main = document.getElementById("main");
let nav = document.getElementById("nav");

const LIMIT = 7;
let paginas = 0;
let offset = 0;

document.addEventListener("DOMContentLoaded", () => {
    contarTransacciones();

});

async function contarTransacciones() {
    await fetch("?svc=contar-transacciones")
            .then(res => res.json())
            .then(json => {
                if (json.value !== null) {
                    paginas = json.paginas / LIMIT;
                }
                let limit = LIMIT;

                main.innerHTML = "";
                mostrarTransacciones(offset, limit);
            });


}

async function mostrarTransacciones(offset, limit) {
    let loading = document.getElementById("loading");
    let cabecera = document.querySelector("main div");
    
    loading.style.visibility = "visible";
    main.style.visibility = "hidden";
    cabecera.style.visibility = "hidden";
    await fetch("?svc=transacciones-paginas&offset=" + offset + "&limit=" + limit)
            .then(res => res.json())
            .then(json => {
                let cont = 0;
                main.innerHTML = "";
                if (json.value !== null) {
                    if (json.length > 1) {
                        json.forEach((transaccion) => {
                            let div = document.createElement("div");
                            let sectId = document.createElement("section");
                            let id = document.createElement("h2");
                            let fecha = document.createElement("p");
                            let importe = document.createElement("p");
                            let sectInfo = document.createElement("section");


                            main.appendChild(div);
                            div.appendChild(sectId);
                            sectId.appendChild(id);
                            sectId.appendChild(fecha);
                            sectId.appendChild(importe);
                            div.appendChild(sectInfo);

                            id.textContent = "#"+transaccion.id;
                            fecha.textContent = transaccion.fecha;
                            importe.textContent = (transaccion.importe).toFixed(2) + " ???";

                            div.classList.add("transaction");
                            sectId.classList.add("id");
                            sectInfo.classList.add("info");

                            fetch("?svc=compras-transaccion&id=" + transaccion.id)
                                    .then(res => res.json())
                                    .then(json => {

                                        if (json.length > 1) {
                                            json.forEach((compra) => {
                                                let art = document.createElement("article");
                                                let a = document.createElement("a");
                                                let img = document.createElement("img");
                                                let h3 = document.createElement("h3");
                                                let p = document.createElement("p");

                                                sectInfo.appendChild(art);
                                                art.appendChild(a);
                                                a.appendChild(img);
                                                a.appendChild(h3);
                                                a.appendChild(p);

                                                a.href = "?cmd=producto-consulta&id=" + compra.producto.id;
                                                img.src = compra.producto.imagen;
                                                h3.textContent = compra.producto.nombre;
                                                p.textContent = (compra.precio).toFixed(2) + " ???";
                                            });
                                        } else {
                                            let art = document.createElement("article");
                                            let a = document.createElement("a");
                                            let img = document.createElement("img");
                                            let h3 = document.createElement("h3");
                                            let p = document.createElement("p");

                                            sectInfo.appendChild(art);
                                            art.appendChild(a);
                                            a.appendChild(img);
                                            a.appendChild(h3);
                                            a.appendChild(p);

                                            a.href = "?cmd=producto-consulta&id=" + json.producto.id;
                                            img.src = json.producto.imagen;
                                            h3.textContent = json.producto.nombre;
                                            p.textContent = json.precio;
                                        }
                                    }).finally(() => {
                                cont++;
                                console.log(cont)
                                if (cont === json.length) {
                                    loading.style.visibility = "hidden";
                                    main.style.visibility = "visible";
                                    cabecera.style.visibility = "visible";
                                }
                            });

                        });

                        //Paginaci??n
                        nav.innerHTML = "";
                        if (paginas > 1) {
                            nav.style.display = "flex";
                            let btnI = document.createElement("a");
                            nav.appendChild(btnI);
                            btnI.textContent = "<<";
                            btnI.addEventListener("click", () => {
                                offset = 0;
                                let limit = LIMIT;
                                mostrarTransacciones(offset, limit);
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

                                    mostrarTransacciones(offset, limit);
                                });
                            }
                            let btnF = document.createElement("a");
                            nav.appendChild(btnF);
                            btnF.textContent = ">>";
                            btnF.addEventListener("click", () => {
                                let limit = LIMIT;
                                mostrarTransacciones((Math.ceil(paginas) - 1) * LIMIT, limit);
                            });
                        } else {
                            nav.style.display = "none";
                        }
                    } else {
                        let cont = 0;
                        let div = document.createElement("div");
                        let sectId = document.createElement("section");
                        let id = document.createElement("h2");
                        let fecha = document.createElement("p");
                        let importe = document.createElement("p");
                        let sectInfo = document.createElement("section");


                        main.appendChild(div);
                        div.appendChild(sectId);
                        sectId.appendChild(id);
                        sectId.appendChild(fecha);
                        sectId.appendChild(importe);
                        div.appendChild(sectInfo);

                        id.textContent = "#"+json.id;
                        fecha.textContent = json.fecha;
                        importe.textContent = json.importe + " ???";

                        div.classList.add("transaction");
                        sectId.classList.add("id");
                        sectInfo.classList.add("info");

                        fetch("?svc=compras-transaccion&id=" + json.id)
                                .then(res => res.json())
                                .then(json => {
                                    if (json.length > 1) {
                                        json.forEach((compra) => {
                                            let art = document.createElement("article");
                                            let a = document.createElement("a");
                                            let img = document.createElement("img");
                                            let h3 = document.createElement("h3");
                                            let p = document.createElement("p");

                                            sectInfo.appendChild(art);
                                            art.appendChild(a);
                                            a.appendChild(img);
                                            a.appendChild(h3);
                                            a.appendChild(p);

                                            a.href = "?cmd=producto-consulta&id=" + compra.producto.id;
                                            img.src = compra.producto.imagen;
                                            h3.textContent = compra.producto.nombre;
                                            p.textContent = (compra.precio).toFixed(2) + " ???";
                                        });
                                    } else {
                                        let art = document.createElement("article");
                                        let a = document.createElement("a");
                                        let img = document.createElement("img");
                                        let h3 = document.createElement("h3");
                                        let p = document.createElement("p");

                                        sectInfo.appendChild(art);
                                        art.appendChild(a);
                                        a.appendChild(img);
                                        a.appendChild(h3);
                                        a.appendChild(p);

                                        a.href = "?cmd=producto-consulta&id=" + json.producto.id;
                                        img.src = json.producto.imagen;
                                        h3.textContent = json.producto.nombre;
                                        p.textContent =(json.precio).toFixed(2) +" ???";
                                    }
                                }).finally(() => {

                            cont++;
                            if (cont === 1) {
                                loading.style.visibility = "hidden";
                                main.style.visibility = "visible";
                                cabecera.style.visibility = "visible";
                            }
                        });

                        //Paginaci??n
                        nav.innerHTML = "";
                        if (paginas > 1) {
                            nav.style.display = "flex";
                            let btnI = document.createElement("a");
                            nav.appendChild(btnI);
                            btnI.textContent = "<<";
                            btnI.addEventListener("click", () => {
                                offset = 0;
                                let limit = LIMIT;
                                mostrarTransacciones(offset, limit);
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

                                    mostrarTransacciones(offset, limit);
                                });
                            }
                            let btnF = document.createElement("a");
                            nav.appendChild(btnF);
                            btnF.textContent = ">>";
                            btnF.addEventListener("click", () => {
                                let limit = LIMIT;

                                mostrarTransacciones((Math.ceil(paginas) - 1) * LIMIT, limit);
                            });
                        } else {
                            nav.style.display = "none";

                        }
                    }
                } else {
                    main.innerHTML = "No ha realizado compras hasta el momento";
                    nav.style.display = "none";
                    loading.style.visibility = "hidden";
                    main.style.visibility = "visible";
                    cabecera.style.visibility = "visible";
                }
            });

    $(function () {
        $(".id").click(function () {
            $(this).siblings('.info').toggle(500);
        });
    });

}