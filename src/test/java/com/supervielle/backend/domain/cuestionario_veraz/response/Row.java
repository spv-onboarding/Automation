package com.supervielle.backend.domain.cuestionario_veraz.response;


public class Row {
    private String resultado;
    private Respuesta respuesta;

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
