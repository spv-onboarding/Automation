package com.supervielle.backend.domain;

import java.sql.Date;

public class DatoFiliatorio {

    private Integer clienteId;
    private String paisNacimiento;
    private String lugarNacimiento;
    private Integer residente;
    private Integer estadoCivil;
    private Date fechaNacimiento;
    private Integer dobleCiudadania;
    private String paisResidencia;
    private String paisDobleCiudadania;
    private String documentoDobleCiudadania;

    public DatoFiliatorio() {
    }


    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Integer getResidente() {
        return residente;
    }

    public void setResidente(Integer residente) {
        this.residente = residente;
    }

    public Integer getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Integer estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getDobleCiudadania() {
        return dobleCiudadania;
    }

    public void setDobleCiudadania(Integer dobleCiudadania) {
        this.dobleCiudadania = dobleCiudadania;
    }

    public String getPaisResidencia() {
        return paisResidencia;
    }

    public void setPaisResidencia(String paisResidencia) {
        this.paisResidencia = paisResidencia;
    }

    public String getPaisDobleCiudadania() {
        return paisDobleCiudadania;
    }

    public void setPaisDobleCiudadania(String paisDobleCiudadania) {
        this.paisDobleCiudadania = paisDobleCiudadania;
    }

    public String getDocumentoDobleCiudadania() {
        return documentoDobleCiudadania;
    }

    public void setDocumentoDobleCiudadania(String documentoDobleCiudadania) {
        this.documentoDobleCiudadania = documentoDobleCiudadania;
    }
}
