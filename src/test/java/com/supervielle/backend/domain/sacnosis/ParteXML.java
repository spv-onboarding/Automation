package com.supervielle.backend.domain.sacnosis;

import java.util.List;

/**
 * @author globant | favio.veron
 */

public class ParteXML {
    private List<Dato> Dato;

    private String Cant;

    private String Existe;



    public String getCant() {
        return Cant;
    }

    public void setCant(String Cant) {
        this.Cant = Cant;
    }

    public String getExiste() {
        return Existe;
    }

    public void setExiste(String Existe) {
        this.Existe = Existe;
    }

    public List<com.supervielle.backend.domain.sacnosis.Dato> getDato() {
        return Dato;
    }

    public void setDato(List<com.supervielle.backend.domain.sacnosis.Dato> dato) {
        Dato = dato;
    }
}
