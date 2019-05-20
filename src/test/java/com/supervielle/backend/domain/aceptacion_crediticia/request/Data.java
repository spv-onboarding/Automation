package com.supervielle.backend.domain.aceptacion_crediticia.request;

public class Data {
    
    private String   idSolicitud;

    private Personas Personas;

    private String   segmento;

    private String   xmlns;

    private String   canal;

    private String   consultaNosis;
    
    private Politica politica;

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Personas getPersonas() {
        return Personas;
    }

    public void setPersonas(Personas Personas) {
        this.Personas = Personas;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getConsultaNosis() {
        return consultaNosis;
    }

    public void setConsultaNosis(String consultaNosis) {
        this.consultaNosis = consultaNosis;
    }
    
    public Politica getPolitica() {
        return politica;
    }

    public void setPolitica(Politica politica) {
        this.politica = politica;
    }
}
