/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.find_by_username;

import com.google.gson.annotations.SerializedName; 
/**
 *
 * @author user
 */
public class FindPersonJsonData {
    public User user;
    public String stat;
    
    public FindPersonJsonData() {
    }
    
    public class User {
        public String id;
        public String nsid;
        public Username username;
    }
    
    public class Username {
        public String _content;
    }
}
