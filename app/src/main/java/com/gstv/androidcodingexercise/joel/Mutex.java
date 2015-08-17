/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gstv.androidcodingexercise.joel;

import java.util.concurrent.Semaphore;
/**
 *
 * @author user
 */
public class Mutex {
    private final Semaphore lock;
    
    public Mutex() {
        lock = new Semaphore(1);
    }
    
    public boolean lock() {
        boolean ret = false;

        try {
            lock.acquire();
            ret = true;
        } catch(InterruptedException _e) {
        }
        
        return ret;
    }
    
    public void unlock() {
        lock.release();
    }
}
