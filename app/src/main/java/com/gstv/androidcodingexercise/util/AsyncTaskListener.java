package com.gstv.androidcodingexercise.util;

/**
 * AndroidCodingExercise
 * Created by Michael Girard on 3/26/15.
 * Copyright 2015 GasStationTV inc. All rights reserved.
 */
public interface AsyncTaskListener {

    public void onCompletion(Object object);
    public void onError(Exception e);
}
