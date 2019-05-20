package com.supervielle.backend.domain.preguntas_seguridad;

public class Resultado {
    private Pregunta[] preguntas ;
    private String cuestionarioId;
    private String lote;
    private String producto;
    private float cantidadPreguntas;

    public Pregunta[] getPreguntas() {
        return preguntas;
    }

    public String getCuestionarioId() {
        return cuestionarioId;
    }

    public String getProducto() {
        return producto;
    }

    public String getLote() {
        return lote;
    }

    public float getCantidadPreguntas() {
        return cantidadPreguntas;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public void setCuestionarioId(String cuestionarioId) {
        this.cuestionarioId = cuestionarioId;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setCantidadPreguntas(float cantidadPreguntas) {
        this.cantidadPreguntas = cantidadPreguntas;
    }

    public void setPreguntas(Pregunta[] preguntas) {
        this.preguntas = preguntas;
    }
}
