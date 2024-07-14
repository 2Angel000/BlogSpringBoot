package com.mangel.startcms.data.maper;

import com.mangel.startcms.data.model.PostMetadata;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMetadataMapper implements RowMapper<PostMetadata> {
    @Override
    public PostMetadata mapRow(ResultSet rs, int rowNum) throws SQLException {
        PostMetadata pm = new PostMetadata();
        pm.setIdPostMetadata(rs.getInt("IdPostMetadata"));
        pm.setClave(rs.getString("Clave"));
        pm.setValor(rs.getString("Valor"));
        pm.setTipo(rs.getString("Tipo"));
        pm.setIdPost(rs.getInt("IdPost"));
        return pm;
    }
}
