package com.kamil.dinnerapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	@Column(name="email")
	private String email;
	@Column(name="phone")
	private String phone;
	@Column(name="password")
	private String password;
	@Column(name="register_date")
	private String registerDate;
	
	@OneToOne(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value = "user")
	
		// WAŻNE ( solves the problem that jackson returns circled / infinity looped result 
												// in bi-directional data connection  
	private Profile profile;
	
	public User() {
		
	}

	public User(String email, String phone, String password, String registerDate) {
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.registerDate = registerDate;
	}

	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getId() {
		return id;
	} 
	public void setId(int id) {
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

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", phone=" + phone + ", password=" + password 
				 + ", registerDate=" + registerDate + "]";
	}
	
	

}
