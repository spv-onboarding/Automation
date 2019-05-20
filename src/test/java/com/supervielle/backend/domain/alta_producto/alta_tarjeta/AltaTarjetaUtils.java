package com.supervielle.backend.domain.alta_producto.alta_tarjeta;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.framework.tdm.UserProvider;

public class AltaTarjetaUtils {

    public static final String CONSULTA_ALTA_TARJETA = "altaTitular";
    public static final String DESCRIPCION_EXITOSA = "Finalizada con éxito";
    public static final String ESTADO_OK = "OK";

    public static String getRequestFromTarjetaRegistrationService(String prospect) {
        return ClientDao.getRequestFromOperation(CONSULTA_ALTA_TARJETA, UserProvider.getCuil(prospect).trim());
    }
    
    public static String getResponseFromTarjetaRegistrationService(String prospect) {
        return ClientDao.getResponseFromOperation(CONSULTA_ALTA_TARJETA, UserProvider.getCuil(prospect).trim());
    }
    
    public static JSONObject getMappedDataFromTarjetaRegistrationResponse(JSONObject jsonObject) {
        return jsonObject.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("alt:RespAltaTitular");
    }
    
    public static ResponseAltaTitular getDTOFromTarjetaRegistrationResponse(String response) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(response);
        jsonObject = getMappedDataFromTarjetaRegistrationResponse(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), ResponseAltaTitular.class);
    }

}
