package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.PermisoMapper;
import com.mangel.startcms.data.model.Permiso;
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
public class PermisoRepository implements PermisoRep {
    private Log logger = LogFactory.getLog(this.getClass());
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
        return jdbcTemplate.query("select * from Permiso",new PermisoMapper());
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

    @Override
    public boolean deleteById(int id) {
        try{
            String sql = String.format("delete from permiso where IdPermiso = '%d'",id);
            jdbcTemplate.execute(sql);
            logger.info("Success permiso delete\n"+sql);
            return true;
        }catch (Exception e){
            logger.error("Error deleting permiso\n"+e);
            return false;
        }
    }

    @Override
    public List<Permiso> findByGrupo(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.query("select p.* from grupo_permiso gp inner join permiso p on gp.IdPermiso = p.IdPermiso where gp.IdGrupo = ?",
                params,
                new PermisoMapper());
    }

    @Override
    public List<Permiso> findNotByIdGrupo(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.query("select p.* from grupo_permiso gp inner join permiso p on gp.IdPermiso = p.IdPermiso where gp.IdGrupo != ?",
                params,
                new PermisoMapper());
    }

}
