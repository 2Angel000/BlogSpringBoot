package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.ContenidoMapper;
import com.mangel.startcms.data.model.Contenido;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ContenidoRepository implements ContenidoRep{
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean save(Contenido contenido) {
       try{
           String sql = String.format("INSERT INTO contenido(Contenido, IdPost, Tipo) VALUES('%s','%d','%s')",
                   contenido.getContenido(), contenido.getIdPost(), contenido.getTipo());
           jdbcTemplate.execute(sql);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public boolean update(Contenido contenido) {
        if(contenido.getIdContenido() != 0){
            String sql = String.format("UPDATE contenido SET Contenido='%s', Tipo='%s' WHERE IdContenido='%d'",
                    contenido.getContenido(), contenido.getTipo(), contenido.getIdContenido());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Contenido> findAll(SpringDataWebProperties.Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM Contenido",new ContenidoMapper());
    }

    @Override
    public Contenido findById(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT * FROM Contenido WHERE IdContenido = ?",params, new ContenidoMapper());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
