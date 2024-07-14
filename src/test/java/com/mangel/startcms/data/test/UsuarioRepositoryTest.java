package com.mangel.startcms.data.test;

import com.mangel.startcms.logic.Component.TestDatabaseConfiguration;
import com.mangel.startcms.data.repository.UsuarioRepository;
import com.mangel.startcms.data.model.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testInsert(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Test usuario 1");
        usuario.setApellido("App Test 1");
        usuario.setCorreo("Test1@gmail.com");
        usuario.setContrasena("123Test");
        usuario.setIdGrupo(1);

        boolean result = usuarioRepository.save(usuario);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Test update 1");
        usuario.setApellido("Apellido update 1");
        usuario.setCorreo("Update1@gmail.com");
        usuario.setContrasena("123Update");
        usuario.setIdGrupo(1);
        boolean result = usuarioRepository.update(usuario);
        Assert.assertTrue(result);
    }

    @Test
    public void testFindId(){
        Usuario usuario = usuarioRepository.findById(1);
        Assert.assertTrue(usuario != null);
        Assert.assertTrue("Test update 1".equals(usuario.getNombre()));
    }

    @Test
    public void testFindAll(){
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        Assert.assertFalse(usuarioRepository.findAll(pageable).isEmpty());
    }


}
