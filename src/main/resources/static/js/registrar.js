async function registrarUsuario(){
    let datos = {};
    datos.nombre = document.getElementById("nombre").value;
    datos.apellido = document.getElementById("apellido").value;
    datos.email = document.getElementById("email").value;
    datos.password = document.getElementById("password").value;

    if(datos.password != document.getElementById("repeatPassword").value){
        alert("contraseña incorrecta!");
        return;
    }
      const request = await fetch('api/usuario', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });

      const response = await request.text();
      if(response == "OK"){
        window.location.href="usuarios.html";
      }else{
        alert("Ya existe una cuenta con ese mail");
      }
}