/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.find_by_username;

import com.gstv.androidcodingexercise.joel.web_api.data_types.HttpResponseData;
/**
 *
 * @author user
 */
public class FindPersonByUsername {
    FindPersonByUsernameHttp http;
    FindPersonByUsernameParser parser;
    HttpResponseData data;
    
    public FindPersonByUsername() {
         http = new FindPersonByUsernameHttp();
         parser = new FindPersonByUsernameParser();
    }
    
    public FindPersonResponseData findPersonByUsername(String _username) {
        FindPersonResponseData ret = new FindPersonResponseData();
        
        if(!http.setUpConnection(_username)) {
        } else if((data = http.getPersonByUsername()).error < 0) {
        } else {
            ret = parser.parseJsonIntoObject(data.httpResponseBody);
        }
        
        return ret;
    }
    
    public void stop() {
        http.shutDownConnection();
    }
}
