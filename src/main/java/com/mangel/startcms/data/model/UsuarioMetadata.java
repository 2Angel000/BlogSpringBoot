package com.mangel.startcms.data.model;

public class UsuarioMetadata {
    private long IdUsuarioMetadata;
    private long Usuario;
    private String Clave;
    private String Valor;
    private String Tipo;

    public long getIdUsuarioMetadata() {
        return IdUsuarioMetadata;
    }

    public void setIdUsuarioMetadata(long idUsuarioMetadata) {
        IdUsuarioMetadata = idUsuarioMetadata;
    }

    public long getUsuario() {
        return Usuario;
    }

    public void setUsuario(long usuario) {
        Usuario = usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
