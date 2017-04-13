package com.hwua.pojo;

public class Execlimp {
	
	private String str;
	
	private String type;
	
	private String place;
	
    private double stprice;
	
	private double endprice;
	public Execlimp() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public double getStprice() {
		return stprice;
	}


	public void setStprice(double stprice) {
		this.stprice = stprice;
	}


	public double getEndprice() {
		return endprice;
	}


	public void setEndprice(double endprice) {
		this.endprice = endprice;
	}


	public Execlimp(String str, String type, String place, double stprice, double endprice) {
		super();
		this.str = str;
		this.type = type;
		this.place = place;
		this.stprice = stprice;
		this.endprice = endprice;
	}


	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	} 
	

	

}
