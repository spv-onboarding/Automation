package com.supervielle.backend.domain.oferta_crediticia;

public class Paquete {

    private String prestamoDescripcion;
    private TarjetaPaquete Visa;
    private String esIdentite;
    private String descripcion;
    private String incluyePrestamo;
    private String linea;
    private String incluyeAcuerdo;
    private String id;
    private String codigo;
    private String prestamoEnTarjetaDeCredito;
    private String paqueteBTID;
    private String tipoOperacion;
    private String permiteAdelantosEnBeneficios;
    private String modulo;
    private String maxCantTarjetas;
    private TarjetaPaquete Master;

    public String getPrestamoDescripcion() {
        return prestamoDescripcion;
    }

    public void setPrestamoDescripcion(String prestamoDescripcion) {
        this.prestamoDescripcion = prestamoDescripcion;
    }

    public TarjetaPaquete getVisa() {
        return Visa;
    }

    public void setVisa(TarjetaPaquete visa) {
        Visa = visa;
    }

    public String getEsIdentite() {
        return esIdentite;
    }

    public void setEsIdentite(String esIdentite) {
        this.esIdentite = esIdentite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIncluyePrestamo() {
        return incluyePrestamo;
    }

    public void setIncluyePrestamo(String incluyePrestamo) {
        this.incluyePrestamo = incluyePrestamo;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getIncluyeAcuerdo() {
        return incluyeAcuerdo;
    }

    public void setIncluyeAcuerdo(String incluyeAcuerdo) {
        this.incluyeAcuerdo = incluyeAcuerdo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrestamoEnTarjetaDeCredito() {
        return prestamoEnTarjetaDeCredito;
    }

    public void setPrestamoEnTarjetaDeCredito(String prestamoEnTarjetaDeCredito) {
        this.prestamoEnTarjetaDeCredito = prestamoEnTarjetaDeCredito;
    }

    public String getPaqueteBTID() {
        return paqueteBTID;
    }

    public void setPaqueteBTID(String paqueteBTID) {
        this.paqueteBTID = paqueteBTID;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getPermiteAdelantosEnBeneficios() {
        return permiteAdelantosEnBeneficios;
    }

    public void setPermiteAdelantosEnBeneficios(String permiteAdelantosEnBeneficios) {
        this.permiteAdelantosEnBeneficios = permiteAdelantosEnBeneficios;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getMaxCantTarjetas() {
        return maxCantTarjetas;
    }

    public void setMaxCantTarjetas(String maxCantTarjetas) {
        this.maxCantTarjetas = maxCantTarjetas;
    }

    public TarjetaPaquete getMaster() {
        return Master;
    }

    public void setMaster(TarjetaPaquete master) {
        Master = master;
    }
}
