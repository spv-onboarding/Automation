package com.supervielle.backend.domain.sacnosis;

/**
 * @author globant | favio.veron
 */

public class Respuesta {

    private String   Id;

    private ParteXML ParteXML;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public ParteXML getParteXML() {
        return ParteXML;
    }

    public void setParteXML(ParteXML ParteXML) {
        this.ParteXML = ParteXML;
    }

}
