package com.supervielle.backend.domain.alta_producto.alta_voucher;

public class Data {

    private String descripcion;

    private String codigoResolucion;

    private Solicitud solicitud;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoResolucion() {
        return codigoResolucion;
    }

    public void setCodigoResolucion(String codigoResolucion) {
        this.codigoResolucion = codigoResolucion;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

}
