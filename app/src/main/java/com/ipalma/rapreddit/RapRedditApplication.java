package com.ipalma.rapreddit;

import android.app.Application;
import android.content.Context;

/**
 * Application class
 * Created by ivan on 12/21/16.
 */
public class RapRedditApplication extends Application {
    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
    }
}
