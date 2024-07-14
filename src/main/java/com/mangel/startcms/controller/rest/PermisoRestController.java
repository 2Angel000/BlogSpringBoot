package com.mangel.startcms.controller.rest;

import com.mangel.startcms.data.repository.PermisoRepository;
import com.mangel.startcms.data.model.Common.RepBase;
import com.mangel.startcms.data.model.Permiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permiso")
public class PermisoRestController {

    @Autowired
    private PermisoRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@RequestBody Permiso permiso){
        return ResponseEntity.ok(new RepBase(repository.save(permiso)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Permiso permiso){
        return ResponseEntity.ok(new RepBase(repository.update(permiso)));
    }

    @GetMapping
    public ResponseEntity<List<Permiso>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permiso> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findById(id));
    }


}
