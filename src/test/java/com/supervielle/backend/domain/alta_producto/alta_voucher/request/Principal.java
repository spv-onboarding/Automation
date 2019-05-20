package com.supervielle.backend.domain.alta_producto.alta_voucher.request;

public class Principal {
    private String tribuDocuNume;

    private String tribuDocuTipo;

    private String entidad;

    private String marca;

    private RegistroAltaBATCH registroAltaBATCH;

    private String usuarioCarga;

    private String cuentaBantotal;

    private String codigoProducto;

    public String getTribuDocuNume ()
    {
        return tribuDocuNume;
    }

    public void setTribuDocuNume (String tribuDocuNume)
    {
        this.tribuDocuNume = tribuDocuNume;
    }

    public String getTribuDocuTipo ()
    {
        return tribuDocuTipo;
    }

    public void setTribuDocuTipo (String tribuDocuTipo)
    {
        this.tribuDocuTipo = tribuDocuTipo;
    }

    public String getEntidad ()
    {
        return entidad;
    }

    public void setEntidad (String entidad)
    {
        this.entidad = entidad;
    }

    public String getMarca ()
    {
        return marca;
    }

    public void setMarca (String marca)
    {
        this.marca = marca;
    }

    public RegistroAltaBATCH getRegistroAltaBATCH ()
    {
        return registroAltaBATCH;
    }

    public void setRegistroAltaBATCH (RegistroAltaBATCH registroAltaBATCH)
    {
        this.registroAltaBATCH = registroAltaBATCH;
    }

    public String getUsuarioCarga ()
    {
        return usuarioCarga;
    }

    public void setUsuarioCarga (String usuarioCarga)
    {
        this.usuarioCarga = usuarioCarga;
    }

    public String getCuentaBantotal ()
    {
        return cuentaBantotal;
    }

    public void setCuentaBantotal (String cuentaBantotal)
    {
        this.cuentaBantotal = cuentaBantotal;
    }

    public String getCodigoProducto ()
    {
        return codigoProducto;
    }

    public void setCodigoProducto (String codigoProducto)
    {
        this.codigoProducto = codigoProducto;
    }
}
