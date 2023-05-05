package com.custoJava.cursoJava.dao;

import com.custoJava.cursoJava.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    Usuario getUsuario(Long id);

    void registrar(Usuario usuario);

    Usuario login(Usuario usuario);

    boolean buscar(Usuario usuario);
}
