package com.supervielle.backend.domain;

public class RegistrationServicesConstants {

    public static final String BAN_TOTAL_ACCOUNT_VOUCHER ="0";
    public static final String ADITIONAL_SERVICE_PERCENTAGE ="100";
    public static final String ADITIONAL_SERVICE_COD_NACIONALIDAD = "80";
    public static final String ADITIONAL_SERVICE_DATE_FORMAT = "yyyy-MM-dd";
    
    public enum PRODUCT_TYPE {

        BATCH("batch"),
        VOUCHER("voucher");

        private String productType;

        PRODUCT_TYPE(String productType) {
            this.productType = productType;
        }

        public String getProductType() {
            return productType;
        }
    }
    
    public enum OPERATION_CODE {

        ALTA_ADICIONAL("2");

        private String operationCode;

        OPERATION_CODE(String operationCode) {
            this.operationCode = operationCode;
        }

        public String getOperationCode() {
            return operationCode;
        }
    }

    public enum BRAND_CODE {

        MASTER("1", "Master"), VISA("2", "Visa");

        private String brandCode;
        private String brand;

        BRAND_CODE(String brandCode, String brand) {
            this.brandCode = brandCode;
            this.brand = brand;
        }

        public String getBrandCode() {
            return brandCode;
        }

        public String getBrand() {
            return brand;
        }

        public static String getBrandCodeByDescription(String description) {
            BRAND_CODE[] brands = BRAND_CODE.values();
            for (BRAND_CODE brand : brands) {
                if (brand.getBrand().equalsIgnoreCase(description)) {
                    return brand.getBrandCode();
                }
            }
            return "";
        }
    }

    public enum ENTITY_NUMBER {

        MASTER("167","Master"), VISA("027","Visa");

        private String entityNumber;
        private String brand;
        
        ENTITY_NUMBER(String entityNumber,String brand) {
            this.entityNumber = entityNumber;
            this.brand = brand;
        }

        public String getEntityNumber() {
            return entityNumber;
        }
        
        public String getBrand() {
            return brand;
        }
        
        public static String getEntityNumberDescription(String description) {
            ENTITY_NUMBER[] brands = ENTITY_NUMBER.values();
            for (ENTITY_NUMBER brand : brands) {
                if (brand.getBrand().equalsIgnoreCase(description)) {
                    return brand.getEntityNumber();
                }
            }
            return "";
        }
    }

    public enum REGISTRATION_TYPE {

        BATCH("1"), VOUCHER("2");

        private String type;

        REGISTRATION_TYPE(String type) {
            this.type = type;
        }

        public String getRegistrationType() {
            return type;
        }
    }

    public enum DOCUMENT_TYPE {

        CDI("3"), DNI("4"), LE("5"), LC("6");

        private String type;

        DOCUMENT_TYPE(String type) {
            this.type = type;
        }

        public String getDocType() {
            return type;
        }
        
    }

    public enum TRIBU_DOC_TYPE {

        CUIT("1"), CUIL("2");

        private String type;

        TRIBU_DOC_TYPE(String type) {
            this.type = type;
        }

        public String getTribuDocType() {
            return type;
        }
    }

    public enum CIVIL_STATUS_CODE {

        SOLTERO("1", "Soltero"), CASADO("2", "Casado"), SEPARADO("3", "Separado"), VIUDO("4", "Viudo"), DIVORCIADO("5",
                "Divorciado"), CONCUBINO("6", "Concubino"), UNION_DE_HECHO("7", "Union de Hecho"), UNION_DE_COV_INSC(
                        "7", "Union de Conv. Insc."), NO_INFORMADO("9", "No informado Uso APV");

        private String civilStatusCode;
        private String civilStatusDescription;

        CIVIL_STATUS_CODE(String code, String description) {
            this.civilStatusCode = code;
            this.civilStatusDescription = description;
        }

        public String getStatusCode() {
            return civilStatusCode;
        }

        public String getStatusDescription() {
            return civilStatusDescription;
        }
    }

    public enum EMPLOYMENT_RELATIONSHIP_CODE {

        RELACION_DEPENDENCIA("6"), MONOTRIBUTISTA("8"), JUBILADO("13"), MENOR_DE_EDAD("16"), SIN_OCUPACION_VIGENTE(
                "18");

        private String code;

        EMPLOYMENT_RELATIONSHIP_CODE(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

    }
}
