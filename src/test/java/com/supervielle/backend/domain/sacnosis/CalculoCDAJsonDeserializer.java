package com.supervielle.backend.domain.sacnosis;

import com.google.gson.*;

import java.lang.reflect.Type;

public class CalculoCDAJsonDeserializer implements
        JsonDeserializer<CalculoCDA> {


    @Override
    public CalculoCDA deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context) throws JsonParseException {

        JsonElement jsonWithoutItemWhenEmpty = json;
        if (json.getAsJsonObject().get("Item").toString().equalsIgnoreCase("\"\"")) {
            jsonWithoutItemWhenEmpty = json.getAsJsonObject().remove("item");
        }
        if (jsonWithoutItemWhenEmpty == null) {
            return null;
        }
        return new Gson().fromJson(jsonWithoutItemWhenEmpty, CalculoCDA.class);
    }
}
