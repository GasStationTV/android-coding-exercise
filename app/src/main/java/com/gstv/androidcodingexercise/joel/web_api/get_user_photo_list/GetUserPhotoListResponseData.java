/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.get_user_photo_list;

import com.gstv.androidcodingexercise.joel.web_api.data_types.FlickrErrorJsonData;
/**
 *
 * @author user
 */
public class GetUserPhotoListResponseData {
    public GetUserPhotoListJsonData dataSuccess;
    public FlickrErrorJsonData dataError;
    
    public GetUserPhotoListResponseData() {
        dataSuccess = null;
        dataError = null;
    }
}
