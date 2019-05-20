package com.supervielle.backend.domain.preguntas_seguridad;

import java.io.Serializable;

public class ResponsePreguntasSeguridad implements Serializable {

    private Resultado result;
    private String version;
    private String code;
    private String message;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Resultado getResult() {
        return result;
    }

    public void setResult(Resultado resultado) {
        this.result = resultado;
    }
}

