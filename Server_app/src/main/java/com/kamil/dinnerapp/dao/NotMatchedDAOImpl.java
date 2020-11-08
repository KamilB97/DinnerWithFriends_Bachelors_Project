package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Matched;
import com.kamil.dinnerapp.entity.MatchedId;
import com.kamil.dinnerapp.entity.NotMatched;

@Repository
public class NotMatchedDAOImpl implements MatchDAO<NotMatched> {
private EntityManager entityManager;
	
	@Autowired
	public NotMatchedDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public NotMatched getNotMatched(Integer profile1Id, Integer profile2Id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query<NotMatched> theQuery = currentSession.createQuery("from NotMatched n where n.profile1 = :profile1Id and n.profile2 = :profile2Id", NotMatched.class);
		theQuery.setParameter("profile1Id", profile1Id);
		theQuery.setParameter("profile2Id", profile2Id);
		
		NotMatched notMatched = theQuery.getSingleResult();

		return notMatched;
}
	
	
	@Override
	public List<NotMatched> getAllForId(int profileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<NotMatched> theQuery = currentSession.createQuery("from NotMatched where profile1 = :profileId or profile2 = :profileId", NotMatched.class);
		theQuery.setParameter("profileId", profileId);
		
		return theQuery.getResultList();
	}

	@Override
	public void save(NotMatched match) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(match);
		
	}

	@Override
	public void delete(MatchedId pk) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from NotMatched where "
		+" (profile1 = :profile1Id  and profile2 = :profile2Id) or (profile2 = :profile1Id  and profile1 = :profile2Id)");
		theQuery.setParameter("profile1Id", pk.getProfile1());
		theQuery.setParameter("profile2Id", pk.getProfile2());
		
		//currentSession.delete(pk);
		
		theQuery.executeUpdate();
		
		
	}
	
	

}
