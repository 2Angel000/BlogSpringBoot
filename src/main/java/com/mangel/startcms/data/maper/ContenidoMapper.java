package com.mangel.startcms.data.maper;

import com.mangel.startcms.data.model.Contenido;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContenidoMapper implements RowMapper<Contenido> {
    @Override
    public Contenido mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contenido contenido = new Contenido();
        contenido.setIdContenido(rs.getInt("IdContenido"));
        contenido.setTipo(rs.getString("Tipo"));
        contenido.setContenido(rs.getString("Contenido"));
        contenido.setIdPost(rs.getInt("IdPost"));
        return contenido;
    }
}
