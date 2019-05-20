package com.supervielle.backend.domain.alta_persona.request;

public class Persona {
    private SitImpositiva SitImpositiva;

    private Identificador identificador;

    private InformacionFinanciera InformacionFinanciera;

    private Domicilios Domicilios;

    private InfoPersona InfoPersona;

    public SitImpositiva getSitImpositiva() {
        return SitImpositiva;
    }

    public void setSitImpositiva(SitImpositiva SitImpositiva) {
        this.SitImpositiva = SitImpositiva;
    }

    public Identificador getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Identificador identificador) {
        this.identificador = identificador;
    }

    public InformacionFinanciera getInformacionFinanciera() {
        return InformacionFinanciera;
    }

    public void setInformacionFinanciera(InformacionFinanciera InformacionFinanciera) {
        this.InformacionFinanciera = InformacionFinanciera;
    }

    public Domicilios getDomicilios() {
        return Domicilios;
    }

    public void setDomicilios(Domicilios Domicilios) {
        this.Domicilios = Domicilios;
    }

    public InfoPersona getInfoPersona() {
        return InfoPersona;
    }

    public void setInfoPersona(InfoPersona InfoPersona) {
        this.InfoPersona = InfoPersona;
    }
}
