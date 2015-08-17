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
public class HttpResponseData {
    public int error;
    public int httpResponse;
    public String httpResponseBody;
    
    public HttpResponseData() {
        error = -1;
        httpResponse = 0;
        httpResponseBody = "";
    }
}
