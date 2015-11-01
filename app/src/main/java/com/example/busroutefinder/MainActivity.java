package com.example.busroutefinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.window_01);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void button1(View v){
		Intent intent=new Intent(this,Place_to_Place_Activity.class);
		startActivity(intent);		
	}
	
	public void button2(View v){
		Intent intent=new Intent(this,RouteNumberSearch.class);
		startActivity(intent);		
	}
	
	public void button3(View v){
		Intent intent=new Intent(this,SetLocationActivity.class);
		startActivity(intent);		
	}
	
	public void button4(View v){
		Intent intent=new Intent(this,AddDataActivity.class);
		startActivity(intent);		
	}
	
}
