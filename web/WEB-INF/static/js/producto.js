/* global fetch */

let fav = document.getElementById("fav");
let prod = document.getElementById("prod-id");
let login = document.getElementById("login");

if (!admin) {
    let car = document.getElementById("car");

    car.addEventListener("click", () => {
        if (user !== null) {
            añadirCarrito();
        } else {
            login.click();
        }
    });
}

fav.addEventListener("click", () => {
    if (user !== null) {
        if (fav.alt === "Favorito") {
            quitarFavorito();
        } else {
            añadirFavorito();
        }
    } else {
        login.click();
    }
});

function quitarFavorito() {
    if (user !== null) {
        fetch(`?svc=quitar-favorito&prod=${prod.textContent}`)
                .then(res => res.json())
                .then(json => {
                    if (json.fav) {
                        fav.src = "public/img/nofav.png";
                        fav.alt = "No Favorito";
                    }
                });
    }
}

function añadirFavorito() {
    if (user !== null) {
        fetch(`?svc=añadir-favorito&prod=${prod.textContent}&user=${user.textContent}`)
                .then(res => res.json())
                .then(json => {
                    if (json.fav) {
                        fav.src = "public/img/fav.png";
                        fav.alt = "Favorito";
                    }
                });
    }
}

function añadirCarrito() {
    if (user !== null) {
        fetch(`?svc=validar-carrito&prod=${prod.textContent}&user=${user.textContent}`)
                .then(res => res.json())
                .then(json => {
                    if (json.res == true) {
                        fetch(`?svc=añadir-carrito&prod=${prod.textContent}&user=${user.textContent}`)
                                .then(res => res.json())
                                .then(json => {
                                    if (json.car) {
                                        Swal.fire({
                                            title: "Oh My Games",
                                            text: "El producto se ha añadido al carrito",
                                            icon: "success",
                                            confirmButtonText: "Continuar"
                                        }).then((result) => {
                                        });
                                    } else {
                                        Swal.fire({
                                            title: "Oh My Games",
                                            text: "El producto no se pudo añadir al carrito, intentelo de nuevo más tarde",
                                            icon: "error",
                                            confirmButtonText: "Continuar"
                                        }).then((result) => {
                                        });
                                    }
                                });
                    } else if (json.res == false) {
                        Swal.fire({
                            title: "Oh My Games",
                            text: "El producto ya se encuentra en el carrito",
                            icon: "info",
                            confirmButtonText: "Continuar"
                        }).then((result) => {
                        });
                    } else {
                        Swal.fire({
                            title: "Oh My Games",
                            text: "El producto no se pudo añadir al carrito, intentelo de nuevo más tarde",
                            icon: "error",
                            confirmButtonText: "Continuar"
                        }).then((result) => {
                        });
                    }
                });
    }
}

