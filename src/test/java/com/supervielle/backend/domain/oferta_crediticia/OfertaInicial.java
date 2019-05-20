package com.supervielle.backend.domain.oferta_crediticia;

import com.google.gson.annotations.SerializedName;

public class OfertaInicial {

    @SerializedName("Visa")
    private TarjetaOfertaInicial visa;
    @SerializedName("acuerdo")
    private String acuerdo;
    @SerializedName("Master")
    private TarjetaOfertaInicial master;

    public TarjetaOfertaInicial getVisa() {
        return visa;
    }

    public void setVisa(TarjetaOfertaInicial visa) {
        this.visa = visa;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public TarjetaOfertaInicial getMaster() {
        return master;
    }

    public void setMaster(TarjetaOfertaInicial master) {
        this.master = master;
    }
}
