/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.find_by_username;

import com.gstv.androidcodingexercise.joel.web_api.data_types.FlickrErrorJsonData;
/**
 *
 * @author user
 */
public class FindPersonResponseData {
    public FindPersonJsonData jsonData;
    public FlickrErrorJsonData errorData;
    
    public FindPersonResponseData() {
        jsonData = null;
        errorData = null;
    }
}
