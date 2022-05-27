/* global fecth*/

let nombre = document.getElementById("nombre");
let btn = document.getElementById("btn");
let errorDiv = document.getElementById("error");
let errorP = document.querySelector("#error p");

nombre.addEventListener("keypress",(event)=>{
    if(event.key ==="Enter"){
        event.preventDefault();
    }
});

btn.addEventListener("click", () => {
    if(validarCampoVacio(nombre,errorDiv,errorP,null)){
    fetch("?svc=añadir-desarrolladora&nombre="+nombre.value)
            .then(res => res.json())
            .then(json => {
                if (json.des !== null) {
                    if (json.des) {
                        Swal.fire({
                            title: "Oh My Games",
                            text: "La desarrolladora se ha añadido correctamente",
                            icon: "success",
                            confirmButtonText: "Continuar"
                        }).then((result) => {
                            window.location = "?cmd=desarrolladora";
                        });
                    } else {
                        Swal.fire({
                            title: "Oh My Games",
                            text: "La desarrolladora no se pudo añadir, contacte con el servicio técnico",
                            icon: "error",
                            confirmButtonText: "Continuar"
                        }).then((result) => {
                            window.location = "?cmd=desarrolladora";
                        });
                    }
                } else {
                    Swal.fire({
                        title: "Oh My Games",
                        text: "La desarrolladora introducida ya existe",
                        icon: "info",
                        confirmButtonText: "Continuar"
                    }).then((result) => {
                        window.location = "?cmd=desarrolladora";
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