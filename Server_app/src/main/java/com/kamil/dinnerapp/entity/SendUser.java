package com.kamil.dinnerapp.entity;

public class SendUser {
	private Integer id;
	private String email;
	private String phone;
	private String password;
	
	public SendUser() {
	}

	public SendUser(String email, String phone, String password) {
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
