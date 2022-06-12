/* global fetch */

let nombre = document.getElementById("nombre");
let tipo = document.getElementById("tipo");
let cat = document.getElementById("categoria");
let des = document.getElementById("desarrolladora");
let desc = document.getElementById("descripcion");
let precio = document.getElementById("precio");
let descuento = document.getElementById("descuento");
let date = document.getElementById("fecha");
let errorDiv = document.querySelector("#error");
let errorP = document.querySelector("#error p");
let img = document.getElementById("imga");
let imgaUp = document.getElementById("imgUp");
let imgLoad = document.getElementById("imgLoad");
let form = document.getElementById("form");
let imgBase64;
let add = document.getElementById("add");

img.addEventListener("change", () => {
    imgBase64 = getBase64Image();
});

add.addEventListener("click", () => {

    if (validarCampoVacio(nombre, errorDiv, errorP, null) &&
            validarCampoVacio(precio, errorDiv, errorP, null) &&
            validarCampoVacio(descuento, errorDiv, errorP, null) &&
            validarDescuto(descuento, errorDiv, errorP, null) &&
            validarCampoVacio(date, errorDiv, errorP, null) &&
            validarFecha(date,errorDiv,errorP) &&
            validarCampoVacio(desc, errorDiv, errorP, null) &&
            validarImgUp(img.files, errorDiv, errorP)) {
        errorDiv.style.visibility = "hidden";
        imgaUp.value = imgBase64;
        form.submit();
    }

});

let regex = /^[0-9.,]/;
//LIMITED INPUTS
descuento.addEventListener("keypress", (event) => {
    if(!regex.test(event.key)){
        event.preventDefault();
    }
});

descuento.addEventListener("input", () => {

    if (parseInt(descuento.value) >= 99) {
        descuento.value = 99;
    } else if (parseInt(descuento.value) <= 0) {
        descuento.value = 5;
    }
});

descuento.addEventListener("blur", () => {
    if (descuento.value == "" || descuento.value == null) {
        descuento.value = 5;
    }
});

precio.addEventListener("keypress", (event) => {
    if(!regex.test(event.key)){
        event.preventDefault();
    }
});

precio.addEventListener("input", () => {
    if (parseInt(precio.value) >= 150) {
        precio.value = 150;
    } else if (parseInt(precio.value) <= 0) {
        precio.value = 5;
    }
});

precio.addEventListener("blur", () => {
    if (precio.value == "" || precio.value == null) {
        precio.value = 5;
    }
});

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

function validarCampoVacio(campo, errorDiv, errorP, msj) {
    if (campo.value === "" || campo.value === null) {
    console.log(campo.value === "")
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

function validarImgUp(img, errorDiv, errorP) {
    console.log(img.length);
    if (img.length > 0) {
        return true;
    } else {
        errorDiv.style.visibility = "visible";
        errorP.textContent = "El campo de imagen es obligatoria";
        return false;
    }
}

//TODO
function validarFecha(date, errorDiv, errorP) {
    d = new Date(date.value);
    dt = new Date();

    if (d <= dt) {
        return true;
    } else {
        errorDiv.style.diplay = "visible";
        errorP.textContent = "La fecha introducida no es válida";
        return false;
    }
}

function validarPrecio() {

}

function validarDescuto(num, errorDiv, errorP) {
    let regex = /^[0-9][0-9]?$|^70$/;
    if (regex.test(num.value)) {
        return true;
    } else {
        errorDiv.style.diplay = "visible";
        errorP.textContent = "El valor descuento no introducido no es válido";
        return false;
    }
}