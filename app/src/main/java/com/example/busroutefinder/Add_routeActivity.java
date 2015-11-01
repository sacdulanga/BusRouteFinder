package com.example.busroutefinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Add_routeActivity extends Activity{
	EditText route,start,end,holts;
	BusRouteAdapter busrotehelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addroute);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		route=(EditText)findViewById(R.id.routeValue);
		start=(EditText)findViewById(R.id.startValue);
		end=(EditText)findViewById(R.id.endValue);
		holts=(EditText)findViewById(R.id.holtsValue);
		busrotehelper=new BusRouteAdapter(this);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addRoute(View view){
		
		String sroute=route.getText().toString();
		String sstart=start.getText().toString();
		String send=end.getText().toString();
		String sholts=holts.getText().toString();
		long id=busrotehelper.insertDataToRouteTable(sroute, sstart,send,sholts);//"192","Moratuwa","Maharagama","Moratuwa,Maliban,Attidiya,Boralesgamuwa,Maharagama"
		busrotehelper.insertDataToRouteTable("100","Moratuwa","Pettah","Panadura,Walana,Old Galle Road,Keselwatta,Moratuwa,Ratmalana,Mt.Lavinia,Dehiwala,Wellawatta,Bambalapitiya,Kollupitiya,Galle Face,Fort,Pettah");
		busrotehelper.insertDataToRouteTable("101","Moratuwa","Pettah","Moratuwa,Ratmalana,Mt.Lavinia,Dehiwala,Wellawatta,Bambalapitiya,Kollupitiya,Slave Island,Lake House,Fort,Pettah");
//		busrotehelper.insertDataToPlaceTable("255","100:101:133:118:119:102:256:156:132:136:139:","6.851314:79.865983");
//		busrotehelper.insertDataToPlaceTable("","100:101:255:256:","6.8358686:79.879075");
//		busrotehelper.insertDataToPlaceTable("Ratmalana","100:101:255:256:02:32:","6.8175444:79.8801797");
//		busrotehelper.insertDataToPlaceTable("Katubadda","100:101:255:02:32:","6.8013371:79.896584");
//		busrotehelper.insertDataToPlaceTable("University of Moratuwa","255:","6.796877:79.901778");
//		busrotehelper.insertDataToPlaceTable("Moratuwa","100:101:158:102:142:154:158:161:192:208:","6.774427:79.88227");
//		busrotehelper.insertDataToPlaceTable("Panadura","100:02:32:17:17/1:17/255:64:87:100:142:183:400/7:447:448:450:450/5:450/9:451:452:453:453/2","6.7284863:79.9299434");
//		busrotehelper.insertDataToPlaceTable("Piliyandala","158:255:120:127:139:139/1:149:157:158:159:162:296:341:342:","6.801505:79.9299434");

		
		if(id<0){
			Message.message(this, "Unsuccessful");
		}
		else{
			Message.message(this, "Successfully insert a row");
		}
			
	}
	
	public void showRouteTable(View view){
		StringBuffer buffer=new StringBuffer();
		String data=busrotehelper.getAllDataFromRouteTable();
		Message.message(this, data);
		
	}
	
}
