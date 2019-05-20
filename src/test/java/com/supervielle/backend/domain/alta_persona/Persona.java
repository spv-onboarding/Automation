package com.supervielle.backend.domain.alta_persona;

public class Persona {
    private String nombre;

    private Estado Estado;

    private String titularRepresentativo;

    private Identificador Identificador;

    private String cuentaCliente;

    private String titularidad;

    private String nroSolicitud;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return Estado;
    }

    public void setEstado(Estado Estado) {
        this.Estado = Estado;
    }

    public String getTitularRepresentativo() {
        return titularRepresentativo;
    }

    public void setTitularRepresentativo(String titularRepresentativo) {
        this.titularRepresentativo = titularRepresentativo;
    }

    public Identificador getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(Identificador Identificador) {
        this.Identificador = Identificador;
    }

    public String getCuentaCliente() {
        return cuentaCliente;
    }

    public void setCuentaCliente(String cuentaCliente) {
        this.cuentaCliente = cuentaCliente;
    }

    public String getTitularidad() {
        return titularidad;
    }

    public void setTitularidad(String titularidad) {
        this.titularidad = titularidad;
    }

    public String getNroSolicitud() {
        return nroSolicitud;
    }

    public void setNroSolicitud(String nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }
}
