package com.cursojava.curso.controllers;


import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;


    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable int id){
        Usuario Usuario = new Usuario();
        com.cursojava.curso.models.Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre ("lucas");
        usuario.setApellido ("moy");
        usuario.setEmail ("lucasmov@gmail.com");
        usuario.setTelefono ("3245263");
        return usuario;
    }
    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization")String token){


        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public  void registrarUsuario(@RequestBody Usuario usuario ){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash= argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }
    @RequestMapping(value = "usuario1")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        com.cursojava.curso.models.Usuario usuario1 = new Usuario();
        usuario.setNombre ("lucas");
        usuario.setApellido ("moy");
        usuario.setEmail ("lucasmov@gmail.com");
        usuario.setTelefono ("3245263");

        return usuario;
    }
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)//funcionamiento del boton eliminar
    public void eliminar(@PathVariable int id){
        usuarioDao.eliminar(id);
    }
    @RequestMapping(value = "usuario3")
    public Usuario buscar(){
        Usuario Usuario = new Usuario();
        com.cursojava.curso.models.Usuario usuario = new Usuario();
        usuario.setNombre ("lucas");
        usuario.setApellido ("moy");
        usuario.setEmail ("lucasmov@gmail.com");
        usuario.setTelefono ("3245263");
        return usuario;
    }
}
