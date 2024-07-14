package com.mangel.startcms.data.test;

import com.mangel.startcms.logic.Component.TestDatabaseConfiguration;
import com.mangel.startcms.data.repository.UsuarioMetadataRepository;
import com.mangel.startcms.data.model.UsuarioMetadata;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class UsuarioMetadataRepositoryTest {
    @Autowired
    private UsuarioMetadataRepository usuarioMetadataRepository;

    @Test
    public void testInsert(){
        UsuarioMetadata um = new UsuarioMetadata();
        um.setClave("Test clave 1");
        um.setValor("Test valor 1");
        um.setTipo("Test tipo 1");
        um.setUsuario(1);

        boolean result = usuarioMetadataRepository.save(um);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        UsuarioMetadata um = new UsuarioMetadata();
        um.setIdUsuarioMetadata(1);
        um.setClave("Test Update 1");
        um.setValor("Test Update valor 1");
        um.setTipo("Test tipo Update 1");
        um.setUsuario(1);

        boolean result = usuarioMetadataRepository.update(um);
        Assert.assertTrue(result);
    }

    @Test
    public void testFindId(){
        UsuarioMetadata um = usuarioMetadataRepository.findById(1);
        Assert.assertTrue(um!=null);
        Assert.assertTrue("Test Update 1".equals(um.getClave()));
    }

    @Test
    public void testFindAll(){
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        Assert.assertFalse(usuarioMetadataRepository.findAll(pageable).isEmpty());
    }



}
