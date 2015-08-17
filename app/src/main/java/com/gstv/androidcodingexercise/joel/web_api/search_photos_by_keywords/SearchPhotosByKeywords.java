/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.search_photos_by_keywords;

import com.gstv.androidcodingexercise.joel.web_api.data_types.HttpResponseData;
import com.gstv.androidcodingexercise.joel.web_api.search_photos_by_keywords.SearchPhotosByKeywordsHttp;
import com.gstv.androidcodingexercise.joel.web_api.search_photos_by_keywords.SearchPhotosByKeywordsParser;
import com.gstv.androidcodingexercise.joel.web_api.search_photos_by_keywords.SearchPhotosByKeywordsResponseData;

/**
 *
 * @author user
 */
public class SearchPhotosByKeywords {
    SearchPhotosByKeywordsHttp http;
    SearchPhotosByKeywordsParser parser;
    HttpResponseData data;
    
    public SearchPhotosByKeywords() {
         http = new SearchPhotosByKeywordsHttp();
         parser = new SearchPhotosByKeywordsParser();
    }
    
    public SearchPhotosByKeywordsResponseData getUserPhotoList(
            String _apiKey, String _nsid, String _keywords) {
        SearchPhotosByKeywordsResponseData ret = new SearchPhotosByKeywordsResponseData();
        
        if(!http.setUpConnection(_apiKey, _nsid, _keywords)) {
        } else if((data = http.searchForPhotos()).error < 0) {
        } else {
            ret = parser.parseJsonIntoObject(data.httpResponseBody);
        }
        
        return ret;
    }
    
    public void stop() {
        http.shutDownConnection();
    }
}
