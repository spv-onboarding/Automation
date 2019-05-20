package com.supervielle.backend.domain.alta_adicional.request;

public class TitularAdicional {

    private Principal Principal;

    private Filiatorios Filiatorios;

    private Complementarios Complementarios;

    private String codOperacion;

    public Principal getPrincipal ()
    {
        return Principal;
    }

    public void setPrincipal (Principal Principal)
    {
        this.Principal = Principal;
    }

    public Filiatorios getFiliatorios ()
    {
        return Filiatorios;
    }

    public void setFiliatorios (Filiatorios Filiatorios)
    {
        this.Filiatorios = Filiatorios;
    }

    public Complementarios getComplementarios ()
    {
        return Complementarios;
    }

    public void setComplementarios (Complementarios Complementarios)
    {
        this.Complementarios = Complementarios;
    }

    public String getCodOperacion ()
    {
        return codOperacion;
    }

    public void setCodOperacion (String codOperacion)
    {
        this.codOperacion = codOperacion;
    }
    
}
