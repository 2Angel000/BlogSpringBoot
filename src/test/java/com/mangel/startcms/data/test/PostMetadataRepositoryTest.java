package com.mangel.startcms.data.test;

import com.mangel.startcms.logic.Component.TestDatabaseConfiguration;
import com.mangel.startcms.data.repository.PostMetadataRepository;
import com.mangel.startcms.data.model.PostMetadata;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class PostMetadataRepositoryTest {
    @Autowired
    private PostMetadataRepository postMetadataRepository;

    @Test
    public void testInsert(){
        PostMetadata pm = new PostMetadata();
        pm.setClave("Test clave 1");
        pm.setValor("Test valor 1");
        pm.setTipo("Test tipo 1");
        pm.setIdPost(1);
        boolean result = postMetadataRepository.save(pm);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        PostMetadata pm = new PostMetadata();
        pm.setIdPostMetadata(1);
        pm.setClave("Test update 1");
        pm.setValor("Test update 1");
        pm.setTipo("Test update 1");
        pm.setIdPost(1);
        boolean result = postMetadataRepository.update(pm);
        Assert.assertTrue(result);
    }

    @Test
    public void testFindId(){
        PostMetadata pm = postMetadataRepository.findById(1);
        Assert.assertTrue(pm != null);
        Assert.assertTrue("Test update 1".equals(pm.getClave()));
    }

    @Test
    public void testFindAll(){
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        Assert.assertFalse(postMetadataRepository.findAll(pageable).isEmpty());
    }

}
