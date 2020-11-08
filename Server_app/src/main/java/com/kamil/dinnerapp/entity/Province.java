package com.kamil.dinnerapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "province")
public class Province {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "province_id")
	private int id;
	@Column(name = "province_name")
	private String name;
	@ManyToOne
	@JoinColumn(name = "country_id")
	@JsonManagedReference(value = "provinces")
	private Country country;
	
	@OneToMany(mappedBy = "province", cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
					,fetch = FetchType.LAZY)
	@JsonBackReference(value = "cities")
	//@JsonManagedReference(value = "province")
	private List<City> cities;
	
	public Province() {
		
	}

	public Province(String name) {
		this.name = name;
		this.country = country;
	}
	
	private void addCity(City city) {
		if(cities == null) {
			cities = new ArrayList<City>();
		}
		cities.add(city);
		city.setProvince(this);
	}
	
	

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
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


	@Override
	public String toString() {
		return "Province [id=" + id + ", name=" + name + ", country=" + country + ", cities=" + cities + "]";
	}

	
	
	
	
	
}
