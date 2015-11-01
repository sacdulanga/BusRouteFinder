package com.example.busroutefinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class RouteNumberSearch extends Activity {
	
	EditText route;
	String result,allResult;
	BusRouteAdapter busrotehelper;
	BusRoute routeNumber;
	private static  int count=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findroute);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		busrotehelper=new BusRouteAdapter(this);
		
		route=(EditText)findViewById(R.id.editText1);
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void Button1(View view){
		
		
		if(count>0){
			MyDialog2 myAlert=new MyDialog2("Start : "+routeNumber.getFrome()+"\n"+"End : "+routeNumber.getTo()+"\n"+"Holts : "+routeNumber.getHoltlist(),route.getText().toString());
			myAlert.show(getFragmentManager(), "My Alert");
		}
		else{
			try {
				
				routeNumber=busrotehelper.getRouteDetail(route.getText().toString());
				//Message.message(this, route.getText().toString());
				MyDialog2 myAlert=new MyDialog2("Start : "+routeNumber.getFrome()+"\n"+"End : "+routeNumber.getTo()+"\n"+"Holts : "+routeNumber.getHoltlist(),route.getText().toString());
				myAlert.show(getFragmentManager(), "My Alert");
				count++;
			} catch (Exception e) {
				Message.message(this, "Try Again");
			}
		}
		
	}
	
	
	public void Button2(View view){
		route.setText(null);
		count=0;
	}

}
