package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.ComentarioMapper;
import com.mangel.startcms.data.model.Comentario;
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
public class ComentarioRepository implements ComentarioRep{
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private Log logger = LogFactory.getLog(getClass());

    @PostConstruct
    public void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean save(Comentario comentario) {
        try{
            String sql = String.format("INSERT INTO comentario(Comentario, IdPost, IdUsuario, Respuesta) VALUES('%s',%d,%d,'%s')",
            comentario.getComentario(), comentario.getIdPost(), comentario.getIdUsuario(), comentario.getRespuesta());
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            System.out.println("ERROR:\n " + e);
            return false;
        }
    }

    @Override
    public boolean update(Comentario comentario){
        if(comentario.getIdComentario() != 0){
            String sql = String.format("UPDATE comentario SET Comentario='%s', IdPost=%d, IdUsuario=%d, Respuesta='%s' WHERE IdComentario=%d",
                    comentario.getComentario(), comentario.getIdPost(), comentario.getIdUsuario(), comentario.getRespuesta(), comentario.getIdComentario());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Comentario> findAll(SpringDataWebProperties.Pageable pageable) {
        return jdbcTemplate.query("SELECT*FROM Comentario",new ComentarioMapper());
    }

    @Override
    public Comentario findById(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT*FROM Comentario WHERE IdComentario = ?", params, new ComentarioMapper());
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
            String sql = String.format("delete from comentario where IdComentario='%d'",id);
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            logger.error("Error deleting:\n"+e);
            return false;
        }
    }

}
