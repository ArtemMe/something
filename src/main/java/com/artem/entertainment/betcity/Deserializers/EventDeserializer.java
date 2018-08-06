package com.artem.entertainment.betcity.Deserializers;

import com.artem.entertainment.betcity.models.Championship;
import com.artem.entertainment.betcity.models.Championships;
import com.artem.entertainment.betcity.models.Event;
import com.artem.entertainment.betcity.models.Events;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventDeserializer implements JsonDeserializer<Events> {
    @Override
    public Events deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = element.getAsJsonObject();
        List<Event> eventList = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            Event event = context.deserialize(entry.getValue(), Event.class);
            eventList.add(event);
        }
        Events events = new Events();
        events.setEvts(eventList);
        return events;
    }

}
