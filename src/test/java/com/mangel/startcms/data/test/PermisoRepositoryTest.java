package com.mangel.startcms.data.test;

import com.mangel.startcms.logic.Component.TestDatabaseConfiguration;
import com.mangel.startcms.data.repository.PermisoRepository;
import com.mangel.startcms.data.model.Permiso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class PermisoRepositoryTest {
    @Autowired
    private PermisoRepository permisoRepository;

    @Test
    public void testInsert(){
        Permiso permiso = new Permiso();
        permiso.setNombre("Test Permiso 1");
        boolean result = permisoRepository.save(permiso);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        Permiso permiso = new Permiso();
        permiso.setIdPermiso(1);
        permiso.setNombre("Test Update 1");
        boolean result = permisoRepository.update(permiso);
        Assert.assertTrue(result);
    }

    @Test
    public void testFindId(){
        Permiso permiso = permisoRepository.findById(1);
        Assert.assertTrue(permiso!=null);
        Assert.assertTrue("Test Update 1".equals(permiso.getNombre()));
    }

    @Test
    public void testFindAll(){
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        Assert.assertFalse(permisoRepository.findAll(pageable).isEmpty());
    }
}
