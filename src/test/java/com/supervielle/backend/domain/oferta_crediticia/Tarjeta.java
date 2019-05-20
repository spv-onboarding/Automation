package com.supervielle.backend.domain.oferta_crediticia;

public class Tarjeta {
    private String porcentaje;

    private String limiteCompraDisponible;

    private String limite;

    private String codigo;

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getLimiteCompraDisponible() {
        return limiteCompraDisponible;
    }

    public void setLimiteCompraDisponible(String limiteCompraDisponible) {
        this.limiteCompraDisponible = limiteCompraDisponible;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
