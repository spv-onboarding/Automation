package com.supervielle.backend.domain;

public enum EMPLOYMENT_RELATIONSHIP_TYPE {
    RELACION_DEPENDENCIA("relación de dependencia"),
    RELACION_JUBILADO_PENSIONADO("jubilado / pensionado"),
    RELACION_AUTONOMO("autonomo"),
    RELACION_MONOTRIBUTO("monotributo");

    private String name;

    EMPLOYMENT_RELATIONSHIP_TYPE(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
