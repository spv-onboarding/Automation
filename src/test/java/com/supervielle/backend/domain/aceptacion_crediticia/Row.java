package com.supervielle.backend.domain.aceptacion_crediticia;

public class Row {

    private EstadoGeneral        EstadoGeneral;

    private EvaluacionesGeneral  EvaluacionesGeneral;

    private EvaluacionesPersonas EvaluacionesPersonas;

    public EstadoGeneral getEstadoGeneral() {
        return EstadoGeneral;
    }

    public void setEstadoGeneral(EstadoGeneral EstadoGeneral) {
        this.EstadoGeneral = EstadoGeneral;
    }

    public EvaluacionesGeneral getEvaluacionesGeneral() {
        return EvaluacionesGeneral;
    }

    public void setEvaluacionesGeneral(EvaluacionesGeneral EvaluacionesGeneral) {
        this.EvaluacionesGeneral = EvaluacionesGeneral;
    }

    public EvaluacionesPersonas getEvaluacionesPersonas() {
        return EvaluacionesPersonas;
    }

    public void setEvaluacionesPersonas(EvaluacionesPersonas EvaluacionesPersonas) {
        this.EvaluacionesPersonas = EvaluacionesPersonas;
    }

}
