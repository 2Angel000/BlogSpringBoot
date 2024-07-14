package com.mangel.startcms.controller.rest;

import com.mangel.startcms.data.repository.PostRepository;
import com.mangel.startcms.data.model.Common.RepBase;
import com.mangel.startcms.data.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostRestController {
    @Autowired
    private PostRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@RequestBody Post post){
        return ResponseEntity.ok(new RepBase(repository.save(post)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Post post){
        return ResponseEntity.ok(new RepBase(repository.update(post)));
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findById(id));
    }

}
