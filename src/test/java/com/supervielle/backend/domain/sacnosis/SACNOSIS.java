package com.supervielle.backend.domain.sacnosis;

/**
 * @author globant | favio.veron
 */

public class SACNOSIS {
    private Respuesta Respuesta;

    public Respuesta getRespuesta() {
        return Respuesta;
    }

    public void setRespuesta(Respuesta Respuesta) {
        this.Respuesta = Respuesta;
    }

    @Override
    public String toString() {
        return "ClassPojo [Respuesta = " + Respuesta + "]";
    }
}
