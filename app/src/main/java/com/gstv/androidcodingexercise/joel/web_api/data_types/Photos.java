/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel.web_api.data_types;

import com.gstv.androidcodingexercise.joel.web_api.data_types.Photo;

/**
 *
 * @author user
 */
public class Photos {
    public int page;
    public int pages;
    public int perPage;
    public String total;
    public Photo[] photo;

    public Photos() {
        page = -1;
        pages = -1;
        perPage = -1;
        total = "-1";
    }
}
