/* global fetch */
let errorDiv = document.querySelector("#error");
let errorP = document.querySelector("#error p");
let form = document.getElementById("form");
let usuario = document.getElementById("Usuario");
let pass = document.getElementById("Contraseña");
let btn = document.getElementById("btn");


btn.addEventListener("click", () => {
    if (
            validarCampoVacio(usuario, errorDiv, errorP) &&
            validarCampoVacio(pass, errorDiv, errorP))
    {
        validarUsuarioCorrecto();
    }
});

function validarCampoVacio(campo, errorDiv, errorP) {
    if (campo.value === "" || campo.value === null) {
        errorDiv.style.visibility = "visible";
        errorP.textContent = `El campo ${campo.id} es obligatorio`;
        campo.focus();
        return false;
    } else {
        errorDiv.style.visibility = "hidden";
        return true;
    }
}

async function validarUsuarioCorrecto() {
    let exist;
    await fetch(`?svc=consultar-usuario&user=${usuario.value}&pass=${pass.value}`)
            .then(res => res.json())
            .then(json => {

                if (json.exist) {
                    exist = true;
                } else {
                    errorDiv.style.visibility = "visible";
                    errorP.textContent = `El usuario o la contraseña son incorrectos`;
                    exist = false;
                }
            });
  if(exist){

              form.submit();
  }
}