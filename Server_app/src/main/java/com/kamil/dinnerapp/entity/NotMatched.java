package com.kamil.dinnerapp.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "not_matched")
@IdClass(value = MatchedId.class)
public class NotMatched implements Serializable {
	
	@Id
	@JoinColumn(name = "profile1")
	private Integer profile1;
	
	@Id
	@JoinColumn(name = "profile2")
	private Integer profile2;
	
	@Column(name = "date")
	private String date;

	
	public NotMatched() {
	}
	
	public NotMatched(Integer profile1, Integer profile2, String date) {
		this.profile1 = profile1;
		this.profile2 = profile2;
		this.date = date;
	}

	public Integer getProfile1() {
		return profile1;
	}

	public void setProfile1(Integer profile1) {
		this.profile1 = profile1;
	}

	public Integer getProfile2() {
		return profile2;
	}

	public void setProfile2(Integer profile2) {
		this.profile2 = profile2;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
