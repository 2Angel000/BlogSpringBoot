package com.mangel.startcms.controller.rest;

import com.mangel.startcms.data.repository.CategoriaRepository;
import com.mangel.startcms.data.model.Categoria;
import com.mangel.startcms.data.model.Common.RepBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaRestController {

    @Autowired
    private CategoriaRepository repository;

    @PutMapping
    @CacheEvict(value = "categorias", allEntries = true)
    public ResponseEntity<RepBase> save(@RequestBody Categoria categoria){
        return ResponseEntity.ok(new RepBase(repository.save(categoria)));
    }

    @PostMapping
    @CacheEvict(value = "categorias", allEntries = true) //también va en delete
    public ResponseEntity<RepBase> update(@RequestBody Categoria categoria){
        return ResponseEntity.ok(new RepBase(repository.update(categoria)));
    }

    @GetMapping
    @Cacheable(value = "categorias")
    public ResponseEntity<List<Categoria>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findById(id));
    }


}
