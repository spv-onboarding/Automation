package com.supervielle.backend.domain.cuestionario_veraz;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.domain.cuestionario_veraz.response.ResponseCuestionarioVeraz;
import com.supervielle.framework.tdm.UserProvider;

public class ConsultaCuestionarioVerazUtils {

    public static final String CONSULTA_VALIDAR_CUESTIONARIO_VERAZ = "validarCuestionarioVeraz-v1";

    public static String getResponseFromValidarCuestionarioVeraz(String prospect) {
        return ClientDao.getResponseFromOperation(CONSULTA_VALIDAR_CUESTIONARIO_VERAZ, UserProvider.getCuil(prospect).trim());
    }

    public static JSONObject getMappedDataFromValidarCuestionarioVerazResponse(JSONObject jsonObject) {
        return jsonObject.getJSONObject("NS1:Envelope").getJSONObject("NS1:Body")
                .getJSONObject("NS2:RespValidarCuestionarioVeraz");
    }

    public static ResponseCuestionarioVeraz getDTOFromValidarCuestionarioVerazResponse(String response) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(response);
        jsonObject = getMappedDataFromValidarCuestionarioVerazResponse(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), ResponseCuestionarioVeraz.class);
    }

    public static Integer getStateFromResponse(String response) {
        ResponseCuestionarioVeraz responseCuestionarioVeraz = getDTOFromValidarCuestionarioVerazResponse(response);
        return responseCuestionarioVeraz.getData().getRow().getRespuesta().getIntegrante().getEstado();
    }

    public static String getResultFromResponse(String response) {
        ResponseCuestionarioVeraz responseCuestionarioVeraz = getDTOFromValidarCuestionarioVerazResponse(response);
        return responseCuestionarioVeraz.getData().getRow().getResultado();
    }

}
