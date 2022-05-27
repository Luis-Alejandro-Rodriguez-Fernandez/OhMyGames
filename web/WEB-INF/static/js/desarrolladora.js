/* global fecth */

let delCat = document.getElementById("delDes");

document.addEventListener("DOMContentLoaded", () => {

    if (delCat.textContent !== "null") {

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