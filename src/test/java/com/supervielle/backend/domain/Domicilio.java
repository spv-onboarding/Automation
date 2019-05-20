package com.supervielle.backend.domain;

public class Domicilio {

    private Integer Id;
    private String Calle;
    private Integer Altura;
    private String Localidad;
    private String Provincia;
    private Integer CodigoPostal;
    private Integer Piso;
    private String Departamento;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public Integer getAltura() {
        return Altura;
    }

    public void setAltura(Integer altura) {
        Altura = altura;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String localidad) {
        Localidad = localidad;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public Integer getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        CodigoPostal = codigoPostal;
    }

    public Integer getPiso() {
        return Piso;
    }

    public void setPiso(Integer piso) {
        Piso = piso;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }
}
