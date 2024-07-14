package com.mangel.startcms.data.model;

import java.util.Date;

public class Categoria {
    private long idCategoria;
    private String Nombre;
    private String Descripcion;
    private Date Fecha;
    private long CategoriaSuperior;

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public long getCategoriaSuperior() {
        return CategoriaSuperior;
    }

    public void setCategoriaSuperior(long categoriaSuperior) {
        CategoriaSuperior = categoriaSuperior;
    }
}
