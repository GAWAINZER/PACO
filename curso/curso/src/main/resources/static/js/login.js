$(document).ready(function() {
    alert("se añadirá un usuario"); //mensaje de alerta
});


async function iniciarSesion() {
    let datos = {};//los datos enviados serán = a los resibidos get=post
    datos.email =document.getElementById('txtEmail').value;
    datos.password =document.getElementById('txtPassword').value;

    const request = await fetch('api/login', {//llamado
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)//agarra los obj y los pasa a json
    });
    const respuesta = await request.text();//traer info de la sesion
    if(respuesta == 'ok'){
      window.location.href='usuarios.html'
    }else{
        alert("datos incorrectos intenta de nuevo")
    }

}
