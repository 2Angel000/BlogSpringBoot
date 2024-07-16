package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.model.Grupo;

public interface GrupoRep extends BaseRep<Grupo>{
    public boolean deleteById(int id);
}
