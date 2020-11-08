package com.kamil.dinnerapp.dao.jointable;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.jointable.ProfileInteresting;

@Repository
public class ProfileInterestingDAOImpl {
	
	
	private EntityManager entityManager;
	
	@Autowired
	public ProfileInterestingDAOImpl(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}
	
	public void addRelationProfileInteresting(ProfileInteresting profileInteresting) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(profileInteresting);
			
	}
	
	//  ZROB DELETE KTORY USUWA WSZYSTKIE WPISY DLA DANAGEO PROFILU, TAK SAMO INTERESTINGS
	
	public void deleteRelationsByProfileId(Integer profileId) {
		System.out.println("usuwanie relacji interestings-profile dla: " + profileId);
		Session currentSession = entityManager.unwrap(Session.class);
		Query<ProfileInteresting> theQuery = currentSession.createQuery("delete from ProfileInteresting pi where pi.profile.id = :profileId");
		theQuery.setParameter("profileId", profileId);
		theQuery.executeUpdate();
		
		
	}
	
	public void deleteRelationProfileInteresting(ProfileInteresting profileInteresting) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(profileInteresting);
		//currentSession.saveOrUpdate(profileInteresting);
			
	}
	
	
	public Integer getSimilarInterestingsCondition(int baseProfileId, int targetProfileId){
		Session currentSession = entityManager.unwrap(Session.class);
		
	Query<Long> querryCountCondition = currentSession.createQuery("SELECT count(*) from ProfileInteresting pi where (pi.profile.id = : targetProfileId "
			+ "and pi.interesting.id IN (SELECT ip.interesting.id from ProfileInteresting ip where ip.profile.id = :baseProfileId))");
	querryCountCondition.setParameter("targetProfileId", targetProfileId);
	querryCountCondition.setParameter("baseProfileId", baseProfileId);
	
	Long interestingCondition = querryCountCondition.getSingleResult();
	Integer intInterestingCondition = interestingCondition != null ? interestingCondition.intValue() : null;
	//System.out.println("interestings condition: "+ interestingCondition);
	
	return intInterestingCondition;
		
	}
	

}
