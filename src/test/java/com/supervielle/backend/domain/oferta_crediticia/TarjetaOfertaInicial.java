package com.supervielle.backend.domain.oferta_crediticia;

import com.google.gson.annotations.SerializedName;

public class TarjetaOfertaInicial {


    @SerializedName("limiteCompraDisponible")
    private String limiteCompraDisponible;

    @SerializedName("porcentaje")
    private String porcentaje;

    @SerializedName("limite")
    private String limite;

    public String getLimiteCompraDisponible() {
        return limiteCompraDisponible;
    }

    public void setLimiteCompraDisponible(String limiteCompraDisponible) {
        this.limiteCompraDisponible = limiteCompraDisponible;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

}
