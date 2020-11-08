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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kamil.dinnerapp.entity.jointable.ProfileCity;

@Entity
@Table(name = "city")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private int id;
	@Column(name = "city_name")
	private String name;
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	//@JsonBackReference(value = "province")
	@JoinColumn(name = "province_id")
	@JsonBackReference(value = "cities")	

	private Province province;
	

	@OneToMany(mappedBy = "city")
	@JsonIgnore
	private List<ProfileCity> profiles;
	
	
	public City() {
		
	}
	public City(String name) {
		this.name = name;
	}
	

	public City(String name, Province province) {
	//	super();
		this.name = name;
		this.province = province;
	}

	
	
	public List<ProfileCity> getProfiles() {
		return profiles;
	}


	public void setProfiles(List<ProfileCity> profiles) {
		this.profiles = profiles;
	}

	public Province getProvince() {
		return province;
	}


	public void setProvince(Province province) {
		this.province = province;
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
		return "City [id=" + id + ", name=" + name + ", province=" + province + "]";
	}

	
	

}
