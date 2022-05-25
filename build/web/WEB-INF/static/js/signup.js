/* global fetch */
let errorDiv = document.querySelector("#error");
let errorP = document.querySelector("#error p");
let form = document.getElementById("form");
let usuario = document.getElementById("Usuario");
let email = document.getElementById("Email");
let pass = document.getElementById("Contraseña");
let pass2 = document.getElementById("Contraseña2");
let btn = document.getElementById("btn");

btn.addEventListener("click", () => {

    if (
            validarCampoVacio(usuario, errorDiv, errorP, null) &&
            validarUsuario() &&
            validarCampoVacio(email, errorDiv, errorP, null) &&
            validarEmail() &&
            validarCampoVacio(pass, errorDiv, errorP, null) &&
            validarContraseña() &&
            validarCampoVacio(pass2, errorDiv, errorP, `Repetir la contraseña es obligatorio`) &&
            validarIguales(pass, pass2)
            )
    {
        validarUsuarioRegistrable();
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

function validarIguales(pass, pass2) {
    return pass.value === pass2.value;
}

function validarUsuario() {
    let userrgx = /^[a-zA-Z0-9á-üÁ-Ü ]{5,20}$/;
    if (!userrgx.test(usuario.value)) {
        errorDiv.style.visibility = "visible";
        errorP.textContent = `El campo ${usuario.id} debe tener entre 5 y 20 carácteres alfanuméricos`;
        usuario.focus();
    }
    return userrgx.test(usuario.value);
}

function validarEmail() {
    let emailrgx = /^[\w-\.]+@([\w-]+\.)+[\w-]{1,}$/;
    if (!emailrgx.test(email.value)) {
        errorDiv.style.visibility = "visible";
        errorP.textContent = `El campo ${email.id} debe tener formato de correo electrónico`;
        email.focus();
    }
    return emailrgx.test(email.value);
}

function validarContraseña() {
    let passrgx = /(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{6,30}$/;
    if (!passrgx.test(pass.value)) {
        errorDiv.style.visibility = "visible";
        errorP.textContent = `El campo ${pass.id} debe tener entre 3 y 30 carácteres alfanuméricos y al menos un número, una minúscula y una mayúscula`;
        pass.focus();
    }
    return passrgx.test(pass.value);
}

async function validarUsuarioRegistrable() {
    let res = false;
    await fetch(`?svc=consultar-usuarios&user=${usuario.value}&email=${email.value}`)
            .then(res => res.json())
            .then(json => {
                if (json.result === false && json.error !== null) {
                    errorDiv.style.visibility = "visible";
                    errorP.textContent = json.error;
                } else if (json.result === true) {
                    res = true;
                }
            });
    if (res) {
        form.submit();
    }
}