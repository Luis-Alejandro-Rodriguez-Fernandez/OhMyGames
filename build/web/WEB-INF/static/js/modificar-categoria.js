/* global fecth*/

let nombre = document.getElementById("nombre");
let id =  document.getElementById("id");
let btn = document.getElementById("btn");

btn.addEventListener("click", () => {
    fetch("?svc=modificar-categoria&nombre="+nombre.value+"&id="+id.value)
            .then(res => res.json())
            .then(json => {
                if (json.cat !== null) {
                    if (json.cat) {
                        Swal.fire({
                            title: "Oh My Games",
                            text: "La categoría se ha modificado correctamente",
                            icon: "success",
                            confirmButtonText: "Continuar"
                        }).then((result) => {
                            window.location = "?cmd=categoria";
                        });
                    } else {
                        Swal.fire({
                            title: "Oh My Games",
                            text: "La categoría no se pudo modificar, contacte con el servicio técnico",
                            icon: "error",
                            confirmButtonText: "Continuar"
                        }).then((result) => {
                            window.location = "?cmd=categoria";
                        });
                    }
                } else {
                    Swal.fire({
                        title: "Oh My Games",
                        text: "La categoría introducida ya existe",
                        icon: "info",
                        confirmButtonText: "Continuar"
                    }).then((result) => {
                        window.location = "?cmd=categoria";
                    });
                }
            });
});


