package com.kamil.dinnerapp.entity.send;

public class SendInteresting {
	String name;
	Integer id;
	
	public SendInteresting() {
		// TODO Auto-generated constructor stub
	}
	
	
	public SendInteresting(Integer id,String name) {
		this.name = name;
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "SendInteresting [name=" + name + ", id=" + id + "]";
	}
	
	

}
