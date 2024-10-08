package com.mangel.startcms.controller.rest;

import com.mangel.startcms.data.repository.UsuarioRepository;
import com.mangel.startcms.data.model.Common.RepBase;
import com.mangel.startcms.data.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioRestController {
    @Autowired
    private UsuarioRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@RequestBody Usuario usuario){
        return ResponseEntity.ok(new RepBase(repository.save(usuario)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Usuario usuario){
        return ResponseEntity.ok(new RepBase(repository.update(usuario)));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findById(id));
    }
}
