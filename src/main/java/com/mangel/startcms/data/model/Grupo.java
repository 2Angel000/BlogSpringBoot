package com.mangel.startcms.data.model;

import java.util.Date;

public class Grupo {
    private long IdGrupo;
    private String Nombre;

    private Date Fecha;

    public long getIdGrupo() {
        return IdGrupo;
    }

    public void setIdGrupo(long idGrupo) {
        IdGrupo = idGrupo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
}
