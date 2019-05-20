package com.supervielle.backend.domain.alta_producto.alta_voucher;

public class Solicitud {

    private String vencimiento;

    private String cvc;

    private String numSolicitud;

    private String descripcion;

    private String cuenta;

    private String codigoResolucion;

    private String tarjeta;

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(String numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCodigoResolucion() {
        return codigoResolucion;
    }

    public void setCodigoResolucion(String codigoResolucion) {
        this.codigoResolucion = codigoResolucion;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
}
