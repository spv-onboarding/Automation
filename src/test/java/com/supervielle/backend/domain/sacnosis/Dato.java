package com.supervielle.backend.domain.sacnosis;

/**
 * @author globant | favio.veron
 */

public class Dato {
    private String Doc;

    private Clave Clave;

    private String Bancarizado;

    private String RZ;

    private UltimoInforme UltimoInforme;

    private CalculoCDA CalculoCDA;

    private String IncluyeBM;

    private DomFiscal DomFiscal;

    private String SinActividad;
    
    private String Nombre;
    
    private String Edad;
    
    private String Apellido;

    private String Aut_Monotrib;

    private String FecNac;

    private String Sexo;

    private String Jubilado;

    private String RelDependencia;


    public String getDoc() {
        return Doc;
    }

    public void setDoc(String Doc) {
        this.Doc = Doc;
    }

    public Clave getClave() {
        return Clave;
    }

    public void setClave(Clave Clave) {
        this.Clave = Clave;
    }

    public String getBancarizado() {
        return Bancarizado;
    }

    public void setBancarizado(String Bancarizado) {
        this.Bancarizado = Bancarizado;
    }

    public String getRZ() {
        return RZ;
    }

    public void setRZ(String RZ) {
        this.RZ = RZ;
    }

    public UltimoInforme getUltimoInforme() {
        return UltimoInforme;
    }

    public void setUltimoInforme(UltimoInforme UltimoInforme) {
        this.UltimoInforme = UltimoInforme;
    }

    public String getIncluyeBM() {
        return IncluyeBM;
    }

    public void setIncluyeBM(String IncluyeBM) {
        this.IncluyeBM = IncluyeBM;
    }

    public com.supervielle.backend.domain.sacnosis.CalculoCDA getCalculoCDA() {
        return CalculoCDA;
    }

    public void setCalculoCDA(com.supervielle.backend.domain.sacnosis.CalculoCDA calculoCDA) {
        CalculoCDA = calculoCDA;
    }

    public com.supervielle.backend.domain.sacnosis.DomFiscal getDomFiscal() {
        return DomFiscal;
    }

    public void setDomFiscal(com.supervielle.backend.domain.sacnosis.DomFiscal domFiscal) {
        DomFiscal = domFiscal;
    }

    public String getSinActividad() {
        return SinActividad;
    }

    public void setSinActividad(String sinActividad) {
        SinActividad = sinActividad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    
    public String getAut_Monotrib() {
        return Aut_Monotrib;
    }

    public void setAut_Monotrib(String aut_Monotrib) {
        Aut_Monotrib = aut_Monotrib;
    }

    public String getFecNac() {
        return FecNac;
    }

    public void setFecNac(String fecNac) {
        FecNac = fecNac;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getJubilado() {
        return Jubilado;
    }

    public void setJubilado(String jubilado) {
        Jubilado = jubilado;
    }

    public String getRelDependencia() {
        return RelDependencia;
    }

    public void setRelDependencia(String relDependencia) {
        RelDependencia = relDependencia;
    }
}
