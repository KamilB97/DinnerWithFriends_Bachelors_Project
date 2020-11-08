package com.kamil.dinnerapp.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "UserConnection")
@Table(name = "connection")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@IdClass(value = UserConnectionId.class)
public class UserConnection implements Serializable{
	
//	@EmbeddedId
//	private UserConnectionPK pk ;
	
	@Id
	@JoinColumn(name = "base_profile_id")
	private Integer baseProfileId;
	
	@Id
	@JoinColumn(name = "target_profile_id")
	private Integer targetProfileId;

	
	@Column(name = "fit_condition")
	private int fitCondition;
	
	@OneToMany(mappedBy = "userConnection")
	@JsonBackReference(value = "userConnection")
	private List<Swipe> swipes;
	
	
	public UserConnection() {
	}
	
	public UserConnection(Integer baseProfileId, Integer targetProfileId, Integer fitConditionId) {
		
		this.baseProfileId = baseProfileId;
		this.targetProfileId = targetProfileId;
		this.fitCondition = fitConditionId;
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

	public List<Swipe> getSwipes() {
		return swipes;
	}

	public void setSwipes(List<Swipe> swipes) {
		this.swipes = swipes;
	}

	public int getFitCondition() {
		return fitCondition;
	}



	public void setFitCondition(int fitCondition) {
		this.fitCondition = fitCondition;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getBaseProfileId(), getTargetProfileId());
	}

	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof UserConnection)) return false;
	        UserConnection that = (UserConnection) o;
	        return Objects.equals(getBaseProfileId(), that.getBaseProfileId()) &&
	                Objects.equals(getTargetProfileId(), that.getTargetProfileId());

	
	
	
	
	
	 }

}
