package com.supervielle.backend.domain.alta_persona.request;

public class UIF {

    private String presentoDDJJUIF;

    private String sujetoObligatorio;

    private String tipoSujetoObligatorio;

    private String presentoCertInscrUIF;

    public String getPresentoDDJJUIF() {
        return presentoDDJJUIF;
    }

    public void setPresentoDDJJUIF(String presentoDDJJUIF) {
        this.presentoDDJJUIF = presentoDDJJUIF;
    }

    public String getSujetoObligatorio() {
        return sujetoObligatorio;
    }

    public void setSujetoObligatorio(String sujetoObligatorio) {
        this.sujetoObligatorio = sujetoObligatorio;
    }

    public String getTipoSujetoObligatorio() {
        return tipoSujetoObligatorio;
    }

    public void setTipoSujetoObligatorio(String tipoSujetoObligatorio) {
        this.tipoSujetoObligatorio = tipoSujetoObligatorio;
    }

    public String getPresentoCertInscrUIF() {
        return presentoCertInscrUIF;
    }

    public void setPresentoCertInscrUIF(String presentoCertInscrUIF) {
        this.presentoCertInscrUIF = presentoCertInscrUIF;
    }
}
