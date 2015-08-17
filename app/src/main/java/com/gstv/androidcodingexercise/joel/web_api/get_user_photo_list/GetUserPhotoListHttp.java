/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.get_user_photo_list;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import com.gstv.androidcodingexercise.joel.web_api.Defines;
import com.gstv.androidcodingexercise.joel.Mutex;
import com.gstv.androidcodingexercise.joel.web_api.data_types.HttpResponseData;

/**
 *
 * @author user
 */
public class GetUserPhotoListHttp {
    private HttpURLConnection connection;
    private final Mutex mutex;
    private String nsid;
    private int startingPageNumber;
    
    public GetUserPhotoListHttp() {
        connection = null;
        mutex = new Mutex();
        nsid = null;
        startingPageNumber = 1;
    }
    
    public boolean setUpConnection(String _nsid, int _startingPageNumber) {
        boolean ret = false;
        
        if(!mutex.lock()) {   
        } else {
            nsid = _nsid;
            startingPageNumber = _startingPageNumber;
            if(setUpConnectionCriticalSection()) {
                ret = true;
            }
            mutex.unlock();
        }
        
        return ret;
    }
    
    public HttpResponseData getUserPhotos() {
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
        
        try {
System.out.println("url : ||" + getUserPhotosByUsernameUrl() + "||");
            URL url = new URL(getUserPhotosByUsernameUrl());
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            ret = true;
        } catch(IOException _e) {
        }
        
        return ret;
    }
    
    // this url gets the thumbnail url
    // and original size photo url so we can use them
    private String getUserPhotosByUsernameUrl() {
        return Defines.flickrBaseUrl
                + Defines.getUserPhotoListMethod
                + "&api_key=" 
                + Defines.apiKey
                + "&user_id=" 
                + nsid
                + "&page="
                + startingPageNumber
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
