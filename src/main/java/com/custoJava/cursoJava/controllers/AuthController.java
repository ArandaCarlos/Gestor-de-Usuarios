package com.custoJava.cursoJava.controllers;

import com.custoJava.cursoJava.dao.UsuarioDao;
import com.custoJava.cursoJava.models.Usuario;
import com.custoJava.cursoJava.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
        Usuario userLogueado = usuarioDao.login(usuario);
        if(userLogueado != null){
            String token = jwtUtil.create(String.valueOf(userLogueado.getId()), userLogueado.getEmail());
            return token;
        }
        return "FAIL";
    }

}
