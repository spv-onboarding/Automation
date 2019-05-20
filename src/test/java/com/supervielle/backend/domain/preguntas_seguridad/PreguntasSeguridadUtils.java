package com.supervielle.backend.domain.preguntas_seguridad;

import com.google.gson.Gson;
import com.supervielle.backend.data.ClientDao;
import com.supervielle.framework.tdm.UserProvider;

public class PreguntasSeguridadUtils {
    
    public static final String CONSULTA_PREGUNTAS_SEGURIDAD = "/api/PreguntasSeguridad";

    public static ResponsePreguntasSeguridad getResponseFromSecurityQuestionsService(String prospect) {
        String response = ClientDao.getResponseFromService(CONSULTA_PREGUNTAS_SEGURIDAD, UserProvider.getCuil(prospect).trim());
        Gson gson = new Gson();
        return gson.fromJson(response, ResponsePreguntasSeguridad.class);
    }
}
