package com.hwua.pojo;

public class Types {
    private String typeid;

    private String typeids;

    @Override
	public String toString() {
		return "Types [typeid=" + typeid + ", typeids=" + typeids + "]";
	}

	public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getTypeids() {
        return typeids;
    }

    public void setTypeids(String typeids) {
        this.typeids = typeids == null ? null : typeids.trim();
    }

	public Types() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Types(String typeid, String typeids) {
		super();
		this.typeid = typeid;
		this.typeids = typeids;
	}
    
}