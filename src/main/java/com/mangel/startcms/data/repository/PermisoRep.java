package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.model.Permiso;

import java.util.List;

public interface PermisoRep extends BaseRep<Permiso>{
    public boolean deleteById(int id);

    public List<Permiso> findByGrupo(int Id);
    public List<Permiso> findNotByIdGrupo(int Id);
}
