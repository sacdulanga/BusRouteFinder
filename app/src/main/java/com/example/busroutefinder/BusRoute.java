package com.example.busroutefinder;

public class BusRoute {

	private String route;
	private String frome;
	private String to;
	private String[] holts;
	private String holtlist;
	
	public BusRoute(String route, String frome, String to, String holts) {
		super();
		this.route = route;
		this.frome = frome;
		this.to = to;
		this.holts = holts.split(",");
		this.holtlist=holts;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getFrome() {
		return frome;
	}

	public void setFrome(String frome) {
		this.frome = frome;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String[] getHolts() {
		return holts;
	}

	public void setHolts(String[] holts) {
		this.holts = holts;
	}

	public String getHoltlist() {
		return holtlist;
	}

	public void setHoltlist(String holtlist) {
		this.holtlist = holtlist;
	}
	
	
	
	
}
