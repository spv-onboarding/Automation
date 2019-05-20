package com.supervielle.backend.domain.alta_producto.alta_voucher.request;

public class Data {
    private String operacion;

    private Principal principal;

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
