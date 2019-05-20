package com.supervielle.backend.domain.oferta_crediticia;

import com.google.gson.Gson;
import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.backend.domain.oferta_crediticia.request.RequestOfertaCrediticia;
import com.supervielle.framework.tdm.UserProvider;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Optional;

public class OfertaCrediticiaUtils {

    public static final String CONSULTA_OFERTA_CREDITICIA = "consultaOfertaCrediticia";

    public static String getRequestFromCreditAcceptationService(String prospect) {
        return ClientDao.getRequestFromOperation(CONSULTA_OFERTA_CREDITICIA, UserProvider.getCuil(prospect).trim());
    }

    public static String getResponseFromCreditAcceptationService(String prospect) {
        return ClientDao.getResponseFromOperation(CONSULTA_OFERTA_CREDITICIA, UserProvider.getCuil(prospect).trim());
    }

    public static JSONObject getMappedDataFromCreditAcceptationRequest(JSONObject jsonObject) {
        return jsonObject.getJSONObject("s:Envelope").getJSONObject("s:Body")
                .getJSONObject("ReqConsultaOfertaCrediticia");
    }

    public static JSONObject getMappedDataFromCreditAcceptationResponse(JSONObject jsonObject) {
        return jsonObject.getJSONObject("NS1:Envelope").getJSONObject("NS1:Body")
                .getJSONObject("NS2:RespConsultaOfertaCrediticia");
    }

    public static RequestOfertaCrediticia getDTOFromCreditAcceptationRequest(String request) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(request);
        jsonObject = getMappedDataFromCreditAcceptationRequest(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), RequestOfertaCrediticia.class);
    }

    public static ResponseOfertaCrediticia getDTOFromCreditAcceptationResponse(String response) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(response);
        jsonObject = getMappedDataFromCreditAcceptationResponse(jsonObject);
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), ResponseOfertaCrediticia.class);
    }

    public static double getScoreValueFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return Double.parseDouble(requestAceptacionCrediticia.getData().getOferta().getScore());
    }

    public static int getPoliticaIDFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return Integer.parseInt(requestAceptacionCrediticia.getData().getOferta().getPoliticaID());
    }

    public static String getActividadIdFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getActividadID();
    }

    public static String getCompromisoOtrosBancosFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getCompromisoOtrosBancos();
    }

    public static String getRentaTitularFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getRentaTitular();
    }

    public static String getEdadFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getEdad();
    }

    public static String getProvinciaIDFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getProvinciaID();
    }

    public static String getCanalVentaIDFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getCanalVentaID();
    }

    public static String getSegmentoIDFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getSegmentoID();
    }

    public static String getSolicitadoDescubiertoFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getSolicitadoDescubierto();
    }

    public static String getSolicitadoVisaLimiteFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getSolicitadoVisaLimite();
    }

    public static String getSolicitadoMasterLimiteFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getSolicitadoMasterLimite();
    }

    public static String getSolicitadoPrestamoCapitalFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getSolicitadoPrestamoCapital();
    }

    public static String getSolicitadoPrestamoPlazoFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getSolicitadoPrestamoPlazo();
    }

    public static String getSolicitadoPrestamoTasaFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getSolicitadoPrestamoTasa();
    }

    public static String getEmpresaIdFromRequest(String response) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(response);
        return requestAceptacionCrediticia.getData().getOferta().getEmpresaID();
    }

    public static String getAcuerdoActualFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getAcuerdoActual();
    }

    public static String getPaqueteActualFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getPaqueteActual();
    }

    public static String getSucursalIDFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getSucursalID();
    }

    public static String getRentaConyugeFromRequest(String request) {
        RequestOfertaCrediticia requestAceptacionCrediticia = getDTOFromCreditAcceptationRequest(request);
        return requestAceptacionCrediticia.getData().getOferta().getRentaConyuge();
    }

    public static String getLimiteTarjetaMasterFromResponse(String response) {
        ResponseOfertaCrediticia responseOfertaCrediticia = getDTOFromCreditAcceptationResponse(response);
        return responseOfertaCrediticia.getData().getRow().getResultado().getOfertaInicial().getMaster().getLimite();
    }

    public static String getLimiteTarjetaVisaFromResponse(String response) {
        ResponseOfertaCrediticia responseOfertaCrediticia = getDTOFromCreditAcceptationResponse(response);
        return responseOfertaCrediticia.getData().getRow().getResultado().getOfertaInicial().getVisa().getLimite();
    }

    public static String getPaqueteIdFromResponse(String response) {
        ResponseOfertaCrediticia responseOfertaCrediticia = getDTOFromCreditAcceptationResponse(response);
        Paquete[] paquetes = responseOfertaCrediticia.getData().getRow().getResultado().getPaquetes().getPaquete();
        Optional<Paquete> paquete = Arrays.stream(paquetes).filter(x -> x.getId() != null).findFirst();
        if (paquete.isPresent()) {
            return paquete.get().getId();
        }
        return StringUtils.EMPTY;
    }

    public static  String getPaqueteUtilizadoFromResponse(String response){
        ResponseOfertaCrediticia responseOfertaCrediticia = getDTOFromCreditAcceptationResponse(response);
        return responseOfertaCrediticia.getData().getRow().getResultado().getPaqueteUtilizado();
    }

}
