package com.hwua.pojo;

public class History {
    private long id;

    private String times;

    private long userId;

    private String operate;

    private String operateobject;
    
    private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", times=" + times + ", userId=" + userId + ", operate=" + operate
				+ ", operateobject=" + operateobject + ", user=" + user + "]";
	}

	public History(String times, long userId, String operate) {
		super();
		this.times = times;
		this.userId = userId;
		this.operate = operate;
	}

	public History(String times, long userId, String operate, String operateobject) {
		super();
		this.times = times;
		this.userId = userId;
		this.operate = operate;
		this.operateobject = operateobject;
	}

	public History() {
		super();
		// TODO Auto-generated constructor stub
	}

	public History(long id, String times, long userId, String operate, String operateobject) {
		super();
		this.id = id;
		this.times = times;
		this.userId = userId;
		this.operate = operate;
		this.operateobject = operateobject;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getOperateobject() {
		return operateobject;
	}

	public void setOperateobject(String operateobject) {
		this.operateobject = operateobject;
	}

   
}