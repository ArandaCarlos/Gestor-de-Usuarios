// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
    $('#usuarios').DataTable();
});

async function cargarUsuarios(){

      const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: getHeader()
      });
      const usuarios = await request.json();

      console.log(usuarios);

      listaUsuariosHTML = '';
      for(let usuario of usuarios){
        let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+usuario.telefono+'</td><td>'+botonEliminar+'</td></tr>';
        listaUsuariosHTML += usuarioHTML;
      }

      document.querySelector('#usuarios tbody').outerHTML = listaUsuariosHTML;

}

async function eliminarUsuario(id){
    if(!confirm('Desea eliminar el usuario?')){
        return;
    }
    const request = await fetch('api/usuario/'+id, {
            method: 'DELETE',
            headers: getHeader()
          });
    location.reload();
}

function getHeader(){
    return{
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
    }
}

async function getUsuario(id){
    const request = await fetch('api/usuario/'+id, {
            method: 'GET',
            headers: getHeader()
          });
    const usuario = await request.json();
    console.log(usuario);
}