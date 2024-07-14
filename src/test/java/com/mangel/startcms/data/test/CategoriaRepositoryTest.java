package com.mangel.startcms.data.test;

import com.mangel.startcms.logic.Component.TestDatabaseConfiguration;
import com.mangel.startcms.data.repository.CategoriaRepository;
import com.mangel.startcms.data.model.Categoria;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})//cuando cargue el test, se carga el bean de testDatabaseConfiguration
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    //@Order(1)
    public void testInsert(){
        Categoria categoria = new Categoria();
        categoria.setNombre("Nuevo Test 3");
        categoria.setDescripcion("Este es un ejemplo de categoria superior");
        categoria.setCategoriaSuperior(1);

        boolean result = categoriaRepository.save(categoria);
        //categoriaRepository.deleteAll();
        Assert.assertTrue(result); //espera un valor booleano True
    }

    @Test
    public void testUpdate(){
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setNombre("Update 1");
        categoria.setDescripcion("Este es un ejemplo de update");
        categoria.setCategoriaSuperior(1);

        boolean result = categoriaRepository.update(categoria);
        Assert.assertTrue(result);
    }

    @Test
    public void testFindId(){
        Categoria categoria = categoriaRepository.findById(1);
        Assert.assertTrue(categoria!=null);
        Assert.assertTrue("Update 1".equals(categoria.getNombre()));
    }

    @Test
    public void testFindAll(){
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        Assert.assertFalse(categoriaRepository.findAll(pageable).isEmpty());
    }
}
