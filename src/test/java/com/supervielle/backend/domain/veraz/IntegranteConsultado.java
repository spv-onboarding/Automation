package com.supervielle.backend.domain.veraz;

/**
 * @author globant | favio.veron
 */
public class IntegranteConsultado {

    private String nombre;

    private Variables[] Variables;

    private Identificador Identificador;

    private String sexo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Variables[] getVariables() {
        return Variables;
    }

    public void setVariables(Variables[] Variables) {
        this.Variables = Variables;
    }

    public Identificador getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(Identificador Identificador) {
        this.Identificador = Identificador;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
