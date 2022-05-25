/* global fetch*/

let reset = document.getElementById("reset");
let modU = document.getElementById("modU");
let usuario = document.getElementById("Usuario");
let email = document.getElementById("Email");
let pass = document.getElementById("Contraseña");
let pass2 = document.getElementById("Contraseña2");
let errorDiv = document.querySelector("#error");
let errorP = document.querySelector("#error p");
let img = document.getElementById("imga");
let imgaUp = document.getElementById("imgUp");
let imgLoad = document.getElementById("imgLoad");
let imgBase64;
let usrO;
let emailO;

document.addEventListener("DOMContentLoaded", () => {
    usrO = usuario.value;
    emailO = email.value;
    pass.textContent = "";
});

reset.addEventListener("click", () => {
    usuario.value = usrO;
    email.value = emailO;
});

img.addEventListener("change", () => {
    imgBase64 = getBase64Image();
});

modU.addEventListener("click", () => {

    if (validarCampoVacio(usuario, errorDiv, errorP, "El usuario no puede estar vacio") &&
            validarUsuario() &&
            validarCampoVacio(email, errorDiv, errorP, "El email no puede estar vacio") &&
            validarEmail() &&
            validarChangePass(pass,pass2,errorDiv,errorP)) {
        imgaUp.value = imgBase64;
        errorDiv.style.visibility = "hidden";

        form.submit();
    }

});


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

function validarUsuario() {
    let userrgx = /^[a-zA-Z0-9á-üÁ-Ü ]{5,20}$/;
    if (!userrgx.test(usuario.value)) {
        errorDiv.style.visibility = "visible";
        errorP.textContent = `El campo ${usuario.id} debe tener entre 5 y 20 carácteres alfanuméricos`;
        usuario.focus();
    }
    return userrgx.test(usuario.value);
}

function validarIguales(pass, pass2) {
    return pass.value === pass2.value;
}

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

function getBase64Image() {
    let imgsUp = document.getElementById("imga").files;
    if (imgsUp.length > 0) {
        let imgUp = imgsUp[0];
        let fileReader = new FileReader();

        fileReader.onload = function (FileLoadEvent) {
            let srcData = FileLoadEvent.target.result;
            imgBase64 = srcData;
            imgLoad.src = srcData;
        };
        fileReader.readAsDataURL(imgUp);
    }
}

function validarChangePass(pass, pass2, errorDiv, errorP) {
    if (pass !== null || pass !== "") {
        if (validarIguales(pass, pass2)) {
            errorDiv.style.visibility = "hidden";
            return true;

        } else {
            errorDiv.style.visibility = "hidden";
            errorP.textContent = "Las contraseñas no coinciden";
            return false;
        }
    }
}

//                            Swal.fire({
//                            title: "Oh My Games",
//                            text: "Ha ocurrido un error durante la modificación, intentelo de nuevo más tarde.",
//                            icon: "error",
//                            confirmButtonText: "Continuar"
//                        }).then((result) => {
//                        });
//                        window.location = "?cmd=usuario-perfil";

//                        Swal.fire({
//                            title: "Oh My Games",
//                            text: "El usuario ha sido modificado",
//                            icon: "success",
//                            confirmButtonText: "Continuar"
//                        }).then((result) => {
//                        });
//                        window.location = "?cmd=usuario-perfil";