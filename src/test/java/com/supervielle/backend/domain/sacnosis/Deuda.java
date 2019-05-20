package com.supervielle.backend.domain.sacnosis;

/**
 * @author globant | favio.veron
 */

public class Deuda {

    private String CodEntidad;

    private String Entidad;

    private String Periodo;

    private String Sit;

    private String Ent_Cod;

    private String Monto;

    private String Proc_Jud;

    public String getCodEntidad() {
        return CodEntidad;
    }

    public void setCodEntidad(String CodEntidad) {
        this.CodEntidad = CodEntidad;
    }

    public String getEntidad() {
        return Entidad;
    }

    public void setEntidad(String Entidad) {
        this.Entidad = Entidad;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String Periodo) {
        this.Periodo = Periodo;
    }

    public String getSit() {
        return Sit;
    }

    public void setSit(String Sit) {
        this.Sit = Sit;
    }

    public String getEnt_Cod() {
        return Ent_Cod;
    }

    public void setEnt_Cod(String Ent_Cod) {
        this.Ent_Cod = Ent_Cod;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String Monto) {
        this.Monto = Monto;
    }

    public String getProc_Jud() {
        return Proc_Jud;
    }

    public void setProc_Jud(String Proc_Jud) {
        this.Proc_Jud = Proc_Jud;
    }

}
