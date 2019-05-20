package com.supervielle.backend.domain.sacnosis;

/**
 * @author globant | favio.veron
 */

public class Clave {
    private String Estado;

    private String EncPor;

    private String Doc;

    private String Identificador;

    private String Prefijo;

    private String RZ;

    private String FechaNov;

    private String SubPrefijo;

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getEncPor() {
        return EncPor;
    }

    public void setEncPor(String EncPor) {
        this.EncPor = EncPor;
    }

    public String getDoc() {
        return Doc;
    }

    public void setDoc(String Doc) {
        this.Doc = Doc;
    }

    public String getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(String Identificador) {
        this.Identificador = Identificador;
    }

    public String getPrefijo() {
        return Prefijo;
    }

    public void setPrefijo(String Prefijo) {
        this.Prefijo = Prefijo;
    }

    public String getRZ() {
        return RZ;
    }

    public void setRZ(String RZ) {
        this.RZ = RZ;
    }

    public String getFechaNov() {
        return FechaNov;
    }

    public void setFechaNov(String FechaNov) {
        this.FechaNov = FechaNov;
    }

    public String getSubPrefijo() {
        return SubPrefijo;
    }

    public void setSubPrefijo(String SubPrefijo) {
        this.SubPrefijo = SubPrefijo;
    }

    @Override
    public String toString() {
        return "ClassPojo [Estado = " + Estado + ", EncPor = " + EncPor + ", Doc = " + Doc + ", Identificador = "
                + Identificador + ", Prefijo = " + Prefijo + ", RZ = " + RZ + ", FechaNov = " + FechaNov
                + ", SubPrefijo = " + SubPrefijo + "]";
    }
}
