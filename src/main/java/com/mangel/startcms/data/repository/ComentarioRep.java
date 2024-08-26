package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.model.Comentario;

public interface ComentarioRep extends BaseRep<Comentario> {
    public boolean deleteById(int id);
}
