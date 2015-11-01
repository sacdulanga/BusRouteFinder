package com.example.busroutefinder.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.busroutefinder.AddDataActivity;
import com.example.busroutefinder.Add_placeActivity;
import com.example.busroutefinder.Add_routeActivity;
import com.example.busroutefinder.MainActivity;
import com.example.busroutefinder.Place_to_Place_Activity;
import com.example.busroutefinder.RouteNumberSearch;
import com.example.busroutefinder.SetLocationActivity;
import com.robotium.solo.Solo;

public class Test extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	
	public Test() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}
	
	public void test1(){
		solo.clickOnButton("Find Route");
		solo.assertCurrentActivity("Test Activity",Place_to_Place_Activity.class);
		solo.goBack();
	}
	
	public void test2(){
		solo.clickOnButton("Route Number");
		solo.assertCurrentActivity("Test Activity",RouteNumberSearch.class);
		solo.goBack();
	}
	
	public void test3(){
		solo.clickOnButton("Notify Me");
		solo.assertCurrentActivity("Test Activity",SetLocationActivity.class);
		solo.clickOnButton("Set Alarm");
		solo.goBack();
	}
	public void test4(){
		solo.clickOnButton("Add Data");
		solo.assertCurrentActivity("Test Activity",AddDataActivity.class);
		solo.clickOnButton("Add Place");
		solo.assertCurrentActivity("Test Activity",Add_placeActivity.class);
		solo.goBack();
		solo.clickOnButton("Add Route");
		solo.assertCurrentActivity("Test Activity",Add_routeActivity.class);
		solo.goBack();
	}

}
