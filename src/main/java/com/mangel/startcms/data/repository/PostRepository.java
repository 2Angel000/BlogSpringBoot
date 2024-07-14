package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.maper.PostMapper;
import com.mangel.startcms.data.model.Post;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PostRepository implements PostRep{
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean save(Post post) {
        try {
            String sql = String.format("INSERT INTO post(Titulo, Slug, Extracto, IdUsuario, IdCategoria, ImagenDestacada, Tipo) VALUES('%s', '%s', '%s', %d, %d, '%s', '%s')",
                    post.getTitulo(), post.getSlug(), post.getExtracto(), post.getIdUsuario(), post.getIdCategoria(), post.getImagenDestacada(), post.getTipo());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("ERRORs\n" + e);
            return false;
        }
    }

    @Override
    public boolean update(Post post) {
        if(post.getIdPost() != 0){ //si está en nulo
            String sql = String.format("UPDATE post SET Titulo='%s', Slug='%s', Extracto='%s', IdUsuario=%d, IdCategoria=%d, ImagenDestacada='%s', Tipo='%s' WHERE IdPost=%d",
                    post.getTitulo(), post.getSlug(), post.getExtracto(), post.getIdUsuario(), post.getIdCategoria(), post.getImagenDestacada(), post.getTipo(), post.getIdPost());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Post> findAll(SpringDataWebProperties.Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM Post", new PostMapper());
    }

    @Override
    public Post findById(int Id) {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT * FROM Post WHERE IdPost = ?", params,new PostMapper());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
