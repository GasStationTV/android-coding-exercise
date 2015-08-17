/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
/**
 *
 * @author user
 */
public class Defines {
    public static final String flickrBaseUrl = "https://api.flickr.com/services/rest/?method=";
    public static final String apiKey = "c34fb9679eadf5d5e30187fcd1a633f1";
    public static final String nsid = "135341216@N03";
    
    public static final String getPeopleByUsernameMethod = "flickr.people.findByUsername";
    public static final String getUserPhotoListMethod = "flickr.people.getPublicPhotos";
    public static final String getPhotosByKeywordMethod = "flickr.photos.search";

    public static final int mainStartImageListDisplayActivity = 100;

    public static final int imageListDisplayAddDataToAdapter = 200;
    public static final int imageListDisplaySetResults = 201;

    public static final int activityStartProgressDialog = 300;
    public static final int activityStopProgressDialog = 301;

    public static String removeFlickrApiFromJson(String _flickrResponse) {
        String ret = null;
        int startPosition = _flickrResponse.indexOf("{");
        int endPosition = _flickrResponse.lastIndexOf("}");
        
        if(startPosition < 0 || endPosition < 0) {
        } else {
            ret = _flickrResponse.substring(startPosition, endPosition + 1);
        }
        
        return ret;
    }
}
