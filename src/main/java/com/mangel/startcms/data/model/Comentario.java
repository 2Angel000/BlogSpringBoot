package com.mangel.startcms.data.model;

import java.util.Date;

public class Comentario {
private long idComentario;
private String Comentario;
private long IdPost;
private long IdUsuario;
private Date Fecha;
private String Respuesta;

    public long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(long idComentario) {
        this.idComentario = idComentario;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public long getIdPost() {
        return IdPost;
    }

    public void setIdPost(long idPost) {
        IdPost = idPost;
    }

    public long getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        IdUsuario = idUsuario;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getRespuesta() {
        return Respuesta;
    }

    public void setRespuesta(String respuesta) {
        Respuesta = respuesta;
    }
}
