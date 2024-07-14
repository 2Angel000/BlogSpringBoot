package com.mangel.startcms.data.test;

import com.mangel.startcms.logic.Component.TestDatabaseConfiguration;
import com.mangel.startcms.data.repository.GrupoRepository;
import com.mangel.startcms.data.model.Grupo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class GrupoRepositoryTest {
    @Autowired
    private GrupoRepository grupoRepository;

    @Test
    public void testInsert(){
        Grupo grupo = new Grupo();
        grupo.setNombre("Nuevo Test 1");

        boolean result = grupoRepository.save(grupo);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        Grupo grupo = new Grupo();
        grupo.setIdGrupo(1);
        grupo.setNombre("Update Test 1");
        boolean result = grupoRepository.update(grupo);
        Assert.assertTrue(result);
    }

    @Test
    public void testFindId(){
        Grupo grupo = grupoRepository.findById(1);
        Assert.assertTrue(grupo!=null);
        Assert.assertTrue("Update Test 1".equals(grupo.getNombre()));
    }

    @Test
    public void testFindAll(){
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        Assert.assertFalse(grupoRepository.findAll(pageable).isEmpty());
    }
}
