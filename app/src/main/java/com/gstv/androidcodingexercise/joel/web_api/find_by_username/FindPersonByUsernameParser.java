/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.find_by_username;

import com.google.gson.Gson;
import com.gstv.androidcodingexercise.joel.web_api.Defines;
import com.gstv.androidcodingexercise.joel.web_api.data_types.FlickrErrorJsonData;

/**
 *
 * @author user
 */
public class FindPersonByUsernameParser {
    
    public FindPersonByUsernameParser() {
    }
    
    public FindPersonResponseData parseJsonIntoObject(String _json) {
        FindPersonResponseData ret = new FindPersonResponseData();
        FindPersonJsonData dataSuccess;
        FlickrErrorJsonData dataError;
        
        String json = Defines.removeFlickrApiFromJson(_json);
        if(json == null) {
        } else if((dataSuccess = parseValidResponse(json)) != null
                && dataSuccess.stat.compareTo("ok") == 0) {
            ret.jsonData = dataSuccess;
        } else if((dataError = parseErrorResponse(json)) != null) {
            ret.errorData = dataError;
        }
        
        return ret;
    }
    
    private FindPersonJsonData parseValidResponse(String _json) {
        FindPersonJsonData ret = null;
        
        try {
            Gson gson = new Gson();
            ret = gson.fromJson(_json, FindPersonJsonData.class);
        } catch(Exception _e) {
        }
        
        return ret;
    }
    
    private FlickrErrorJsonData parseErrorResponse(String _json) {
        FlickrErrorJsonData ret = null;
        
        try {
            Gson gson = new Gson();
            ret = gson.fromJson(_json, FlickrErrorJsonData.class);
        } catch(Exception _e) {
        }
        
        return ret;
    }
}
