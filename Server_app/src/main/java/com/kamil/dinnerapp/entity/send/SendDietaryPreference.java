package com.kamil.dinnerapp.entity.send;

public class SendDietaryPreference {
	Integer id;
	String name;
	
	
	
	public SendDietaryPreference(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public SendDietaryPreference() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SendDietaryPreference [id=" + id + ", name=" + name + "]";
	}
	

}
