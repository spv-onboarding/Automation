package com.supervielle.backend.domain.preguntas_seguridad;

public class Pregunta {

    private String id;
    private String orden;
    private Opcion[] opciones;
    private String texto;
    private String cantidadOpciones;
    private String respuesta;

    public String getId() {
        return id;
    }

    public String getOrden() {
        return orden;
    }

    public Opcion[] getOpciones() {
        return opciones;
    }

    public String getTexto() {
        return texto;
    }

    public String getCantidadOpciones() {
        return cantidadOpciones;
    }

    public String getRespuesta() {
        return respuesta;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public void setOpciones(Opcion[] opciones) {
        this.opciones = opciones;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setCantidadOpciones(String cantidadOpciones) {
        this.cantidadOpciones = cantidadOpciones;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
