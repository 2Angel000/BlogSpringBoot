package com.mangel.startcms.data.test;

import com.mangel.startcms.logic.Component.TestDatabaseConfiguration;
import com.mangel.startcms.data.repository.ComentarioRepository;
import org.junit.Assert;
import com.mangel.startcms.data.model.Comentario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class ComentarioRepositoryTest {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Test
    public void testInsert(){
        Comentario comentario = new Comentario();
        comentario.setComentario("Test Comentario 1");
        comentario.setIdPost(1);
        comentario.setIdUsuario(1);
        comentario.setRespuesta("Test Respuesta 1");

        boolean result = comentarioRepository.save(comentario);
        Assert.assertTrue(result);
    }
}
