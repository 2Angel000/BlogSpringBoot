package com.mangel.startcms.data.test;

import com.mangel.startcms.logic.Component.TestDatabaseConfiguration;
import com.mangel.startcms.data.repository.PostRepository;
import com.mangel.startcms.data.model.Post;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testInsert(){
        Post post = new Post();
        post.setTitulo("Test Titulo 1");
        post.setSlug("Test-Titulo-1");
        post.setExtracto("Test-1");
        post.setIdUsuario(1);
        post.setIdCategoria(1);
        post.setImagenDestacada("Img");
        post.setTipo("Web");

        boolean result = postRepository.save(post);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        Post post = new Post();
        post.setIdPost(1);
        post.setTitulo("Test Update 1");
        post.setSlug("Test-Update-1");
        post.setExtracto("Test-Update-1");
        post.setIdUsuario(1);
        post.setIdCategoria(1);
        post.setImagenDestacada("Update");
        post.setTipo("Web Update");
        boolean result = postRepository.update(post);
        Assert.assertTrue(result);
    }

    @Test
    public void testFindId(){
        Post post = postRepository.findById(1);
        Assert.assertTrue(post!=null);
        Assert.assertTrue("Test Update 1".equals(post.getTitulo()));
    }

    @Test
    public void testFindAll(){
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        Assert.assertFalse(postRepository.findAll(pageable).isEmpty());
    }
}
