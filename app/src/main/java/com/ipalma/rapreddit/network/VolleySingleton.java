package com.ipalma.rapreddit.network;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.ipalma.rapreddit.RRApplication;

/**
 * Singleton class to maintain a single Volley Request Queue across the app for all the
 * network operations.
 * Created by ivan on 12/21/16.
 */

public class VolleySingleton {
    private static final int REQUEST_TIMEOUT = 5000;
    private static final int MAX_RETRIES = 1;
    private static final float BACKOFF_MULT = 2f;
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private RetryPolicy defaultRetryPolicy;

    private VolleySingleton() {
        // init request queue
        Cache cache = new DiskBasedCache(RRApplication.getAppContext().getCacheDir(), 10 * 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        // create retry policy
        defaultRetryPolicy = new DefaultRetryPolicy(REQUEST_TIMEOUT, MAX_RETRIES, BACKOFF_MULT);
    }

    public static synchronized VolleySingleton getInstance() {
        if (instance == null) {
            instance = new VolleySingleton();
        }
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setRetryPolicy(defaultRetryPolicy);
        requestQueue.add(req);
    }

    public void cancelRequestsFor(Object tag) {
        requestQueue.cancelAll(tag);
    }
}
