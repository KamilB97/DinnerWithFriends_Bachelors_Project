package com.kamil.dinnerapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "country")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private int id;
	
	@Column(name = "country_name")
	private String name;
	
	@OneToMany(mappedBy = "country",fetch = FetchType.LAZY)
	@JsonBackReference("provinces")
	private List<Province> provinces;
	
	public void addProvince(Province province) {
		if(provinces == null) {
			provinces = new ArrayList<Province>();
		}
		provinces.add(province);
	}
	
public Country(String name) {
	this.name = name;
		
	}
public Country() {
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<Province> getProvinces() {
	return provinces;
}

public void setProvinces(List<Province> provinces) {
	this.provinces = provinces;
}

	

}
