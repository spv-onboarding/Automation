package com.supervielle.backend.domain.alta_persona.request;

public class Data {
    private String aplicacionExterna;

    private Personas Personas;

    private String nroSolicitud;

    public String getAplicacionExterna() {
        return aplicacionExterna;
    }

    public void setAplicacionExterna(String aplicacionExterna) {
        this.aplicacionExterna = aplicacionExterna;
    }

    public Personas getPersonas() {
        return Personas;
    }

    public void setPersonas(Personas Personas) {
        this.Personas = Personas;
    }

    public String getNroSolicitud() {
        return nroSolicitud;
    }

    public void setNroSolicitud(String nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }

}
