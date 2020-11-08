package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Matched;
import com.kamil.dinnerapp.entity.MatchedId;

@Repository
public class MatchedDAOImpl implements MatchDAO<Matched> {

	private EntityManager entityManager;
	
	@Autowired
	public MatchedDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
//	@Override
//	public Matched get(MatchedPK pk) {
//		entityManager.createQuery("from Matched where profile1 = :profile1Id and profile2 = :profile2Id", Matched.class);
//		
//		return null;
//	}

	@Override
	public List<Matched> getAllForId(int profileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Matched> theQuery = currentSession.createQuery("from Matched where profile1 = :profileId or profile2 = :profileId" , Matched.class);
		theQuery.setParameter("profileId", profileId);
		
		return theQuery.getResultList();
	}
	public Matched getMatch(Integer profile1Id, Integer profile2Id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Matched> theQuery = currentSession.createQuery("from Matched where profile1 = :profile1Id and profile2 = :profile2Id" , Matched.class);
		theQuery.setParameter("profile1Id", profile1Id);
		theQuery.setParameter("profile2Id", profile2Id);
		Matched match = null;
		try {
			 match = theQuery.getSingleResult();
		} catch (NoResultException  nre) {
			// TODO: handle exception
		}
		
		return match;
	}

	@Override
	public void save(Matched match) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(match);
		
	}

	@Override
	public void delete(MatchedId pk) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Matched where "
		+" (profile1 = :profile1Id  and profile2 = :profile2Id) or (profile2 = :profile1Id  and profile1 = :profile2Id)");
		theQuery.setParameter("profile1Id", pk.getProfile1());
		theQuery.setParameter("profile2Id", pk.getProfile2());
		
		//currentSession.delete(pk);
		
		theQuery.executeUpdate();
		
		
	}
	
	

}
