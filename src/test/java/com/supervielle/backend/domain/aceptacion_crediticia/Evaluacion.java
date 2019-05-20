package com.supervielle.backend.domain.aceptacion_crediticia;

public class Evaluacion {
    private Estado Estado;

    private String tipoRegla;

    private String idRegla;

    public Estado getEstado() {
        return Estado;
    }

    public void setEstado(Estado Estado) {
        this.Estado = Estado;
    }

    public String getTipoRegla() {
        return tipoRegla;
    }

    public void setTipoRegla(String tipoRegla) {
        this.tipoRegla = tipoRegla;
    }

    public String getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(String idRegla) {
        this.idRegla = idRegla;
    }
}
