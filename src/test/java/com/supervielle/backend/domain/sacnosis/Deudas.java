package com.supervielle.backend.domain.sacnosis;

/**
 * @author globant | favio.veron
 */

public class Deudas {
    private Deuda[] Deuda;

    private String Cant;

    public Deuda[] getDeuda() {
        return Deuda;
    }

    public void setDeuda(Deuda[] Deuda) {
        this.Deuda = Deuda;
    }

    public String getCant() {
        return Cant;
    }

    public void setCant(String Cant) {
        this.Cant = Cant;
    }

    @Override
    public String toString() {
        return "ClassPojo [Deuda = " + Deuda + ", Cant = " + Cant + "]";
    }
}
