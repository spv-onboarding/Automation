package com.supervielle.backend.domain.sacnosis;

/**
 * @author globant | favio.veron
 */

public class UltimoInforme {
    private String CompromisosMensuales;

    private Deudas Deudas;

    public String getCompromisosMensuales() {
        return CompromisosMensuales;
    }

    public void setCompromisosMensuales(String CompromisosMensuales) {
        this.CompromisosMensuales = CompromisosMensuales;
    }

    public Deudas getDeudas() {
        return Deudas;
    }

    public void setDeudas(Deudas Deudas) {
        this.Deudas = Deudas;
    }

}
