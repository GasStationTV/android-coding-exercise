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
public class Photo {
    public String id;
    public String owner;
    public String secret;
    public String server;
    public int farm;
    public String title;
    public int ispublic;
    public int isfriend;
    public int isfamily;
    public String url_t;
    public String height_t;
    public String width_t;
    public String url_o;
    public String height_o;
    public String width_o;

    public Photo() {
        id = "";
        owner = "";
        secret = "";
        server = "";
        farm = -1;
        title = "";
        ispublic = -1;
        isfriend = -1;
        isfamily = -1;
        url_t = "";
        height_t = "";
        width_t = "";
        url_o = "";
        height_o = "";
        width_o = "";
    }
}
