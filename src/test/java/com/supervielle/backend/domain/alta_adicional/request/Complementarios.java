package com.supervielle.backend.domain.alta_adicional.request;

public class Complementarios {
    
    private String cuentaSocio;

    private String fechaSolicitud;

    private String usuarioCarga;

    public String getCuentaSocio() {
        return cuentaSocio;
    }

    public void setCuentaSocio(String cuentaSocio) {
        this.cuentaSocio = cuentaSocio;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getUsuarioCarga() {
        return usuarioCarga;
    }

    public void setUsuarioCarga(String usuarioCarga) {
        this.usuarioCarga = usuarioCarga;
    }
}
