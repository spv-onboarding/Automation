package com.supervielle.backend.domain.alta_persona;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.domain.alta_persona.request.RequestAltaPersona;
import com.supervielle.framework.tdm.UserProvider;

public class AltaPersonaUtils {

    public static final String CONSULTA_ALTA_PERSONA = "altaPersona";
    public static final String ESTADO_OK = "OK";
    public static final String APLICACION_EXTERNA = "OB00000";
    public static final String DNI_CONSTANT_VALUE = "4";
    public static final String CODIGO_PERSONA_FISICA = "F";
    public static final String CANAL_DISTRIBUCION = "14";
    public static final String TIPO_ALTA = "1";
    public static final String CATEGORIA = "2";
    public static final String COD_TITULARIDAD = "1";
    public static final String TITULAR_REPRESENTATIVO = "T";
    public static final String INSTITUCION_FINANCIERA = "N";
    public static final String COD_EJECUTIVO = "999";
    public static final String COD_SECTOR = "3";
    public static final String COD_ACTIVIDAD_ECONOMICA = "82";
    public static final String PERSONA_EXPUESTA = "N";

    public static String getRequestFromPersonRegistrationService(String prospect) {
        return ClientDao.getRequestFromOperation(CONSULTA_ALTA_PERSONA, UserProvider.getCuil(prospect).trim());
    }

    public static String getResponseFromPersonRegistrationService(String prospect) {
        return ClientDao.getResponseFromOperation(CONSULTA_ALTA_PERSONA, UserProvider.getCuil(prospect).trim());
    }
    
    public static JSONObject getMappedDataFromPersonRegistrationRequest(JSONObject jsonObject) {
        return jsonObject.getJSONObject("s:Envelope").getJSONObject("s:Body").getJSONObject("ReqAltaPersona");
    }

    public static JSONObject getMappedDataFromPersonRegistrationResponse(JSONObject jsonObject) {
        return jsonObject.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("alt:RespAltaPersona");
    }
    
    public static RequestAltaPersona getDTOFromPersonRegistrationRequest(String request) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(request);
        jsonObject = getMappedDataFromPersonRegistrationRequest(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), RequestAltaPersona.class);
    }
    
    public static ResponseAltaPersona getDTOFromPersonRegistrationResponse(String response) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(response);
        jsonObject = getMappedDataFromPersonRegistrationResponse(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), ResponseAltaPersona.class);
    }
    
    public enum EducationaLevel {
        Primario("1"), Secundario("2"), Terciario("3"), Universitario("4");

        private String nivel;

        EducationaLevel(String nivel) {
            this.nivel = nivel;
        }

        public String getNivel() {
            return nivel;
        }
    }

}
