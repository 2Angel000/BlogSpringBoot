package com.mangel.startcms.data.maper;

import com.mangel.startcms.data.model.Grupo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GrupoMaper implements RowMapper<Grupo> {
    @Override
    public Grupo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Grupo grupo = new Grupo();
        grupo.setIdGrupo(rs.getInt("IdGrupo"));
        grupo.setNombre(rs.getString("Nombre"));
        grupo.setFecha(rs.getDate("Fecha"));
        return grupo;
    }
}
