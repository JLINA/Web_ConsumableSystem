package com.hwua.pojo;

import java.util.Date;

public class Users {
	
    private long id;   //���

    private String username;  //�û���

    private String password;  //����
 
    private String role;    //���Ȩ��

    private String phone;   //�绰

    private String createtime;   //����ʱ��

    private String lasttime;   //�ϴε�¼ʱ��
    
    private String state;    //ʹ��״̬

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", phone="
				+ phone + ", createtime=" + createtime + ", lasttime=" + lasttime + ", state=" + state + "]";
	}

	public Users(long id, String username, String password, String role, String phone, String createtime,
			String lasttime, String state) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.phone = phone;
		this.createtime = createtime;
		this.lasttime = lasttime;
		this.state = state;
	}

	public Users(String username, String password, String role, String phone, String createtime, String lasttime,
			String state) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.phone = phone;
		this.createtime = createtime;
		this.lasttime = lasttime;
		this.state = state;
	}

	public Users(long id, String username, String password, String role, String phone, String state) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.phone = phone;
		this.state = state;
	}

	
    
    
	
}