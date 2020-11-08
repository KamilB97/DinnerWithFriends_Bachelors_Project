package com.kamil.dinnerapp.entity;

public class JwtUser {

	private Integer id;
	private String userName;
	

	public JwtUser() {
	}

	public JwtUser(Integer id, String userName) {
		this.id = id;
		this.userName = userName;
		
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

}
