package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.CategoriaMapper;
import com.mangel.startcms.data.model.Categoria;
import jakarta.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.List;

@Repository
public class CategoriaRepository implements CategoriaRep {
    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource); //conecta a la bd
    }

    @Override
    public boolean save(Categoria categoria) {
        try {
            String sql = String.format("INSERT INTO categoria(Nombre, Descripcion, CategoriaSuperior) VALUES('%s', '%s', '%d')",
                    categoria.getNombre(), categoria.getDescripcion(), categoria.getCategoriaSuperior());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public boolean update(Categoria categoria) {
        if (categoria.getIdCategoria() > 0) { //si está en nulo
            String sql = String.format("UPDATE categoria SET Nombre='%s', Descripcion='%s', CategoriaSuperior='%d' WHERE IdCategoria='%d' ",
                    categoria.getNombre(), categoria.getDescripcion(), categoria.getCategoriaSuperior(), categoria.getIdCategoria());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Categoria> findAll(SpringDataWebProperties.Pageable pageable) {
        return jdbcTemplate.query("select * from Categoria ", new CategoriaMapper());
    }

    //        return jdbcTemplate.query("select * from Categoria ", new CategoriaMapper());
    @Override
    public Categoria findById(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT*FROM Categoria WHERE IdCategoria = ?", params, new CategoriaMapper());
    }

    //Solo para el test
    public void deleteAll() {
        jdbcTemplate.execute("delete from categoria");
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
