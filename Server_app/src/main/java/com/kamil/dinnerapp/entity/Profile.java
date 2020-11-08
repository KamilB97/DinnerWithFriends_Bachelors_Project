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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kamil.dinnerapp.entity.jointable.ProfileCity;
import com.kamil.dinnerapp.entity.jointable.ProfileDietary;
import com.kamil.dinnerapp.entity.jointable.ProfileInteresting;


@Entity
@Table(name="profile")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="profile_id")
	private int id;
	
	@OneToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "user")
	private User user; 					
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id")
	private Image image;
	
	@OneToMany(mappedBy = "profile")
	private List<ProfileInteresting> interestings;
	
	@OneToMany(mappedBy = "profile")
	private List<ProfileDietary> dietaryPreferences;
	
	@OneToOne(mappedBy = "profile" )
	private ProfileCity city;
		
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name="about")
	private String about;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "longitude")
	private String longitude;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name="update_date")
	private String updateDate;
	
	@Column(name="status")
	private int status;
	
	public Profile() {
		
	}

	public Profile( String firstName, String lastName, String gender, String about,
			String updateDate, int status) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.about = about;
		this.updateDate = updateDate;
		this.status = status;
	}

	
	

	

//	public List<UserConnection> getUserConnections() {
//		return userConnections;
//	}
//
//	public void setUserConnections(List<UserConnection> userConnections) {
//		this.userConnections = userConnections;
//	}

	

	
	public List<ProfileInteresting> getProfileInterestings() {
		return interestings;
	}
	
	
	public void setProfileInterestings(List<ProfileInteresting> interesting) {
		this.interestings = interesting;
	}

	public List<ProfileDietary> getProfileDietaryPreferences() {
		return dietaryPreferences;
	}

	public void setProfileDietaryPreferences(List<ProfileDietary> dietaryPreferences) {
		this.dietaryPreferences = dietaryPreferences;
	}

	public ProfileCity getCity() {
		return city;
	}

	public void setCity(ProfileCity city) {
		this.city = city;
		
	}

//	public MeetingLocation getMeetingLocation() {
//		return meetingLocation;
//	}
//
//	public void setMeetingLocation(MeetingLocation meetingLocation) {
//		this.meetingLocation = meetingLocation;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		user.setProfile(this);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", user=" + user + ", image=" + image.getId() + " url: " + image.getUrl() + ", city=" + city.toString()
			 + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", about=" + about + ", updateDate=" + updateDate + ", status=" + status + "]";
	}
	
	
	

}
