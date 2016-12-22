package com.ipalma.rapreddit.services;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ipalma.rapreddit.helpers.RedditDeserializer;
import com.ipalma.rapreddit.models.Reddit;
import com.ipalma.rapreddit.network.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * Created by ivan on 22/12/2016.
 */

public class RedditService {
    private static final String URL_REDDITS = "https://www.reddit.com/reddits.json";

    public static void getReddits(String requestTag, final RedditCallback listener) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL_REDDITS, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listener.onSuccess(parseRedditsJson(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.toString());
                    }
        });

        jsonObjectRequest.setTag(requestTag);
        VolleySingleton.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private static List<Reddit> parseRedditsJson(JSONObject response) throws JSONException {
        Type listType = new TypeToken<List<Reddit>>() {}.getType();
        String reddits =  response.getJSONObject("data").getString("children");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reddit.class, new RedditDeserializer())
                .create();

        return gson.fromJson(reddits, listType);
    }

    public interface RedditCallback {
        void onError(String message);
        void onSuccess(List<Reddit> reddits);
    }
}
