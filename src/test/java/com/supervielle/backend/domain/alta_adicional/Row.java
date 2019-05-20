package com.supervielle.backend.domain.alta_adicional;

public class Row {

    private Estado Estado;

    private String nroSolicitud;

    private CodigoResolucion codigoResolucion;

    public Estado getEstado() {
        return Estado;
    }

    public void setEstado(Estado Estado) {
        this.Estado = Estado;
    }

    public String getNroSolicitud() {
        return nroSolicitud;
    }

    public void setNroSolicitud(String nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }

    public CodigoResolucion getCodigoResolucion() {
        return codigoResolucion;
    }

    public void setCodigoResolucion(CodigoResolucion codigoResolucion) {
        this.codigoResolucion = codigoResolucion;
    }
}
