package com.kamil.dinnerapp.dao;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Swipe;
import com.kamil.dinnerapp.entity.UserConnection;

@Repository
public class SwipeDAOImpl implements GenericDao<Swipe, Integer> {

	private EntityManager entityManager;

	public SwipeDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Swipe get(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);

		return currentSession.get(Swipe.class, id);
	}

	@Override
	public List<Swipe> getAll() {
		Session currentSession = entityManager.unwrap(Session.class);

		return currentSession.createQuery("from Swipe", Swipe.class).getResultList();
	}

	@Override
	public void save(Swipe swipe) {
		Session currentSession = entityManager.unwrap(Session.class);

		if (swipe.getId() != 0) {
			swipe.setId(0);
		}
		try {
			currentSession.saveOrUpdate(swipe);
			System.out.println("Dodano swipe");
		} catch (Exception e) {
			System.out.println("nie dodano swipe");
			e.printStackTrace();
		}
		

	}

	public void update(Swipe swipe) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(swipe);
	}

	@Override
	public void delete(Integer swipeId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery(
				"delete from Swipe where id = :swipeId");
		
		theQuery.setParameter("swipeId", swipeId);
		theQuery.executeUpdate();

	}
	
	public void delete(Swipe swipe) {
		Session currentSession = entityManager.unwrap(Session.class);
		
	currentSession.delete(swipe);
		
		

	}

	// @@@@@@@@@@@@@@22 moze byc blad
	public void deleteBothSwipes(Integer baseProfileId, Integer targetProfileId) {

		System.out.println(" w DELETE BOTH SWIPES ");

		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery(
				"delete from Swipe s where s.userConnection.baseProfileId = :bProfileId and s.userConnection.targetProfileId = :tProfileId");
		
		theQuery.setParameter("bProfileId", baseProfileId);
		theQuery.setParameter("tProfileId", targetProfileId);
		theQuery.executeUpdate();
	}

	public Swipe get(Integer baseProfileId, Integer targetProfileId, Integer searchingProfileId) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Swipe> theQuery = currentSession
				.createQuery("from Swipe s where s.userConnection.baseProfileId = :bProfileId "
						+ "and s.userConnection.targetProfileId =: tProfileId and profile.id =: searchingProfileId");
		theQuery.setParameter("bProfileId", baseProfileId);
		theQuery.setParameter("tProfileId", targetProfileId);
		theQuery.setParameter("searchingProfileId", searchingProfileId);
		Swipe swipe = theQuery.getSingleResult();
		return swipe;
	}

	public List<Swipe> get(Integer baseProfileId, Integer targetProfileId) { //Integer requestingProfileId
		Session currentSession = entityManager.unwrap(Session.class);
		List<Swipe> swipe = null;
			try {
				Query<Swipe> theQuery = currentSession
						.createQuery("from Swipe s where s.userConnection.baseProfileId = :bProfileId "
								+ "and s.userConnection.targetProfileId =: tProfileId");
				theQuery.setParameter("bProfileId", baseProfileId);
				theQuery.setParameter("tProfileId", targetProfileId);
				swipe = theQuery.getResultList();
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		return swipe;
	}
	
	public List<Swipe> getFriendCandidatesList(Integer searchingProfileId) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Swipe> theQuery = currentSession
				.createQuery("from Swipe s where (s.liked != 2 and s.profile.id =: searchingProfileId) order by s.userConnection.fitCondition desc", Swipe.class);
		
		theQuery.setParameter("searchingProfileId", searchingProfileId);
		int number = (int)(Math.random() * (10 - 1)) +1; 
		int firstResult = number *5;
		theQuery.setFirstResult(firstResult);
		theQuery.setMaxResults(5);
		List<Swipe> swipe = theQuery.getResultList();
		return swipe;
	}
	
	
	
	
}
