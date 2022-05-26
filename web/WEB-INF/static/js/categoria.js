/* global fecth */

let delCat = document.getElementById("delCat");

document.addEventListener("DOMContentLoaded", () => {

    if (delCat.textContent !== "null") {
        console.log(delCat.textContent);
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