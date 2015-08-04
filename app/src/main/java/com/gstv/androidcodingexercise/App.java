package com.gstv.androidcodingexercise;

import android.app.Application;

import com.gstv.androidcodingexercise.util.DiskLruMediaCache;

/**
 * AndroidCodingExercise
 * Created by Michael Girard on 3/25/15.
 * Copyright 2015 GasStationTV inc. All rights reserved.
 */
public class App extends Application {
    private static final String TAG = Constants.BASE_TAG + App.class.getSimpleName();

    public static DiskLruMediaCache mediaCache;

    @Override public void onCreate() {
        super.onCreate();
        mediaCache = new DiskLruMediaCache(getApplicationContext());
    }

    @Override public void onTerminate() {
        mediaCache = null;
        super.onTerminate();
    }
}
