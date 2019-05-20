package com.supervielle.backend.domain.consulta_scoring_supervielle;

import com.google.gson.Gson;
import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.domain.consulta_scoring_supervielle.response.ResponseConsultaScoringSupervielle;
import com.supervielle.framework.tdm.UserProvider;
import org.json.JSONObject;


public class ConsultaScoringSupervielleUtils {

    public static final String CONSULTA_SCORING_SUPERVIELLE = "ConsultaScoringSupervielle";

    public static String getResponseFromCreditAcceptationService(String prospect) {
        return ClientDao.getResponseFromOperation(CONSULTA_SCORING_SUPERVIELLE, UserProvider.getCuil(prospect).trim());
    }

    public static JSONObject getMappedDataFromSupervielleScoringConsultResponse(JSONObject jsonObject) {
        return jsonObject.getJSONObject("NS1:Envelope").getJSONObject("NS1:Body")
                .getJSONObject("NS2:RespConsultaScoringSupervielle");
    }

    public static ResponseConsultaScoringSupervielle getDTOFromCreditAcceptationResponse(String response) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(response);
        jsonObject = getMappedDataFromSupervielleScoringConsultResponse(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), ResponseConsultaScoringSupervielle.class);
    }

    public static double getScoreFromResponse(String response){
        ResponseConsultaScoringSupervielle responseConsultaScoringSupervielle = getDTOFromCreditAcceptationResponse(response);
        return Double.parseDouble(responseConsultaScoringSupervielle.getData().getScore());
    }

}
