package com.artem.entertainment.betcity.Deserializers;

import com.artem.entertainment.betcity.models.Championship;
import com.artem.entertainment.betcity.models.Championships;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChmpDeserializer implements JsonDeserializer<Championships>{
        @Override
        public Championships deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = element.getAsJsonObject();
            List<Championship> chmp = new ArrayList<>();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                Championship city = context.deserialize(entry.getValue(), Championship.class);
                chmp.add(city);
            }
            Championships championships = new Championships();
            championships.setChmps(chmp);
            return championships;
        }
}
