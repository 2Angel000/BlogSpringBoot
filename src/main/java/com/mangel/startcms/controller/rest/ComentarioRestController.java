package com.mangel.startcms.controller.rest;

import com.mangel.startcms.data.repository.ComentarioRepository;
import com.mangel.startcms.data.model.Comentario;
import com.mangel.startcms.data.model.Common.RepBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioRestController {

    @Autowired
    public ComentarioRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@RequestBody Comentario comentario){
        return ResponseEntity.ok(new RepBase(repository.save(comentario)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Comentario comentario){
        return ResponseEntity.ok(new RepBase(repository.update(comentario)));
    }

    @GetMapping
    public ResponseEntity<List<Comentario>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

     @GetMapping("/{id}")
    public ResponseEntity<Comentario> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findById(id));
     }

}
