package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.PermisoMapper;
import com.mangel.startcms.data.model.Permiso;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PermisoRepository implements PermisoRep {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean save(Permiso object) {
        try {
            String sql = String.format("INSERT INTO permiso(Nombre) VALUES('%s')", object.getNombre());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Permiso object) {
       if (object.getIdPermiso() != 0){
           String sql = String.format("UPDATE permiso SET Nombre='%s' WHERE IdPermiso='%d'", object.getNombre(), object.getIdPermiso());
           jdbcTemplate.execute(sql);
           return true;
       }
        return false;
    }

    @Override
    public List<Permiso> findAll(SpringDataWebProperties.Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM Permiso",new PermisoMapper());
    }

    @Override
    public Permiso findById(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT * FROM Permiso WHERE IdPermiso = ?",params, new PermisoMapper());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
