package com.mangel.startcms.data.maper;

import com.mangel.startcms.data.model.Categoria;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaMapper implements RowMapper<Categoria> {

    @Override
    public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("IdCategoria"));
        categoria.setNombre(rs.getString("Nombre"));
        categoria.setDescripcion(rs.getString("Descripcion"));
        categoria.setFecha(rs.getDate("Fecha"));
        categoria.setCategoriaSuperior(rs.getInt("CategoriaSuperior")); //rs.getTipo(NombreTabla)
        return categoria;
    }
}
