package com.supervielle.backend.domain.sacnosis;

import java.util.List;

public class CalculoCDA {
    private List<Item> Item;

    private String Titulo;

    private String Documento;

    private String Fecha;

    private String NroCDA;

    private String Version;

    private String RazonSocial;



    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getNroCDA() {
        return NroCDA;
    }

    public void setNroCDA(String NroCDA) {
        this.NroCDA = NroCDA;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    public List<com.supervielle.backend.domain.sacnosis.Item> getItem() {
        return Item;
    }

    public void setItem(List<com.supervielle.backend.domain.sacnosis.Item> item) {
        Item = item;
    }
}
