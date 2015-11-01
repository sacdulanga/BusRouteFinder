package com.example.busroutefinder;

import android.app.Activity;

public class DistanceFinder extends Activity {
	
	private char unit='K';
    private double lat1,lon1;
    private double lat2,lon2;
    private String []placeone;
    private String []placetwo;
		
    public double distance(BusHalt Placeone, BusHalt Placetwo) {
        this.placeone=Placeone.getLocation();
        this.placetwo=Placetwo.getLocation();
        lat1=Double.parseDouble(placeone[0]);
        lon1=Double.parseDouble(placeone[1]);
        lat2=Double.parseDouble(placetwo[0]);
        lon2=Double.parseDouble(placetwo[1]); 
	        
        double theta = (lon1 - lon2);
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
        	dist = dist * 1.609344;
        } else if (unit == 'N') {
		    dist = dist * 0.8684;
        }
        	return (dist);
    }
	    
    public double distanceofPoint(double la1,double ln1,double la2,double ln2){
    	 lat1=la1;
         lon1=ln1;
         lat2=la2;
         lon2=ln2;
 	        
         double theta = (lon1 - lon2);
         double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
         dist = Math.acos(dist);
         dist = rad2deg(dist);
         dist = dist * 60 * 1.1515;
         if (unit == 'K') {
         	dist = dist * 1.609344;
         } else if (unit == 'N') {
 		    dist = dist * 0.8684;
         }
         	return (dist);
    }
    private double deg2rad(double deg) {
    	return (deg * Math.PI / 180.0);
    }
	    
    private double rad2deg(double rad) {
    	return (rad * 180 / Math.PI);
    }   

}
