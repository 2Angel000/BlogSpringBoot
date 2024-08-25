package com.mangel.startcms.data.model;

import java.util.Date;

public class Usuario {
    private long IdUsuario;
    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Contrasena;
    private Date Fecha;
    private long IdGrupo;

    public long getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public long getIdGrupo() {
        return IdGrupo;
    }

    public void setIdGrupo(long idGrupo) {
        IdGrupo = idGrupo;
    }
}
