package com.kamil.dinnerapp.entity;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class UserConnectionId implements Serializable{
	
	private Integer baseProfileId;
	private Integer targetProfileId;
	
	
	public UserConnectionId() {
		// TODO Auto-generated constructor stub
	}
	
	public UserConnectionId(Integer baseProfileId, Integer targetProfileId) {
		this.baseProfileId = baseProfileId;
		this.targetProfileId = targetProfileId;
	}
	public Integer getBaseProfileId() {
		return baseProfileId;
	}
	public void setBaseProfileId(Integer baseProfileId) {
		this.baseProfileId = baseProfileId;
	}
	public Integer getTargetProfileId() {
		return targetProfileId;
	}
	public void setTargetProfileId(Integer targetProfileId) {
		this.targetProfileId = targetProfileId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getBaseProfileId(), getTargetProfileId());
	}

	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof UserConnectionId)) return false;
	        UserConnectionId that = (UserConnectionId) o;
	        return Objects.equals(getBaseProfileId(), that.getBaseProfileId()) &&
	                Objects.equals(getTargetProfileId(), that.getTargetProfileId());
	    }
	
	

}
