package com.supervielle.backend.domain.veraz;

/**
 * @author globant | favio.veron
 */

public class Row {
    private String Informe;

    private IntegranteConsultado IntegranteConsultado;

    public String getInforme ()
    {
        return Informe;
    }

    public void setInforme (String Informe)
    {
        this.Informe = Informe;
    }

    public IntegranteConsultado getIntegranteConsultado ()
    {
        return IntegranteConsultado;
    }

    public void setIntegranteConsultado (IntegranteConsultado IntegranteConsultado)
    {
        this.IntegranteConsultado = IntegranteConsultado;
    }


}


