package com.supervielle.backend.domain.alta_persona.request;

public class Impuesto {

    private String codImpuesto;

    private String codCondInpuesto;

    public String getCodImpuesto() {
        return codImpuesto;
    }

    public void setCodImpuesto(String codImpuesto) {
        this.codImpuesto = codImpuesto;
    }

    public String getCodCondInpuesto() {
        return codCondInpuesto;
    }

    public void setCodCondInpuesto(String codCondInpuesto) {
        this.codCondInpuesto = codCondInpuesto;
    }
}
