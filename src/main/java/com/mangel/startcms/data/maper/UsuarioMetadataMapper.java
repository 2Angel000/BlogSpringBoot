package com.mangel.startcms.data.maper;

import com.mangel.startcms.data.model.UsuarioMetadata;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMetadataMapper implements RowMapper<UsuarioMetadata> {
    @Override
    public UsuarioMetadata mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsuarioMetadata um = new UsuarioMetadata();
        um.setIdUsuarioMetadata(rs.getInt("IdUsuarioMetadata"));
        um.setUsuario(rs.getInt("Usuario"));
        um.setClave(rs.getString("Clave"));
        um.setValor(rs.getString("Valor"));
        um.setTipo(rs.getString("Tipo"));
        return um;
    }
}
