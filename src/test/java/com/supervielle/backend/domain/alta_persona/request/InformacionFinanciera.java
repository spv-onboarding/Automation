package com.supervielle.backend.domain.alta_persona.request;

public class InformacionFinanciera {

    private UIF UIF;

    private FATCA FATCA;

    public UIF getUIF() {
        return UIF;
    }

    public void setUIF(UIF UIF) {
        this.UIF = UIF;
    }

    public FATCA getFATCA() {
        return FATCA;
    }

    public void setFATCA(FATCA FATCA) {
        this.FATCA = FATCA;
    }

}
