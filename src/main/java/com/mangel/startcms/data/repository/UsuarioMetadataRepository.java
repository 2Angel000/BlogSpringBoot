package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.UsuarioMetadataMapper;
import com.mangel.startcms.data.model.UsuarioMetadata;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UsuarioMetadataRepository implements UsuarioMetadataRep{
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public boolean save(UsuarioMetadata usuarioMetadata) {
        try {
            String sql = String.format("INSERT INTO usuario_metadata(Usuario, Clave, Valor, Tipo) VALUES(%d,'%s', '%s', '%s')",
                   usuarioMetadata.getUsuario(), usuarioMetadata.getClave(), usuarioMetadata.getValor(), usuarioMetadata.getTipo());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(UsuarioMetadata usuarioMetadata) {
        if(usuarioMetadata.getIdUsuarioMetadata() != 0){ //si está en nulo
            String sql = String.format("UPDATE usuario_metadata SET Usuario=%d, Clave='%s', Valor='%s', Tipo='%s' WHERE IdUsuarioMetadata= %d ",
                  usuarioMetadata.getUsuario(),usuarioMetadata.getClave(), usuarioMetadata.getValor(), usuarioMetadata.getTipo(), usuarioMetadata.getIdUsuarioMetadata());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<UsuarioMetadata> findAll(SpringDataWebProperties.Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM Usuario_Metadata", new UsuarioMetadataMapper());
    }

    @Override
    public UsuarioMetadata findById(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT * FROM Usuario_Metadata WHERE IdUsuarioMetadata = ?",params, new UsuarioMetadataMapper());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
