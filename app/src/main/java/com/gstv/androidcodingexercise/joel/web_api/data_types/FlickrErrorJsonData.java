/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.data_types;

/**
 *
 * @author user
 */
public class FlickrErrorJsonData {
    public String stat;
    public int code;
    public String message;
    
    public FlickrErrorJsonData() {
        stat = "";
        code = -1;
        message = "";
    }
}
