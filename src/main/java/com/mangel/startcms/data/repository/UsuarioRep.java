package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.model.Usuario;

public interface UsuarioRep extends BaseRep<Usuario>{
 public boolean deleteById(int id);
}
