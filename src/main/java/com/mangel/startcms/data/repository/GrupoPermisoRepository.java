package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.GrupoPermisoMapper;
import com.mangel.startcms.data.model.GrupoPermiso;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GrupoPermisoRepository implements GrupoPermisoRep{
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void PostConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public boolean save(GrupoPermiso grupoPermiso) {
        try {
            String sql = String.format("INSERT INTO grupo_permiso(IdGrupo, IdPermiso) VALUES(%d, %d)",
                    grupoPermiso.getIdGrupo(), grupoPermiso.getIdPermiso());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Errorr:\n " + e);
            return false;
        }
    }

    @Override
    public boolean update(GrupoPermiso grupoPermiso) {
        if(grupoPermiso.getIdGrupo() != 0 && grupoPermiso.getIdPermiso() != 0) { //si está en nulo
            String sql = String.format("UPDATE grupo_permiso SET IdGrupo=%d, IdPermiso=%d WHERE IdGrupoPermiso=%d",
                    grupoPermiso.getIdGrupo(), grupoPermiso.getIdPermiso(), grupoPermiso.getIdGrupoPermiso());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<GrupoPermiso> findAll(SpringDataWebProperties.Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM grupo_permiso", new GrupoPermisoMapper());
    }

    @Override
    public GrupoPermiso findById(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT * FROM grupo_permiso WHERE IdGrupoPermiso = ?", params, new GrupoPermisoMapper());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
