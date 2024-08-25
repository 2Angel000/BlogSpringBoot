package com.mangel.startcms.data.maper;

import com.mangel.startcms.data.model.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("IdUsuario"));
        usuario.setNombre(rs.getString("Nombre"));
        usuario.setApellido(rs.getString("Apellido"));
        usuario.setCorreo(rs.getString("Correo"));
        usuario.setFecha(rs.getDate("Fecha"));
        usuario.setContrasena(rs.getString("Contrasena"));
        usuario.setIdGrupo(rs.getInt("IdGrupo"));
        return usuario;
    }
}
