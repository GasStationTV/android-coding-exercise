/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api;

import com.gstv.androidcodingexercise.joel.Mutex;
/**
 *
 * @author user
 */
public class DataManagerSingleton {
    private static final Mutex mutex = new Mutex();
    private static String username = "";
    private static String nsid = "";
    
    public static boolean setUsername(String _username) {
        boolean ret = false;
        
        if(!mutex.lock()) {
        } else {
            if(_username == null || _username.length() < 50) {
                username = _username;
            }
            mutex.unlock();
        }
        
        return ret;
    }
    
    public static String getUsername() {
        String ret = "";
        
        if(!mutex.lock()) {
        } else {
            ret = new String(username);
            mutex.unlock();
        }
        
        return ret;
    }
    
    public static boolean setNsid(String _nsid) {
        boolean ret = false;
        
        if(!mutex.lock()) {
        } else {
            if(_nsid == null || _nsid.length() < 50) {
                nsid = _nsid;
            }
            mutex.unlock();
        }
        
        return ret;
    }
    
    public static String getNsid() {
        String ret = "";
        
        if(!mutex.lock()) {
        } else {
            ret = new String(nsid);
            mutex.unlock();
        }
        
        return ret;
    }
}
