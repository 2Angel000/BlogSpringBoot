package com.mangel.startcms.data.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import java.util.List;

//interface generica
public interface BaseRep<T> {
    public boolean save(T object);
    public boolean update(T object);
    public List<T> findAll(Pageable pageable); //pagina y obtiene el obj
    public T findById(int Id);

}
