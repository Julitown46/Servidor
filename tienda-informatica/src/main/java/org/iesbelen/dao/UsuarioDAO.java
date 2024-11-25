package org.iesbelen.dao;

import org.iesbelen.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {
    public void create(Usuario usuario);
    public List<Usuario> getAll();
    public Optional<Usuario> find(int idUsuario);
    public void update(Usuario usuario);
    public void delete(int idUsuario);
}
