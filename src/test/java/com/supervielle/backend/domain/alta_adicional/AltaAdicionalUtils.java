package com.supervielle.backend.domain.alta_adicional;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.domain.alta_adicional.request.RequestAltaAdicional;
import com.supervielle.framework.tdm.UserProvider;

public class AltaAdicionalUtils {

    public static final String CONSULTA_ALTA_ADICIONAL = "altaAdicional";

    public static String getResponseFromRegistrationOfAditionalService(String prospect) {
        return ClientDao.getResponseFromOperation(CONSULTA_ALTA_ADICIONAL, UserProvider.getCuil(prospect).trim());
    }

    public static String getRequestFromRegistrationOfAditionalService(String prospect) {
        return ClientDao.getRequestFromOperation(CONSULTA_ALTA_ADICIONAL, UserProvider.getCuil(prospect).trim());
    }

    public static JSONObject getMappedDataFromRegistrationOfAditionalRequest(JSONObject jsonObject) {
        return jsonObject.getJSONObject("s:Envelope").getJSONObject("s:Body").getJSONObject("ReqAltaAdicional");
    }

    public static JSONObject getMappedDataFromRegistrationOfAditionalResponse(JSONObject jsonObject) {
        return jsonObject.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
                .getJSONObject("alt:RespAltaAdicional");
    }

    public static RequestAltaAdicional getDTOFromRegistrationOfAditionalRequest(String request) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(request);
        jsonObject = getMappedDataFromRegistrationOfAditionalRequest(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), RequestAltaAdicional.class);
    }

    public static ResponseAltaAdicional getDTOFromRegistrationOfAditionalResponse(String response) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(response);
        jsonObject = getMappedDataFromRegistrationOfAditionalResponse(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), ResponseAltaAdicional.class);
    }

}
