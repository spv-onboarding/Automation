package com.supervielle.backend;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SupervielleServicesUtils {

    public boolean validateStatus(Response response, String status) {
        boolean result = false;
        response.then().assertThat().statusCode(Integer.parseInt(status)).extract().response();
        System.out.println("Response body: " + response.body().asString());
        result = true;
        return result;
    }

    public Response requestPost(Map<String, Object> map, String baseHost, String resorces) {
        Gson gson = new Gson();
        // Convert the ordered map into an ordered string.
        String json = gson.toJson(map, LinkedHashMap.class);
        System.out.println("Parametro gson: " + json);
        // Deberia poder pasar requestParameter al body, pero lo pasa sin orden
        return RestAssured.given().header("Content-Type", ContentType.JSON).body(json).when().post(baseHost + resorces);
    }

    public Map<String, Object> getClientInfo(long cuil, String mail, String offeringType) {
        Map<String, Object> loginAgente = new LinkedHashMap<String, Object>();
        loginAgente.put("identificador", "string");

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        map.put("dni", 0);
        map.put("nombre", "Favio");
        map.put("apellido", "Robles");
        map.put("cuil", cuil);
        map.put("sexo", "M");
        map.put("email", mail);
        map.put("id", null);
        map.put("codigoArea", "03704");
        map.put("telefono", "15680627");
        map.put("tipoOferta", offeringType);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        return map;
    }

    public static JSONObject parseResponseToJson(String response) {
        return XML.toJSONObject(response);
    }

}
