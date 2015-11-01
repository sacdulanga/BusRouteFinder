package com.example.busroutefinder;


import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Place_to_Place_Activity extends Activity{
	EditText from,to;
	String result,allResult;
	BusRouteAdapter busrotehelper;
	private static  int count=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place_to_place_window);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		busrotehelper=new BusRouteAdapter(this);
		
		from=(EditText)findViewById(R.id.editFrom);
		to=(EditText)findViewById(R.id.editTo);
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void findroute(View view){
		if(count>0){
			MyDialog2 myAlert=new MyDialog2(this.getresult().toString(),"Best Route");
			myAlert.show(getFragmentManager(), "My Alert");
		}
		else{
			try {
				
				String fromS=from.getText().toString();
				String toS=to.getText().toString();
				RouteFinder r=new RouteFinder();
				r.step_01(this,fromS, toS);
				MyDialog2 myAlert=new MyDialog2(this.getresult().toString(),"Best Route");
				myAlert.show(getFragmentManager(), "My Alert");
				count++;
			} catch (Exception e) {
				Message.message(this, "Try Again");
			}
		}
		
	}
	
	public void findAllPosibleRoutes(View view){
		if(count>0){
			MyDialog2 myAlert=new MyDialog2(this.getAllResult().toString(),"All Results");
			myAlert.show(getFragmentManager(), "My Alert");
		}
		else{
			try {
				
				String fromS=from.getText().toString();
				String toS=to.getText().toString();
				RouteFinder r=new RouteFinder();
				r.step_01(this,fromS, toS);
				MyDialog2 myAlert=new MyDialog2(this.getAllResult().toString(),"All Results");
				myAlert.show(getFragmentManager(), "My Alert");
				count++;
			} catch (Exception e) {
				Message.message(this, "Try Again");
			}
		}
	}
	
	public ArrayList<BusHalt> getChangeOverPlaces(){
		return busrotehelper.getChangeOverPlaces();
	}
	
	public BusHalt getBusHolt(String name){
		return busrotehelper.getFromOrToPlace(name);

	}
	
	public void setResult(String data){
		result=data;
	}
	
	public String getresult(){
		return result;
	}
	
	public void setAllResult(String data){
		allResult=data;
	}
	
	public String getAllResult(){
		return allResult;
	}
	
	public void showDialog(View view){
		from.setText(null);
		to.setText(null);
		this.setAllResult(null);
		this.setResult(null);
		count=0;
	}
}
