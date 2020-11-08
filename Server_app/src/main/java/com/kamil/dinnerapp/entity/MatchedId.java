package com.kamil.dinnerapp.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class MatchedId implements Serializable {
	
	private Integer profile1;
	private Integer profile2;
	
	public MatchedId() {
	}

	public MatchedId(Integer profile1, Integer profile2) {
		this.profile1 = profile1;
		this.profile2 = profile2;
	}

	public Integer getProfile1() {
		return profile1;
	}

	public void setProfile1(Integer profile1) {
		profile1 = profile1;
	}

	public Integer getProfile2() {
		return profile2;
	}

	public void setProfile2(Integer profile2) {
		profile2 = profile2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getProfile1(), getProfile2());
	}

	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof MatchedId)) return false;
	        MatchedId that = (MatchedId) o;
	        return Objects.equals(getProfile1(), that.getProfile1()) &&
	                Objects.equals(getProfile2(), that.getProfile2());
	    }	
}
