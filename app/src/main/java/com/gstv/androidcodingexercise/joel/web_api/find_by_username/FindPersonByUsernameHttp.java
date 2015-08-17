/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.find_by_username;

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
public class FindPersonByUsernameHttp {
    private HttpURLConnection connection;
    private final Mutex mutex;
    private String username;
    
    public FindPersonByUsernameHttp() {
        connection = null;
        mutex = new Mutex();
        username = null;
    }
    
    public boolean setUpConnection(String _username) {
        boolean ret = false;
        
        if(!mutex.lock()) {   
        } else {
            username = _username;
            if(setUpConnectionCriticalSection()) {
                ret = true;
            }
            mutex.unlock();
        }
        
        return ret;
    }
    
    public HttpResponseData getPersonByUsername() {
        HttpResponseData ret = new HttpResponseData();
        
        if((ret.httpResponse = getPersonByUsernameGetResponseCode()) 
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
            URL url = new URL(getPersonByUsernameUrl());
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            ret = true;
        } catch(IOException _e) {
        }
        
        return ret;
    }
    
    private String getPersonByUsernameUrl() {
        return Defines.flickrBaseUrl 
                + Defines.getPeopleByUsernameMethod
                + "&api_key=" 
                + Defines.apiKey
                + "&username=" 
                + username
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
    
    private int getPersonByUsernameGetResponseCode() {
        int ret = -1;
        
        try {
            ret = connection.getResponseCode();
        } catch(IOException _e) {
        }
        
        return ret;
    }
}
