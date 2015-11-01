package com.example.busroutefinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BusRouteAdapter {
	SQLiteDatabase db;
	BusRoteHelper helper;
	public BusRouteAdapter(Context context){
		helper=new BusRoteHelper(context);	
		db=helper.getWritableDatabase();
	}
	
	public long insertDataToPlaceTable(String place,String routes,String location ){
		
		
		ContentValues contentValues=new ContentValues();
		contentValues.put(BusRoteHelper.PNAME, place.toLowerCase());
		contentValues.put(BusRoteHelper.CAMEOVERROUTES, routes);
		contentValues.put(BusRoteHelper.LOCATION, location);
		long id=db.insert(BusRoteHelper.TABLE_01_NAME,null,contentValues);
		return id;
		
	}
	
	public long insertDataToRouteTable(String route,String start,String end,String holts ){
		ContentValues contentValues=new ContentValues();
		contentValues.put(BusRoteHelper.ROUTE, route);
		contentValues.put(BusRoteHelper.FROME, start);
		contentValues.put(BusRoteHelper.TOO, end);
		contentValues.put(BusRoteHelper.HOLTS, holts);
		long id=db.insert(BusRoteHelper.TABLE_03_NAME,null,contentValues);
		return id;
		
	}
	
	
	public long insertDataChangeOverPlaceTable(String place){
		ContentValues contentValues=new ContentValues();
		contentValues.put(BusRoteHelper.PNAMEC, place.toLowerCase());
		long id=db.insert(BusRoteHelper.TABLE_02_NAME,null,contentValues);
		return id;
		
	}
	
	public String getAllDataFromPlaceTable(){
		String[] columns={BusRoteHelper.UID,BusRoteHelper.PNAME,BusRoteHelper.CAMEOVERROUTES,BusRoteHelper.LOCATION};
		Cursor cursor=db.query(BusRoteHelper.TABLE_01_NAME, columns, null, null, null, null, null);
		StringBuffer buffer=new StringBuffer();
		while(cursor.moveToNext()){
			int cid=cursor.getInt(0);
			String pname=cursor.getString(1);
			String camOverPlace=cursor.getString(2);
			String location=cursor.getString(3);
			buffer.append(cid+" "+pname+" "+camOverPlace+" "+location+"\n");
		}
		return buffer.toString();
		
	}
	
	public String getAllDataFromRouteTable(){
		String[] columns={BusRoteHelper.UID,BusRoteHelper.ROUTE,BusRoteHelper.FROME,BusRoteHelper.TOO,BusRoteHelper.HOLTS};
		Cursor cursor=db.query(BusRoteHelper.TABLE_03_NAME, columns, null, null, null, null, null);
		StringBuffer buffer=new StringBuffer();
		while(cursor.moveToNext()){
			int cid=cursor.getInt(0);
			String route=cursor.getString(1);
			String start=cursor.getString(2);
			String end=cursor.getString(3);
			String holts=cursor.getString(4);
			buffer.append(cid+" "+route+" "+start+" "+end+" "+holts+"\n");
		}
		return buffer.toString();
		
	}
	
	public String getAllDataFromChangeOverTable(){
		String[] columns={BusRoteHelper.UID,BusRoteHelper.PNAMEC};
		Cursor cursor=db.query(BusRoteHelper.TABLE_02_NAME, columns, null, null, null, null, null);
		StringBuffer buffer=new StringBuffer();
		while(cursor.moveToNext()){
			int cid=cursor.getInt(0);
			String pname=cursor.getString(1).toLowerCase();
			buffer.append(cid+" "+pname+"\n");
		}
		return buffer.toString();
	}
	
	public BusRoute getRouteDetail(String routenumber){
		BusRoute route=null;
		String[] columns={BusRoteHelper.UID,BusRoteHelper.ROUTE,BusRoteHelper.FROME,BusRoteHelper.TOO,BusRoteHelper.HOLTS};
		Cursor cursor=db.query(BusRoteHelper.TABLE_03_NAME, columns, BusRoteHelper.ROUTE+"='"+routenumber+"'", null, null, null, null);
		//StringBuffer buffer=new StringBuffer();
		while(cursor.moveToNext()){
			int index1=cursor.getColumnIndex(BusRoteHelper.ROUTE);
			int index2=cursor.getColumnIndex(BusRoteHelper.FROME);
			int index3=cursor.getColumnIndex(BusRoteHelper.TOO);
			int index4=cursor.getColumnIndex(BusRoteHelper.HOLTS);
			String Route=cursor.getString(index1);
			String from=cursor.getString(index2);
			String to=cursor.getString(index3);
			String holts=cursor.getString(index4);
			route=new BusRoute(Route,from,to,holts);
			
		}
		return route;
	}
	
	
	public BusHalt getFromOrToPlace(String name){
		BusHalt holt=null;
//		String fromquery="select PName,cameOver,location from place where PName='"+fromholt+"'";
		String[] columns={BusRoteHelper.PNAME,BusRoteHelper.CAMEOVERROUTES,BusRoteHelper.LOCATION};
		Cursor cursor=db.query(BusRoteHelper.TABLE_01_NAME, columns, BusRoteHelper.PNAME+"='"+name+"'", null, null, null, null);
		while(cursor.moveToNext()){
			int index1=cursor.getColumnIndex(BusRoteHelper.PNAME);
			int index2=cursor.getColumnIndex(BusRoteHelper.CAMEOVERROUTES);
			int index3=cursor.getColumnIndex(BusRoteHelper.LOCATION);
			String pname=cursor.getString(index1).toLowerCase();
			String cameoverroutes=cursor.getString(index2);
			String location=cursor.getString(index3);
			holt=new BusHalt(pname, cameoverroutes,location);
		}
		
		return holt;
		
	}
	
	public ArrayList<BusHalt> getChangeOverPlaces(){
		ArrayList<BusHalt> changeOverHolts =new ArrayList<BusHalt>() ;
//		String fromquery="select place.PName,comeOverRoute,location from place,changeover where changeover.PName=place.PName;";
		StringBuffer buffer=new StringBuffer();
		String selectQuery="SELECT  * FROM " + BusRoteHelper.TABLE_01_NAME + " td, "+ BusRoteHelper.TABLE_02_NAME + " tg WHERE tg." + BusRoteHelper.PNAMEC + " = " + "td." + BusRoteHelper.PNAME;
		Cursor cursor = db.rawQuery(selectQuery,null);
		while(cursor.moveToNext()){
			int index1=cursor.getColumnIndex(BusRoteHelper.PNAME);
			int index2=cursor.getColumnIndex(BusRoteHelper.CAMEOVERROUTES);
			int index3=cursor.getColumnIndex(BusRoteHelper.LOCATION);
			String pname=cursor.getString(index1);
			String cameoverroutes=cursor.getString(index2);
			String location=cursor.getString(index3);
			changeOverHolts.add(new BusHalt(pname, cameoverroutes,location));
			buffer.append(pname+" "+cameoverroutes+" "+location+"\n");		
			
		}
		return changeOverHolts;
		
		
	}
	
	
	   static class BusRoteHelper extends SQLiteOpenHelper  {
	    	
		    private static final String DATABASE_NAME="sac_busrotes";
			private static final String TABLE_01_NAME="place";
			private static final String TABLE_02_NAME="changeoverplace";
			private static final String TABLE_03_NAME="rotes";
			private static final int DATABASE_VERSION=24;
			private static final String UID="_id";
			private static final String PNAME="PName";
			private static final String PNAMEC="PName";
			private static final String ROUTE="Route";
			private static final String CAMEOVERROUTES="cameOverRoutes";
			private static final String LOCATION="location";
			private static final String FROME="Start";
			private static final String TOO="End"; 
			private static final String HOLTS="holts";
			private static final String CREATE_TABLE_01="CREATE TABLE "+TABLE_01_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+PNAME+" VARCHAR(255),"+CAMEOVERROUTES+" VARCHAR(255),"+LOCATION+" VARCHAR(255));";
			private static final String CREATE_TABLE_02="CREATE TABLE "+TABLE_02_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+PNAMEC+" VARCHAR(255));";
			private static final String CREATE_TABLE_03="CREATE TABLE "+TABLE_03_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ROUTE+" VARCHAR(255),"+FROME+" VARCHAR(255),"+TOO+" VARCHAR(255),"+HOLTS+" VARCHAR(255));";
			private Context context;
			private static final String DROP_TABLE1="DROP TABLE IF EXISTS "+TABLE_01_NAME;
			private static final String DROP_TABLE2="DROP TABLE IF EXISTS "+TABLE_02_NAME;
			private static final String DROP_TABLE3="DROP TABLE IF EXISTS "+TABLE_03_NAME;
			
			public BusRoteHelper(Context context) {
				super(context,"DATABASE_NAME",null,DATABASE_VERSION);
				this.context=context;
				Message.message(context, "costructor called");
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				try {
					db.execSQL(CREATE_TABLE_01);
					db.execSQL(CREATE_TABLE_02);
					db.execSQL(CREATE_TABLE_03);
					Message.message(context, "OnCreate called");
				} catch (SQLException e) {
					Message.message(context, ""+e);
				}
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				try {
					Message.message(context, "OnUpgrade called");
					db.execSQL(DROP_TABLE1);
					db.execSQL(DROP_TABLE2);
					db.execSQL(DROP_TABLE3);
					onCreate(db);
				} catch (SQLException e) {
					Message.message(context, ""+e);
				}
				
			}
			
			

	   }
	   
	   
	   //************************************************************
	   public void close() {
	        if (helper != null) {
	            helper.close();
	        }
	    }
	   
	   public long createList(String name) {
		   
	        ContentValues initialValues = new ContentValues();
	  
	        initialValues.put(BusRoteHelper.PNAME, name);
	 
	        return db.insert(BusRoteHelper.TABLE_01_NAME, null, initialValues);
	  
	    }
	  
	  
	    public Cursor searchByInputText(String name) throws SQLException {
	    	
			String[] columns={BusRoteHelper.PNAME,BusRoteHelper.CAMEOVERROUTES,BusRoteHelper.LOCATION};
			Cursor cursor=db.query(BusRoteHelper.TABLE_01_NAME, columns, BusRoteHelper.PNAME+"='"+name+"'", null, null, null, null);
	  
	        if (cursor != null) {
	            cursor.moveToFirst();
	        }
	        return cursor;
	  
	    }
	  
	  
//	    public boolean deleteAllNames() {
//	        int doneDelete = db.delete(FTS_VIRTUAL_TABLE, null , null);
//	        return doneDelete > 0;
//	  
//	    }
}
