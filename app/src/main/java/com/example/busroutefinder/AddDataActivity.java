package com.example.busroutefinder;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class AddDataActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_data);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void button1(View v){
		Intent intent=new Intent(this,Add_placeActivity.class);
		startActivity(intent);		
	}
	
	public void button2(View v){
		Intent intent=new Intent(this,Add_routeActivity.class);
		startActivity(intent);		
	}
}
