package com.supervielle.backend.domain.alta_persona;

public class Identificador {
    private String numDoc;

    private String paisOrigen;

    private String tipoDoc;

    public String getNumDoc ()
    {
        return numDoc;
    }

    public void setNumDoc (String numDoc)
    {
        this.numDoc = numDoc;
    }

    public String getPaisOrigen ()
    {
        return paisOrigen;
    }

    public void setPaisOrigen (String paisOrigen)
    {
        this.paisOrigen = paisOrigen;
    }

    public String getTipoDoc ()
    {
        return tipoDoc;
    }

    public void setTipoDoc (String tipoDoc)
    {
        this.tipoDoc = tipoDoc;
    }

}
