package com.bdb.api.json;

import com.bdb.api.model.Status;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class StatusDeserializer implements JsonDeserializer<Status> {

    @Override
    public Status deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Status.findByName(json.getAsString());
    }
}
