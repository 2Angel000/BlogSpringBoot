package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.UsuarioMapper;
import com.mangel.startcms.data.model.Usuario;
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
public class UsuarioRepository implements UsuarioRep{
    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean save(Usuario usuario) {
        try {
            String sql = String.format("INSERT INTO usuario(Nombre, Apellido, Correo, Contrasena, IdGrupo) VALUES('%s', '%s', '%s', '%s','%d')",
              usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getContrasena(), usuario.getIdGrupo());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("ERRORs\n" + e);
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario) {
        if(usuario.getIdUsuario() > 0){ //si está en nulo
            String sql = String.format("UPDATE usuario SET Nombre='%s', Apellido='%s', Correo='%s', Contrasena='%s', IdGrupo='%d' WHERE IdUsuario='%d' ",
                    usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getContrasena(), usuario.getIdGrupo(), usuario.getIdUsuario());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Usuario> findAll(SpringDataWebProperties.Pageable pageable) {
        return jdbcTemplate.query("select * from usuario ", new UsuarioMapper());
    }
    @Override
    public Usuario findById(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT * FROM usuario WHERE IdUsuario = ?", params, new UsuarioMapper());
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
            String sql = String.format("delete from usuario where IdUsuario = '%d'", id);
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            logger.error("Error deleting usuario", e);
            return false;
        }
    }
}
