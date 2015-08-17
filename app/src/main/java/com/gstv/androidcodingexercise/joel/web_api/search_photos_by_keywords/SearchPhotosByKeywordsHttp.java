/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.search_photos_by_keywords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.gstv.androidcodingexercise.joel.Mutex;
import com.gstv.androidcodingexercise.joel.web_api.Defines;
import com.gstv.androidcodingexercise.joel.web_api.data_types.HttpResponseData;

/**
 *
 * @author user
 */
public class SearchPhotosByKeywordsHttp {
    private HttpURLConnection connection;
    private final Mutex mutex;
    private String apiKey;
    private String nsid;
    private String keywords;
    
    public SearchPhotosByKeywordsHttp() {
        connection = null;
        mutex = new Mutex();
        apiKey = null;
        nsid = null;
        keywords = null;
    }
    
    public boolean setUpConnection(String _apiKey, String _nsid, String _keywords) {
        boolean ret = false;
        
        if(!mutex.lock()) {   
        } else {
            apiKey = _apiKey;
            nsid = _nsid;
            keywords = _keywords;
            if(setUpConnectionCriticalSection()) {
                ret = true;
            }
            mutex.unlock();
        }
        
        return ret;
    }
    
    public HttpResponseData searchForPhotos() {
        HttpResponseData ret = new HttpResponseData();
        
        if((ret.httpResponse = getUserPhotosGetResponseCode()) 
                != HttpURLConnection.HTTP_OK) {
        } else if((ret.httpResponseBody = readResponse()) == null) {
        } else {
            ret.error = 0;
        }
        connection.disconnect();
        
        return ret;
    }
    
    public boolean shutDownConnection() {
        boolean ret = false;
        
        if(!mutex.lock()) {   
        } else {
            connection.disconnect();
            connection = null;
            mutex.unlock();
        }
        
        return ret;
    }
    
    
    private boolean setUpConnectionCriticalSection() {
        boolean ret = false;
System.out.println("url : ||" + getSearchPhotosByKeywordsUrl() + "||");
        try {
            URL url = new URL(getSearchPhotosByKeywordsUrl());
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            ret = true;
        } catch(IOException _e) {
        }
        
        return ret;
    }
    
    // this url gets the thumbnail url
    // and original size photo url so we can use them
    private String getSearchPhotosByKeywordsUrl() {
        return Defines.flickrBaseUrl
                + Defines.getPhotosByKeywordMethod
                + "&api_key=" 
                + apiKey
                + "&user_id=" 
                + nsid
                + "&text="
                + keywords
                + "&extras=url_t,url_o"
                + "&format=json";
    }
    
    private String readResponse() {
        String ret = null;
        String temp = "";
        
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            while((line = bufferedReader.readLine()) != null) {
                temp += line;
            }
            bufferedReader.close();
            ret = temp;
        } catch(IOException _e) {
        }
        
        return ret;
    }
    
    private int getUserPhotosGetResponseCode() {
        int ret = -1;
        
        try {
            ret = connection.getResponseCode();
        } catch(IOException _e) {
        }
        
        return ret;
    }
}
