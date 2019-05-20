package com.supervielle.backend.domain.aceptacion_crediticia;

public class Persona {
    private Identificador identificador;

    private Evaluacion[]  Evaluacion;

    public Identificador getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Identificador identificador) {
        this.identificador = identificador;
    }

    public Evaluacion[] getEvaluacion() {
        return Evaluacion;
    }

    public void setEvaluacion(Evaluacion[] Evaluacion) {
        this.Evaluacion = Evaluacion;
    }
}
