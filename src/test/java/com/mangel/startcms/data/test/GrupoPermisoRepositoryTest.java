package com.mangel.startcms.data.test;

import com.mangel.startcms.logic.Component.TestDatabaseConfiguration;
import com.mangel.startcms.data.repository.GrupoPermisoRepository;
import com.mangel.startcms.data.model.GrupoPermiso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class GrupoPermisoRepositoryTest {

    @Autowired
    private GrupoPermisoRepository grupoPermisoRepository;

    @Test
    public void testInsert(){
        GrupoPermiso gp = new GrupoPermiso();
        gp.setIdPermiso(1);
        gp.setIdGrupo(1);

        boolean result = grupoPermisoRepository.save(gp);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        GrupoPermiso gp = new GrupoPermiso();
        gp.setIdGrupoPermiso(1);
        gp.setIdGrupo(1);
        gp.setIdPermiso(2);
        boolean result = grupoPermisoRepository.update(gp);
        Assert.assertTrue(result);
    }

    @Test
    public void testFindId(){
        Assert.assertTrue(grupoPermisoRepository.findById(1).getIdGrupoPermiso()==1);
    }

    @Test
    public void testFindAll(){
        Assert.assertFalse(grupoPermisoRepository.findAll(new SpringDataWebProperties.Pageable()).isEmpty());
    }
}
