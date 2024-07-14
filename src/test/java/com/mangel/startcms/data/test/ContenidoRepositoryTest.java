package com.mangel.startcms.data.test;

import com.mangel.startcms.logic.Component.TestDatabaseConfiguration;
import com.mangel.startcms.data.repository.ContenidoRepository;
import com.mangel.startcms.data.model.Contenido;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class ContenidoRepositoryTest {

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Test
    public void testInsert(){
        Contenido contenido = new Contenido();
        contenido.setTipo("Test Tipo 1");
        contenido.setContenido("Test Contenido 1");
        contenido.setIdPost(1);

        boolean result = contenidoRepository.save(contenido);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        Contenido contenido = new Contenido();
        contenido.setIdContenido(1);
        contenido.setTipo("Test update tipo 2");
        contenido.setContenido("Test update 1");
        contenido.setIdPost(1);
        boolean result = contenidoRepository.update(contenido);
        Assert.assertTrue(result);
    }

    @Test
    public void testFindId(){
        Assert.assertTrue(contenidoRepository.findById(1).getIdContenido()==1);
    }
    
    @Test
    public void testFindAll(){
        Assert.assertFalse(contenidoRepository.findAll(new SpringDataWebProperties.Pageable()).isEmpty());
    }

}
