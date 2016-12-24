package com.ipalma.rapreddit.services;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.helpers.DataUtils;
import com.ipalma.rapreddit.helpers.RedditDeserializer;
import com.ipalma.rapreddit.helpers.SharedPreferencesHelper;
import com.ipalma.rapreddit.models.Reddit;
import com.ipalma.rapreddit.network.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * Created by ivan on 22/12/2016.
 */

public class RedditService {
    private static final String URL_REDDITS = "https://www.reddit.com/reddits.json";
    private static final String REDDITS_CACHE_KEY = "reddits_cache";

    public static void getReddits(String requestTag, final RedditCallback listener) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL_REDDITS, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // cache last request for offline mode
                            SharedPreferencesHelper.savePreference(REDDITS_CACHE_KEY, response.toString());

                            listener.onSuccess(parseRedditsJson(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String message;

                        if (error instanceof NoConnectionError) {
                            // when no connection we try to get from cache
                            ArrayList<Reddit> reddits = getRedditsFromCache();

                            if (reddits != null) {
                                // we have data cached
                                listener.onSuccess(reddits);
                                return;
                            } else {
                                // no connection and no data cached
                                message = DataUtils.getString(R.string.no_network_error);
                            }
                        } else {
                            message = DataUtils.getString(R.string.generic_error);
                        }

                        listener.onError(message);
                    }
        });

        jsonObjectRequest.setTag(requestTag);
        VolleySingleton.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private static ArrayList<Reddit> parseRedditsJson(JSONObject response) throws JSONException {
        Type listType = new TypeToken<ArrayList<Reddit>>() {}.getType();
        String reddits =  response.getJSONObject("data").getString("children");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reddit.class, new RedditDeserializer())
                .create();

        return gson.fromJson(reddits, listType);
    }

    private static ArrayList<Reddit> getRedditsFromCache() {
        String reddits = SharedPreferencesHelper.getStringPreference(REDDITS_CACHE_KEY);

        try {
            return parseRedditsJson(new JSONObject(reddits));
        } catch (Exception e) {
            return null;
        }
    }

    public interface RedditCallback {
        void onError(String message);
        void onSuccess(ArrayList<Reddit> reddits);
    }
}
