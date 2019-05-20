package com.supervielle.backend.domain.alta_persona.request;

public class Domicilio {
    private String codLocalidad;

    private String cp;

    private String codPais;

    private String email;

    private String codDomicilio;

    private String telefono;

    private String codProvincia;

    private String piso;

    private String cpNuevo;

    private String calle;

    private String numero;

    public String getCodLocalidad() {
        return codLocalidad;
    }

    public void setCodLocalidad(String codLocalidad) {
        this.codLocalidad = codLocalidad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodDomicilio() {
        return codDomicilio;
    }

    public void setCodDomicilio(String codDomicilio) {
        this.codDomicilio = codDomicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getCpNuevo() {
        return cpNuevo;
    }

    public void setCpNuevo(String cpNuevo) {
        this.cpNuevo = cpNuevo;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
