package com.example.busroutefinder;

import android.app.Activity;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class BusHalt extends Activity {

	private String name;
    private String[] roteList;
    private String[] location;

    public BusHalt(String PName,String Rotes ,String location) {
        this.name=PName;    
        this.roteList=Rotes.split(":");
        this.location=location.split(":");
    }

    public String getName() {
        return name;
    }

    public String[] getRoteList() {
        return roteList;
    }

    public String[] getLocation() {
        return location;
    }           
}


