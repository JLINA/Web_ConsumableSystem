package com.hwua.pojo;

public class SelectAll {
	
	private int max;
	
	private int min;
	
	private String str;
	
	private String type;
	
	private String place;
	
	private double stprice;
	
	private double endprice;
	

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public double getStplice() {
		return stprice;
	}

	public void setStplice(double stprice) {
		this.stprice = stprice;
	}

	public double getEndplice() {
		return endprice;
	}

	public void setEndplice(double endprice) {
		this.endprice = endprice;
	}

	public SelectAll(String str, String place, double stprice, double endprice) {
		super();
		this.str = str;
		this.place = place;
		this.stprice = stprice;
		this.endprice = endprice;
	}

	public SelectAll(int max, int min, String str, String type, String place, double stprice, double endprice) {
		super();
		this.max = max;
		this.min = min;
		this.str = str;
		this.type = type;
		this.place = place;
		this.stprice = stprice;
		this.endprice = endprice;
	}

	public SelectAll(String str, String type) {
		super();
		this.str = str;
		this.type = type;
	}

	public SelectAll(int max, int min, String str, String type) {
		super();
		this.max = max;
		this.min = min;
		this.str = str;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SelectAll(String str) {
		super();
		this.str = str;
	}

	public SelectAll() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SelectAll(int max, int min, String str) {
		super();
		this.max = max;
		this.min = min;
		this.str = str;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
