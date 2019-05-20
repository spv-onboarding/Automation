package com.supervielle.backend.domain.oferta_crediticia;

public class Resultado {
    private NormalizacionTarjetas NormalizacionTarjetas;

    private OfertaInicial OfertaInicial;

    private PaqueteList Paquetes;

    private String paqueteUtilizado;

    public NormalizacionTarjetas getNormalizacionTarjetas() {
        return NormalizacionTarjetas;
    }

    public void setNormalizacionTarjetas(NormalizacionTarjetas normalizacionTarjetas) {
        NormalizacionTarjetas = normalizacionTarjetas;
    }

    public OfertaInicial getOfertaInicial() {
        return OfertaInicial;
    }

    public void setOfertaInicial(OfertaInicial ofertaInicial) {
        OfertaInicial = ofertaInicial;
    }

    public PaqueteList getPaquetes() {
        return Paquetes;
    }

    public void setPaquetes(PaqueteList paquetes) {
        Paquetes = paquetes;
    }

    public String getPaqueteUtilizado() {
        return paqueteUtilizado;
    }

    public void setPaqueteUtilizado(String paqueteUtilizado) {
        this.paqueteUtilizado = paqueteUtilizado;
    }
}
