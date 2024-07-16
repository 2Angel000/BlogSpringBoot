package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.GrupoMaper;
import com.mangel.startcms.data.model.Grupo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GrupoRepository implements GrupoRep{

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean save(Grupo grupo) {
        try {
            String sql = String.format("INSERT INTO grupo(Nombre) VALUES('%s')", grupo.getNombre());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Grupo grupo) {
        if(grupo.getIdGrupo() > 0){
            String sql = String.format("UPDATE grupo SET Nombre='%s' WHERE IdGrupo= %d ", grupo.getNombre(), grupo.getIdGrupo());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Grupo> findAll(SpringDataWebProperties.Pageable pageable) {
        return jdbcTemplate.query("SELECT*FROM Grupo", new GrupoMaper());
    }

    @Override
    public Grupo findById(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT*FROM Grupo WHERE IdGrupo = ?", params, new GrupoMaper());
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
            String sql = String.format("delete from grupo where IdGrupo='%d'", id);
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            System.out.println("e = " + e);
            return false;
        }
    }
}
