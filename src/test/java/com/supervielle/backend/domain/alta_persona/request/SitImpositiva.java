package com.supervielle.backend.domain.alta_persona.request;

public class SitImpositiva {

    private Impuesto[] Impuesto;

    public Impuesto[] getImpuesto ()
    {
        return Impuesto;
    }

    public void setImpuesto (Impuesto[] Impuesto)
    {
        this.Impuesto = Impuesto;
    }
}
