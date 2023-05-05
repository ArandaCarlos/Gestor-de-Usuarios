package com.custoJava.cursoJava.controllers;

import com.custoJava.cursoJava.dao.UsuarioDao;
import com.custoJava.cursoJava.models.Usuario;
import com.custoJava.cursoJava.utils.JWTUtil;
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

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        return usuarioDao.getUsuario(id);
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){
        if(!validarToken(token)){
            return null;
        }
        return usuarioDao.getUsuarios();

    }

    private boolean validarToken(String token){
        String idUsuario = jwtUtil.getKey(token);
        return idUsuario != null;
    }

    @RequestMapping(value = "editar")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setApellido("Aranda");
        usuario.setEmail("aranda.carlos.damian");
        usuario.setNombre("Carlos");
        usuario.setTelefono("2213539143");
        usuario.setPassword("aranda22");
        return usuario;
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token,
                         @PathVariable Long id){
        if(!validarToken(token)){
            return;
        }
        usuarioDao.eliminar(id);


    }

    @RequestMapping(value = "api/usuario", method = RequestMethod.POST)
    public String registrar(@RequestBody Usuario usuario){
        if(usuarioDao.buscar(usuario)){
            return "Fail";
        }
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String passwordHash = argon2.hash(1, 1024,1,usuario.getPassword());
        usuario.setPassword(passwordHash);
        usuarioDao.registrar(usuario);
        return "OK";
    }


}
