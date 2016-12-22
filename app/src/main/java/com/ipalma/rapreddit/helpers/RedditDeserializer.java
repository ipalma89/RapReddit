package com.ipalma.rapreddit.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.ipalma.rapreddit.models.Reddit;

import java.lang.reflect.Type;

/**
 * Custom Gson deserializer for reddits
 * Created by ivan on 22/12/2016.
 */

public class RedditDeserializer implements JsonDeserializer<Reddit> {

    @Override
    public Reddit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement content = json.getAsJsonObject().get("data");
        return new Gson().fromJson(content, Reddit.class);
    }
}
