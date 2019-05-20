package com.supervielle.backend.domain.alta_producto.alta_voucher;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.domain.alta_producto.alta_voucher.request.RequestAltaInstantaneaMaster;
import com.supervielle.framework.tdm.UserProvider;

public class AltaVoucherUtils {

    public static final String CONSULTA_ALTA_VOUCHER = "altaInstantaneaMaster-v1";
    public static final String DESCRIPCION_EXITOSA = "Finalizada con éxito";

    public static String getRequestFromVoucherRegistrationService(String prospect) {
        return ClientDao.getRequestFromOperation(CONSULTA_ALTA_VOUCHER, UserProvider.getCuil(prospect).trim());
    }

    public static JSONObject getMappedDataFromVoucherRegistrationRequest(JSONObject jsonObject) {
        return jsonObject.getJSONObject("s:Envelope").getJSONObject("s:Body").getJSONObject("ReqAltaInstantaneaMaster");
    }

    public static String getResponseFromVoucherRegistrationService(String prospect) {
        return ClientDao.getResponseFromOperation(CONSULTA_ALTA_VOUCHER, UserProvider.getCuil(prospect).trim());
    }

    public static JSONObject getMappedDataFromVoucherRegistrationResponse(JSONObject jsonObject) {
        return jsonObject.getJSONObject("NS1:Envelope").getJSONObject("NS1:Body")
                .getJSONObject("NS1:RespAltaInstantaneaMaster");
    }

    public static RequestAltaInstantaneaMaster getDTOFromVoucherRegistrationRequest(String request) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(request);
        jsonObject = getMappedDataFromVoucherRegistrationRequest(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), RequestAltaInstantaneaMaster.class);
    }

    public static ResponseAltaInstantaneaMaster getDTOFromVoucherRegistrationResponse(String response) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(response);
        jsonObject = getMappedDataFromVoucherRegistrationResponse(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), ResponseAltaInstantaneaMaster.class);
    }
}
