/* global fecth*/

let nombre = document.getElementById("nombre");
let id = document.getElementById("id");
let btn = document.getElementById("btn");
let errorDiv = document.getElementById("error");
let errorP = document.querySelector("#error p");

nombre.addEventListener("keypress",(event)=>{
    if(event.key ==="Enter"){
        event.preventDefault();
    }
});

btn.addEventListener("click", () => {
    if (validarCampoVacio(nombre, errorDiv, errorP, null)) {
        fetch("?svc=modificar-categoria&nombre=" + nombre.value + "&id=" + id.value)
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
    }
});

function validarCampoVacio(campo, errorDiv, errorP, msj) {
    if (campo.value === "" || campo.value === null) {
        errorDiv.style.visibility = "visible";
        if (msj === null) {
            errorP.textContent = `El campo ${campo.id} es obligatorio`;
        } else {
            errorP.textContent = msj;
        }
        campo.focus();
        return false;
    } else {
        errorDiv.style.visibility = "hidden";
        return true;
    }
}
