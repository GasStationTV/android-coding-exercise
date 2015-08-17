/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.get_user_photo_list;

import com.gstv.androidcodingexercise.joel.web_api.data_types.HttpResponseData;
import com.gstv.androidcodingexercise.joel.web_api.find_by_username.FindPersonByUsernameHttp;
import com.gstv.androidcodingexercise.joel.web_api.find_by_username.FindPersonByUsernameParser;
import com.gstv.androidcodingexercise.joel.web_api.find_by_username.FindPersonResponseData;

/**
 *
 * @author user
 */
public class GetUserPhotoList {
    GetUserPhotoListHttp http;
    GetUserPhotoListParser parser;
    HttpResponseData data;
    
    public GetUserPhotoList() {
         http = new GetUserPhotoListHttp();
         parser = new GetUserPhotoListParser();
    }
    
    public GetUserPhotoListResponseData getUserPhotoList(
            String _nsid, int _startingPageNumber) {
        GetUserPhotoListResponseData ret = new GetUserPhotoListResponseData();
        
        if(!http.setUpConnection(_nsid, _startingPageNumber)) {
        } else if((data = http.getUserPhotos()).error < 0) {
        } else {
            ret = parser.parseJsonIntoObject(data.httpResponseBody);
        }
        
        return ret;
    }
    
    public void stop() {
        
    }
}
