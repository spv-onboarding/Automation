package com.supervielle.backend.domain.aceptacion_crediticia.request;

public class RequestAceptacionCrediticia {

    private Data   Data;

    private String xmlns;

    public Data getData() {
        return Data;
    }

    public void setData(Data Data) {
        this.Data = Data;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

}
