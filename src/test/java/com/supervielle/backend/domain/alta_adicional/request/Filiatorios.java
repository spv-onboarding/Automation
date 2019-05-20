package com.supervielle.backend.domain.alta_adicional.request;

public class Filiatorios {

    private String codOcupacion;

    private String nomCliente;

    private String nomEmbozado;

    private String codNacionalidad;

    private String sexo;

    private String codEstadoCivil;

    private String fechaNacimiento;

    public String getCodOcupacion ()
    {
        return codOcupacion;
    }

    public void setCodOcupacion (String codOcupacion)
    {
        this.codOcupacion = codOcupacion;
    }

    public String getNomCliente ()
    {
        return nomCliente;
    }

    public void setNomCliente (String nomCliente)
    {
        this.nomCliente = nomCliente;
    }

    public String getNomEmbozado ()
    {
        return nomEmbozado;
    }

    public void setNomEmbozado (String nomEmbozado)
    {
        this.nomEmbozado = nomEmbozado;
    }

    public String getCodNacionalidad ()
    {
        return codNacionalidad;
    }

    public void setCodNacionalidad (String codNacionalidad)
    {
        this.codNacionalidad = codNacionalidad;
    }

    public String getSexo ()
    {
        return sexo;
    }

    public void setSexo (String sexo)
    {
        this.sexo = sexo;
    }

    public String getCodEstadoCivil ()
    {
        return codEstadoCivil;
    }

    public void setCodEstadoCivil (String codEstadoCivil)
    {
        this.codEstadoCivil = codEstadoCivil;
    }

    public String getFechaNacimiento ()
    {
        return fechaNacimiento;
    }

    public void setFechaNacimiento (String fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }
    
}
