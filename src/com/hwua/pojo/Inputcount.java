package com.hwua.pojo;

public class Inputcount {

	private long id;
	
	private long count;
	
	private long surplus;

	public Inputcount(long id, long count, long surplus) {
		super();
		this.id = id;
		this.count = count;
		this.surplus = surplus;
	}

	public Inputcount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inputcount(long id, long surplus) {
		super();
		this.id = id;
		this.surplus = surplus;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getSurplus() {
		return surplus;
	}

	public void setSurplus(long surplus) {
		this.surplus = surplus;
	}
}
