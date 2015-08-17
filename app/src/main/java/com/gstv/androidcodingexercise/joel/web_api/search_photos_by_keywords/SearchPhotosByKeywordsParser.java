/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.search_photos_by_keywords;

import com.google.gson.Gson;
import com.gstv.androidcodingexercise.joel.web_api.Defines;
import com.gstv.androidcodingexercise.joel.web_api.data_types.FlickrErrorJsonData;

/**
 *
 * @author user
 */
public class SearchPhotosByKeywordsParser {
    
    public SearchPhotosByKeywordsParser() {
    }
    
    public SearchPhotosByKeywordsResponseData parseJsonIntoObject(String _json) {
        SearchPhotosByKeywordsResponseData ret = new SearchPhotosByKeywordsResponseData();
        SearchPhotosByKeywordsJsonData dataSuccess;
        FlickrErrorJsonData dataError;
        
        String json = Defines.removeFlickrApiFromJson(_json);
        if(json == null) {
        } else if((dataSuccess = parseValidResponse(json)) != null
                && dataSuccess.stat.compareTo("ok") == 0) {
            ret.dataSuccess = dataSuccess;
        } else if((dataError = parseErrorResponse(json)) != null) {
            ret.dataError = dataError;
        }
        
        return ret;
    }
    
    private SearchPhotosByKeywordsJsonData parseValidResponse(String _json) {
        SearchPhotosByKeywordsJsonData ret = null;
        
        try {
            Gson gson = new Gson();
            ret = gson.fromJson(_json, SearchPhotosByKeywordsJsonData.class);
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
