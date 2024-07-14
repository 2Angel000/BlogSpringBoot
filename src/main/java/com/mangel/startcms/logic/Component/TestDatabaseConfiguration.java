package com.mangel.startcms.logic.Component;

import com.mangel.startcms.data.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//@Configuration
public class TestDatabaseConfiguration {
    @Bean
    public DataSource getDatabaseSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_springblogdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("13102000");
        return dataSource;
    }
    //INYECTAR TODAS LAS CLASES
    @Bean
    public CategoriaRepository categoriaRepository(){
            return new CategoriaRepository();
    }

    @Bean
    public UsuarioRepository usuarioRepository(){
        return new UsuarioRepository();
    }

    @Bean
    public ComentarioRepository comentarioRepository(){
        return new ComentarioRepository();
    }

    @Bean
    public ContenidoRepository contenidoRepository(){
        return new ContenidoRepository();
    }

    @Bean
    public PostRepository postRepository(){
        return new PostRepository();
    }

    @Bean
    public GrupoRepository grupoRepository(){
        return new GrupoRepository();
    }

    @Bean
    public GrupoPermisoRepository grupoPermisoRepository(){
        return new GrupoPermisoRepository();
    }

    @Bean
    public PermisoRepository permisoRepository(){
        return new PermisoRepository();
    }

    @Bean
    public PostMetadataRepository postMetadataRepository(){
        return new PostMetadataRepository();
    }

    @Bean
    public UsuarioMetadataRepository usuarioMetadataRepository(){
        return new UsuarioMetadataRepository();
    }

}
