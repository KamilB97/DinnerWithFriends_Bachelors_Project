package com.kamil.dinnerapp.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Profile;


public interface ProfileDAO extends GenericDao<Profile,Integer> {

	public void update(Profile profile);
	
	// add Profile methods

	
	
	

}
