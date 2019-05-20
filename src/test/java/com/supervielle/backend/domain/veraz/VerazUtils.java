package com.supervielle.backend.domain.veraz;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.framework.tdm.UserProvider;

/**
 * @author globant | favio.veron
 */
public class VerazUtils {

    public static final String CONSULTA_VERAZ = "consultaVeraz";
    public static final String GROUP_1_NAME = "max_lim_gra";
    public static final String GROUP_2_NAME = "max_lim_grb";
    public static final String GROUP_3_NAME = "max_lim_grc";
    public static final String INCOME_PREDICTOR_NAME = "income_predictor";
    public static final String SCORE_VERAZ_NAME = "score_veraz";

    public static String getResponseFromVerazService(String prospect) {
        return ClientDao.getResponseFromOperation(CONSULTA_VERAZ, UserProvider.getCuil(prospect).trim());
    }

    public static JSONObject getMappedDataFromVerazResponse(JSONObject jsonObject) {
        return jsonObject.getJSONObject("NS1:Envelope").getJSONObject("NS1:Body")
                .getJSONObject("NS2:RespConsultaVeraz");
    }

    public static ResponseConsultaVerazDTO getDTOFromVerazResponse(String response) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(response);
        jsonObject = getMappedDataFromVerazResponse(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), ResponseConsultaVerazDTO.class);
    }

    public static boolean verazResponseHasValue0(String response) {
        ResponseConsultaVerazDTO responseConsultaVerazDTO = getDTOFromVerazResponse(response);
        Variables[] variables = responseConsultaVerazDTO.getData().getRow().getIntegranteConsultado().getVariables();
        for (Variables variablesElement : variables) {
            if ("0".equals(variablesElement.getValor()) && variableBelongsToBankGroups(variablesElement)) {
                return true;
            }
        }
        return false;
    }

    public static boolean verazResponseHasScoreVeraz0(String response) {
        ResponseConsultaVerazDTO responseConsultaVerazDTO = getDTOFromVerazResponse(response);
        Variables[] variables = responseConsultaVerazDTO.getData().getRow().getIntegranteConsultado().getVariables();
        for (Variables variablesElement : variables) {
            if (SCORE_VERAZ_NAME.equals(variablesElement.getNombre()) && "0".equals(variablesElement.getValor())) {
                return true;
            }
        }
        return false;
    }

    public static boolean variableBelongsToBankGroups(Variables variables) {
        return variables.getNombre().equals(GROUP_1_NAME) || variables.getNombre().equals(GROUP_2_NAME)
                || variables.getNombre().equals(GROUP_3_NAME);
    }

    public static int getScoreFromResponse(String response) {
        int scoreVeraz = 0;
        ResponseConsultaVerazDTO responseConsultaVerazDTO = getDTOFromVerazResponse(response);
        Variables[] variables = responseConsultaVerazDTO.getData().getRow().getIntegranteConsultado().getVariables();
        for (Variables variablesElement : variables) {
            if (SCORE_VERAZ_NAME.equals(variablesElement.getNombre())) {
                scoreVeraz = Integer.valueOf(variablesElement.getValor());
            }
        }
        return scoreVeraz;
    }

    public static String getIncomePredictorCategoryFromVerazResponse(String verazResponse) {
        ResponseConsultaVerazDTO responseConsultaVerazDTO = getDTOFromVerazResponse(verazResponse);
        Variables[] variables = responseConsultaVerazDTO.getData().getRow().getIntegranteConsultado().getVariables();
        for (Variables variablesElement : variables) {
            if (INCOME_PREDICTOR_NAME.equals(variablesElement.getNombre())) {
                return variablesElement.getValor();
            }
        }
        return "";
    }

    public static Double getMaxValueOfGruposBanco(String verazResponse) {
        ResponseConsultaVerazDTO responseConsultaVerazDTO = getDTOFromVerazResponse(verazResponse);
        Variables[] variables = responseConsultaVerazDTO.getData().getRow().getIntegranteConsultado().getVariables();
        double max = 0;
        for (Variables variablesElement : variables) {
            if (variableBelongsToBankGroups(variablesElement)
                    && max < Double.parseDouble(variablesElement.getValor())) {
                max = Double.parseDouble(variablesElement.getValor());
            }
        }
        return max;
    }
}
