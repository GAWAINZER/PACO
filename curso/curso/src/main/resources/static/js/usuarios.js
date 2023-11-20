// Call the dataTables jQuery plugin
$(document).ready(function() {
  alert("se esta modificando información"); //mensaje de alerta
  cargarUsuarios(); //para ejecutar funcion
  $('#usuarios').DataTable();
});


async function cargarUsuarios() {

  const request = await fetch('api/usuarios', {//llamado
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();

  let listadoHtml= '';//funcion de llamado de usuarios
  for (let usuario of usuarios ){
      let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

      let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;
      let usuarioHtml= '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+''+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+telefonoTexto+'</td><td>'+botonEliminar+'</td></tr>';

      listadoHtml += usuarioHtml;
  }

  document.querySelector('#usuarios tbody').outerHTML = listadoHtml;


}

function getHeaders(){
  return{
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Authorization': localStorage.token
  };
}
async function eliminarUsuario(id) {
  if (!confirm('¿ Desea eliminar este usuario ?')){
    return;
  }
  const request = await fetch('api/usuarios/' + id, {//llamado
    method: 'DELETE',
    headers: getHeaders()
  });
  alert("Se ha eliminado el usuario con el id número: "+id);
  location.reload();
}