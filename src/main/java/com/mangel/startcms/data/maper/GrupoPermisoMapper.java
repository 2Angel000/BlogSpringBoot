package com.mangel.startcms.data.maper;

import com.mangel.startcms.data.model.GrupoPermiso;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GrupoPermisoMapper implements RowMapper<GrupoPermiso> {
    @Override
    public GrupoPermiso mapRow(ResultSet rs, int rowNum) throws SQLException {
        GrupoPermiso gp = new GrupoPermiso();
        gp.setIdGrupoPermiso(rs.getInt("IdGrupoPermiso"));
        gp.setIdGrupo(rs.getInt("IdGrupo"));
        gp.setIdPermiso(rs.getInt("IdPermiso"));
        return gp;
    }
}
