package com.supervielle.backend.domain;

import java.math.BigDecimal;

public class DatoAnalitico
{
    private Integer RentaTitular;
    private Integer OfertaCrediticiaPoliticaID;
    private String CategoriaNSE;
    private BigDecimal CompromisoOtrosBancos;
    private Integer ActividadID;
    private Integer Edad;
    private Integer ProvinciaId;

    public DatoAnalitico() {
    }

    public Integer getRentaTitular() {
        return RentaTitular;
    }

    public void setRentaTitular(Integer rentaTitular) {
        RentaTitular = rentaTitular;
    }

    public Integer getOfertaCrediticiaPoliticaID() {
        return OfertaCrediticiaPoliticaID;
    }

    public void setOfertaCrediticiaPoliticaID(Integer ofertaCrediticiaPoliticaID) {
        OfertaCrediticiaPoliticaID = ofertaCrediticiaPoliticaID;
    }

    public String getCategoriaNSE() {
        return CategoriaNSE;
    }

    public void setCategoriaNSE(String categoriaNSE) {
        CategoriaNSE = categoriaNSE;
    }

    public BigDecimal getCompromisoOtrosBancos() {
        return CompromisoOtrosBancos;
    }

    public void setCompromisoOtrosBancos(BigDecimal compromisoOtrosBancos) {
        CompromisoOtrosBancos = compromisoOtrosBancos;
    }

    public Integer getActividadID() {
        return ActividadID;
    }

    public void setActividadID(Integer actividadID) {
        ActividadID = actividadID;
    }

    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer edad) {
        Edad = edad;
    }

    public Integer getProvinciaId() {
        return ProvinciaId;
    }

    public void setProvinciaId(Integer provinciaId) {
        ProvinciaId = provinciaId;
    }
}
