package com.example.busroutefinder;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SetLocationActivity extends FragmentActivity {
	
	 GoogleMap googleMap;
	 TextView textLat,textLong;
	 String content="You have arrived at your destination";
	 double Lt,Ln;
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.set_location);
	        getActionBar().setDisplayHomeAsUpEnabled(true);
				SupportMapFragment supportMapFragment = (SupportMapFragment)
				getSupportFragmentManager().findFragmentById(R.id.maps);
				textLat=(TextView)findViewById(R.id.textLat);
				textLong=(TextView)findViewById(R.id.textLong);
				generatemap();
				
	    }
	    
	    public void generatemap(){
	    	 // Getting a reference to the map
	        try {
				initializeMap();
				//googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);;
	        	 googleMap.setOnMapClickListener(new OnMapClickListener() {
	        		 
	                 @Override
	                 public void onMapClick(LatLng latLng) {
	      
	                     // Creating a marker
	                     MarkerOptions markerOptions = new MarkerOptions();
	      
	                     // Setting the position for the marker
	                     markerOptions.position(latLng);
	      
	                     // Setting the title for the marker.
	                     // This will be displayed on taping the marker
	                     markerOptions.title(latLng.latitude + " : " + latLng.longitude);
	                     Lt=latLng.latitude;
	                     Ln=latLng.longitude;
	      
	                     // Clears the previously touched position
	                     googleMap.clear();
	      
	                     // Animating to the touched position
	                     googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
	      
	                     // Placing a marker on the touched position
	                     googleMap.addMarker(markerOptions);
	                 }
	                 
	             });
	        	 
			} catch (Exception e) {
				Message.message(this, e.toString());
			}
	    }
	 
	    
	    private void initializeMap() 
	    {
	        if (googleMap == null) {
	            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.maps)).getMap();
	           
	            // check if map is created successfully or not
	            if (googleMap == null) {
	                Toast.makeText(getApplicationContext(),"Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
	            }
	        }
	    }
	    
	    	
	    
	    public void setAlarm(View view){
	        LocationManager lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		    LocationListener ll=new mylocationListner(); 
		    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,ll);
	    	
	    	double []location=new double[2];
	    	location[0]=67945.32;
	    	location[1]=79.90096;
	    }
	
	    
	    //******************************************************
	    
	    class mylocationListner implements LocationListener{
	    	  
	    	  @Override
	    	public void onLocationChanged(Location location) {
	    		  if(location !=null){
	    			  double pLong=location.getLongitude();
	    			  double pLat=location.getLatitude();
	    			  
	    			  textLat.setText(Double.toString(pLat));
	    			  textLong.setText(Double.toString(pLong));
	    			  DistanceFinder d=new DistanceFinder();
	    			  double distance=d.distanceofPoint(Lt, Ln, pLat, pLong);
	    			  
	    			  pLat=pLat*10000;
	    			  int pLongInt=(int)pLat;
	    			  int mInt=67945;
	    			  if(distance<=1){
	    				  sendBasicNotification();
	    				  finish();
	    		            System.exit(0);
	    			  }
	    		  }
	    	}
	    	  @Override
	    	public void onStatusChanged(String provider, int status, Bundle extras) {
	    	}
	    	  
	    	  @Override
	    	public void onProviderDisabled(String provider) {
	    	}
	    	  
	    	@Override
	    	public void onProviderEnabled(String provider) {
	    	}
	    	
	    	
	      }
	      
	    @Override
	    protected void onStop() {
	    	super.onStop();
	    }
	      
	      @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	  if(item.getItemId()==android.R.id.home){ 
	  			finish();
	  		}
	    	return super.onOptionsItemSelected(item);
	    }
	      
	      public void sendBasicNotification(){
	  		NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
	  		builder.setAutoCancel(true);
	  		builder.setDefaults(Notification.DEFAULT_SOUND);
	  		builder.setContentTitle("Bus Route Finder");
	  		builder.setContentText(content);
	  		builder.setSmallIcon(R.drawable.icon);
	  		
	  		Notification notification=builder.build();
	  		NotificationManager manager=(NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
	  		manager.notify(0,notification);
	  	}

}
