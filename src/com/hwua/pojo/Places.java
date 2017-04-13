package com.hwua.pojo;

public class Places {
    private String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

	public Places(String place) {
		super();
		this.place = place;
	}

	public Places() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Places [place=" + place + "]";
	}
    
}