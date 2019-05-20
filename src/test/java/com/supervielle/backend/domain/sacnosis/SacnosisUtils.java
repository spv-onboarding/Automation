package com.supervielle.backend.domain.sacnosis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.supervielle.backend.SupervielleServicesUtils;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.framework.tdm.UserProvider;

/**
 * @author globant | favio.veron
 */
public class SacnosisUtils {

    public static final String CONSULTA_SACNOSIS = "consultaSACNOSIS";
    private static final String NSE_ITEM = "NSE";
    private static final Logger LOG = Logger.getLogger(SacnosisUtils.class.getName());

    public static String getResponseFromCommercialBackgroundService(String prospect) {
        return ClientDao.getResponseFromOperation(CONSULTA_SACNOSIS, UserProvider.getCuil(prospect).trim());
    }

    public static JSONObject getMappedDataFromCommercialBackgroundResponse(JSONObject jsonObject) {
        return jsonObject.getJSONObject("NS1:Envelope").getJSONObject("NS1:Body")
                .getJSONObject("NS2:RespConsultaSACNOSIS");
    }

    public static ResponseConsultaSACNOSISDTO getDTOFromCommercialBackgroundResponse(String response) {
        JSONObject jsonObject = SupervielleServicesUtils.parseResponseToJson(response);
        jsonObject = getMappedDataFromCommercialBackgroundResponse(jsonObject);
        Gson gson = new GsonBuilder().registerTypeAdapter(CalculoCDA.class, new CalculoCDAJsonDeserializer()).create();
        return gson.fromJson(jsonObject.toString(), ResponseConsultaSACNOSISDTO.class);
    }

    public static List<Dato> getDato(ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO) {
        return responseConsultaSACNOSISDTO.getData().getRow().getSACNOSIS().getRespuesta().getParteXML().getDato();
    }

    public static boolean SACNOSISResponseHasGivenValueInPrefijo(String SACNOSISresponse, String prefijo) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                SACNOSISresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);
        for (Dato dato : datos) {
            if (dato.getClave().getPrefijo().equals(prefijo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean SACNOSISResponseHasDeudas(String SACNOSISresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                SACNOSISresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);
        for (Dato dato : datos) {
            if (dato.getUltimoInforme() != null && dato.getUltimoInforme().getDeudas() != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean SACNOSISResponseHasNotGivenValueInItemAndClave(String SACNOSISresponse, final String norItem,
            final String clave) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                SACNOSISresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);
        Optional<Dato> datoCalculoCDA = datos.stream().filter(x -> x.getCalculoCDA() != null).findFirst();
        if (datoCalculoCDA.isPresent()) {
            CalculoCDA calculoCDA = datoCalculoCDA.get().getCalculoCDA();
            return calculoCDA.getItem().stream()
                    .noneMatch(x -> x.getNro().equals(norItem) && x.getClave().equals(clave));
        }
        return true;
    }

    public static boolean SACNOSISResponseHasGivenValueInSinActividad(String SACNOSISresponse, final String actividad) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                SACNOSISresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);
        Optional<Dato> withoutActivity = datos.stream().filter(x -> x.getSinActividad() != null).findFirst();
        if (withoutActivity.isPresent()) {
            return withoutActivity.get().getSinActividad().equalsIgnoreCase(actividad);
        }
        return true;
    }

    public static String getCategoryFromNSE(String SACNOSISresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                SACNOSISresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);
        for (Dato dato : datos) {
            if (dato.getCalculoCDA() != null) {
                List<Item> items = dato.getCalculoCDA().getItem();
                for (Item item : items) {
                    if (NSE_ITEM.equals(item.getDescrip())) {
                        return item.getValor();
                    }
                }
            }
        }
        return StringUtils.EMPTY;
    }

    public static String getProvinciaDomicilioFiscal(String SACNOSISresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                SACNOSISresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);
        Optional<Dato> province = datos.stream().filter(x -> x.getDomFiscal() != null).findFirst();
        if (province.isPresent()) {
            return province.orElse(null).getDomFiscal().getProv();
        }
        return StringUtils.EMPTY;

    }

    public static String getValueInAutMonotribFromSACNOSISResponse(String sacnosiSresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                sacnosiSresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);

        Optional<Dato> monotax = datos.stream().filter(x -> x.getAut_Monotrib() != null).findFirst();
        if (monotax.isPresent()) {
            return monotax.get().getAut_Monotrib();
        }
        return StringUtils.EMPTY;
    }

    public static String getValueInGeneroFromSACNOSISResponse(String sacnosiSresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                sacnosiSresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);

        Optional<Dato> gender = datos.stream().filter(x -> x.getSexo() != null).findFirst();
        if (gender.isPresent()) {
            return gender.get().getSexo();
        }
        return StringUtils.EMPTY;
    }

    public static Date getValueInFechaNacimientoFromSACNOSISResponse(String sacnosiSresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                sacnosiSresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);

        Optional<Dato> birthDateData = datos.stream().filter(x -> x.getFecNac() != null).findFirst();
        if (birthDateData.isPresent()) {
            Dato dato = birthDateData.get();
            SimpleDateFormat dateWithFormat = new SimpleDateFormat("yyyyMMdd");
            try {
                return dateWithFormat.parse(dato.getFecNac());
            } catch (ParseException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return null;
    }

    public static Dato getFirstDatoFromResponseSACNOSIS(String SACNOSISresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                SACNOSISresponse);
        return getDato(responseConsultaSACNOSISDTO).get(0);
    }

    public static String getValueInCUILFromSACNOSISResponse(String sacnosiSresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                sacnosiSresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);

        Optional<Dato> document = datos.stream().filter(x -> x.getDoc() != null).findFirst();
        if (document.isPresent()) {
            return document.get().getDoc();
        }
        return StringUtils.EMPTY;
    }

    public static String getFiscalDomicile(String SACNOSISresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                SACNOSISresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);

        Optional<Dato> province = datos.stream().filter(x -> x.getDomFiscal() != null).findFirst();
        if (province.isPresent()) {
            return province.orElse(null).getDomFiscal().getDom();
        }
        return StringUtils.EMPTY;
    }

    public static String getFiscalZipCode(String SACNOSISresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                SACNOSISresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);

        Optional<Dato> province = datos.stream().filter(x -> x.getDomFiscal() != null).findFirst();
        if (province.isPresent()) {
            return province.orElse(null).getDomFiscal().getCP();
        }
        return StringUtils.EMPTY;
    }

    public static String getValueInJubiladoFromSACNOSISResponse(String sacnosiSresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                sacnosiSresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);

        Optional<Dato> jubilado = datos.stream().filter(x -> x.getJubilado() != null).findFirst();
        if (jubilado.isPresent()) {
            return jubilado.get().getJubilado();
        }
        return StringUtils.EMPTY;
    }

    public static String getValueInRelacionDependenciaFromSACNOSISResponse(String sacnosiSresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                sacnosiSresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);

        Optional<Dato> relacionDependencia = datos.stream().filter(x -> x.getRelDependencia() != null).findFirst();
        if (relacionDependencia.isPresent()) {
            return relacionDependencia.get().getRelDependencia();
        }
        return StringUtils.EMPTY;
    }

    public static Integer getValueInAgeFromSACNOSISResponse(String sacnosiSresponse) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                sacnosiSresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);

        Optional<Dato> edad = datos.stream().filter(x -> x.getEdad() != null).findFirst();
        if (edad.isPresent()) {
            return Integer.parseInt(edad.get().getEdad());
        }
        return 0;
    }

    public static String SACNOSISResponseGetValueInItem(String SACNOSISresponse, final String nroItem) {
        ResponseConsultaSACNOSISDTO responseConsultaSACNOSISDTO = getDTOFromCommercialBackgroundResponse(
                SACNOSISresponse);
        List<Dato> datos = getDato(responseConsultaSACNOSISDTO);
        Optional<Dato> datoCalculoCDA = datos.stream().filter(x -> x.getCalculoCDA() != null).findFirst();
        if (datoCalculoCDA.isPresent()) {
            CalculoCDA calculoCDA = datoCalculoCDA.get().getCalculoCDA();
            Optional<Item> item = calculoCDA.getItem().stream().filter(x -> x.getNro().equals(nroItem)).findFirst();
            if (item.isPresent()){
               return item.get().getValor();
            }
        }
        return null;
    }

}
