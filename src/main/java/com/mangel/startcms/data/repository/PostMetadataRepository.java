package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.PostMetadataMapper;
import com.mangel.startcms.data.model.PostMetadata;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PostMetadataRepository implements PostMetadataRep{
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public boolean save(PostMetadata postMetadata) {
        try {
            String sql = String.format("INSERT INTO posts_Metadata(Clave, Valor, Tipo, IdPost) VALUES('%s', '%s', '%s',%d)",
                    postMetadata.getClave(), postMetadata.getValor(), postMetadata.getTipo(), postMetadata.getIdPost());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(PostMetadata postMetadata) {
        if(postMetadata.getIdPostMetadata() != 0){ //si está en nulo
            String sql = String.format("UPDATE posts_metadata SET Clave='%s', Valor='%s', Tipo='%s', IdPost=%d WHERE IdPostMetadata=%d ",
                   postMetadata.getClave(), postMetadata.getValor(), postMetadata.getTipo(), postMetadata.getIdPost(), postMetadata.getIdPostMetadata());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<PostMetadata> findAll(SpringDataWebProperties.Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM posts_metadata", new PostMetadataMapper());
    }

    @Override
    public PostMetadata findById(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT * FROM posts_metadata WHERE IdPostMetadata = ?", params, new PostMetadataMapper());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
