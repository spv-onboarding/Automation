package com.supervielle.backend.domain.aceptacion_crediticia.request;

public class Persona {
    private String          ingreso;

    private Identificador   identificador;

    private Score           Score;

    private String          mancomuna;

    private DatosNoclientes DatosNoclientes;

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public Identificador getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Identificador identificador) {
        this.identificador = identificador;
    }

    public Score getScore() {
        return Score;
    }

    public void setScore(Score Score) {
        this.Score = Score;
    }

    public String getMancomuna() {
        return mancomuna;
    }

    public void setMancomuna(String mancomuna) {
        this.mancomuna = mancomuna;
    }

    public DatosNoclientes getDatosNoclientes() {
        return DatosNoclientes;
    }

    public void setDatosNoclientes(DatosNoclientes DatosNoclientes) {
        this.DatosNoclientes = DatosNoclientes;
    }
}
