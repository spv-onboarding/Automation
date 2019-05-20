package com.supervielle.backend.domain.aceptacion_crediticia;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.domain.aceptacion_crediticia.request.RequestAceptacionCrediticia;
import com.supervielle.framework.tdm.UserProvider;

public class AceptacionCrediticiaUtils {

    public static final String CONSULTA_ACEPTACION_CREDITICIA = "aceptacionCrediticia-v1.1";

    public static String getRequestFromCreditAcceptationService(String prospect) {
        return ClientDao.getRequestFromOperation(CONSULTA_ACEPTACION_CREDITICIA, UserProvider.getCuil(prospect).trim());
    }

    public static JSONObject getMappedDataFromCreditAcceptationRequest(JSONObject jsonObject) {
        return jsonObject.getJSONObject("s:Envelope").getJSONObject("s:Body").getJSONObject("ReqAceptacionCrediticia");
    }

    public static RequestAceptacionCrediticia getDTOFromCreditAcceptationRequest(String request) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(request);
        jsonObject = getMappedDataFromCreditAcceptationRequest(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), RequestAceptacionCrediticia.class);
    }

    public static String getResponseFromCreditAcceptationService(String prospect) {
        return ClientDao.getResponseFromOperation(CONSULTA_ACEPTACION_CREDITICIA,
                UserProvider.getCuil(prospect).trim());
    }

    public static JSONObject getMappedDataFromCreditAcceptationResponse(JSONObject jsonObject) {
        return jsonObject.getJSONObject("NS1:Envelope").getJSONObject("NS1:Body")
                .getJSONObject("NS2:RespAceptacionCrediticia");
    }

    public static ResponseAceptacionCrediticiaDTO getDTOFromCreditAcceptationResponse(String response) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(response);
        jsonObject = getMappedDataFromCreditAcceptationResponse(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), ResponseAceptacionCrediticiaDTO.class);
    }

    public static int getPoliticaValueFromRequest(String response) {
        RequestAceptacionCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(response);
        if (requestAceptacionCrediticia.getData().getPolitica() != null) {
            return requestAceptacionCrediticia.getData().getPolitica().getPoliticaId();
        }
        return 1;
    }

    public static int getIngresoFromRequest(String response) {
        RequestAceptacionCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(response);
        return Integer.parseInt(requestAceptacionCrediticia.getData().getPersonas().getPersona().getIngreso());
    }

    public static int getScoreValueFromRequest(String response) {
        RequestAceptacionCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(response);
        return Integer.parseInt(requestAceptacionCrediticia.getData().getPersonas().getPersona().getScore().getScore());
    }

    public static String getScoreTypeFromRequest(String response) {
        RequestAceptacionCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(response);
        return requestAceptacionCrediticia.getData().getPersonas().getPersona().getScore().getTipoScore();
    }

    public static EstadoGeneral getEstadoGeneralFromAceptCrediticiaResponse(String response) {
        ResponseAceptacionCrediticiaDTO responseAceptacionCrediticia = getDTOFromCreditAcceptationResponse(response);
        return responseAceptacionCrediticia.getData().getRow().getEstadoGeneral();
    }

}
