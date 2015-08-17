/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.search_photos_by_keywords;

import com.gstv.androidcodingexercise.joel.web_api.data_types.FlickrErrorJsonData;

/**
 *
 * @author user
 */
public class SearchPhotosByKeywordsResponseData {
    public SearchPhotosByKeywordsJsonData dataSuccess;
    public FlickrErrorJsonData dataError;
    
    public SearchPhotosByKeywordsResponseData() {
        dataSuccess = null;
        dataError = null;
    }
}
