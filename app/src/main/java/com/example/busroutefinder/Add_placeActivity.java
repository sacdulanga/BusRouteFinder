package com.example.busroutefinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Add_placeActivity extends Activity {
	EditText place,location,cameOverRoutes,changeOverPlace;
	BusRouteAdapter busrotehelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		place=(EditText)findViewById(R.id.placeValue);
		location=(EditText)findViewById(R.id.locationValue);
		cameOverRoutes=(EditText)findViewById(R.id.cameOverRoutesValue);
		changeOverPlace=(EditText)findViewById(R.id.changeOverPlaceNameValue);
		busrotehelper=new BusRouteAdapter(this);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addPlace(View view){
		
		String splace=place.getText().toString();
		String scameOverRoutes=cameOverRoutes.getText().toString();
		String slocation=location.getText().toString();
		long id=busrotehelper.insertDataToPlaceTable(splace, scameOverRoutes, slocation);
		busrotehelper.insertDataToPlaceTable("Pettah","100:101:133:02:32:120:122:125:130:133:134:136:137:138/1:138/2:138/3:138/4","6.93430075:79.8551787");
		busrotehelper.insertDataToPlaceTable("Kottawa","255:128:129:138/2:138/3:174:296:308:336:336/1:342:","6.8396872:79.9643224");
		busrotehelper.insertDataToPlaceTable("Dehiwala","100:101:133:118:119:102:256:156:132:136:139:","6.851314:79.865983");
		busrotehelper.insertDataToPlaceTable("Mt.Lavinia","100:101:255:256:","6.8358686:79.879075");
		busrotehelper.insertDataToPlaceTable("Ratmalana","100:101:255:256:02:32:","6.8175444:79.8801797");
		busrotehelper.insertDataToPlaceTable("Katubadda","100:101:255:02:32:","6.8013371:79.896584");
		busrotehelper.insertDataToPlaceTable("University of Moratuwa","255:","6.796877:79.901778");
		busrotehelper.insertDataToPlaceTable("Moratuwa","100:101:158:102:142:154:158:161:192:208:","6.774427:79.88227");
		busrotehelper.insertDataToPlaceTable("Panadura","100:02:32:17:17/1:17/255:64:87:100:142:183:400/7:447:448:450:450/5:450/9:451:452:453:453/2","6.7284863:79.9299434");
		busrotehelper.insertDataToPlaceTable("Piliyandala","158:255:120:127:139:139/1:149:157:158:159:162:296:341:342:","6.801505:79.9299434");

		
		if(id<0){
			Message.message(this, "Unsuccessful");
		}
		else{
			Message.message(this, "Successfully insert a row");
		}
			
	}
	
	public void addChangeOver(View view){

		String schangeOverPlace=changeOverPlace.getText().toString();
		long id=busrotehelper.insertDataChangeOverPlaceTable(schangeOverPlace);//Matara 5.9519922,80.550748 02:32
		busrotehelper.insertDataChangeOverPlaceTable("Pettah");
		busrotehelper.insertDataChangeOverPlaceTable("Kottawa");
		busrotehelper.insertDataChangeOverPlaceTable("Dehiwala");
		busrotehelper.insertDataChangeOverPlaceTable("Mt.Lavinia");
		busrotehelper.insertDataChangeOverPlaceTable("Ratmalana");
		busrotehelper.insertDataChangeOverPlaceTable("Katubadda");
		busrotehelper.insertDataChangeOverPlaceTable("Moratuwa");
		busrotehelper.insertDataChangeOverPlaceTable("Panadura");
		busrotehelper.insertDataChangeOverPlaceTable("Piliyandala");
		if(id<0){
			Message.message(this, "Unsuccessful");
		}
		else{
			Message.message(this, "Successfully insert a row");
		}
	}
	
	public void showPTable(View view){
		StringBuffer buffer=new StringBuffer();
		String data=busrotehelper.getAllDataFromPlaceTable();
		Message.message(this, data);
		
	}
	
	public void showCTable(View view){
		String data=busrotehelper.getAllDataFromChangeOverTable();
		Message.message(this,data);
		
	}
}
