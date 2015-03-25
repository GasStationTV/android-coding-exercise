package com.gstv.androidcodingexercise.model;

import java.util.ArrayList;
import java.util.List;

/**
 * AndroidCodingExercise
 * Created by Michael Girard on 3/25/15.
 * Copyright 2015 GasStationTV inc. All rights reserved.
 */
public class Photos {
    public int page = 0;
    public int pages = 0;
    public int perpage = 0;
    public int total = 0;
    List<Photo> photos = new ArrayList<>();
}
