package com.example.busroutefinder;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class SolutionRoute {
    ArrayList<String> routBiginingFrom=new ArrayList<String>();
    ArrayList<String> routBiginingChange=new ArrayList<String>();
    private String name;
    private double distance;

    public ArrayList<String> getRoutBiginingFrom() {
        return routBiginingFrom;
    }

    public ArrayList<String> getRoutBiginingChange() {
        return routBiginingChange;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public void setRoutBiginingFrom(String routBiginingfrom) {
        routBiginingFrom.add(routBiginingfrom);
    }

    public void setRoutBiginingChange(String routBiginingChange) {
        this.routBiginingChange.add(routBiginingChange);
    }

    public void setName(String sname) {
        this.name = sname;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    
    
}
