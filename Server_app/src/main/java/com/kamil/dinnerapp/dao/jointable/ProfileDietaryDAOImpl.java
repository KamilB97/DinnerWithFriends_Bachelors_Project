package com.kamil.dinnerapp.dao.jointable;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.jointable.ProfileDietary;
import com.kamil.dinnerapp.entity.jointable.ProfileInteresting;

@Repository
public class ProfileDietaryDAOImpl {

	private EntityManager entityManager;

	@Autowired
	public ProfileDietaryDAOImpl(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	public void addRelationProfileDietary(ProfileDietary profileDietary) {

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(profileDietary);

	}
	
	public void deleteRelationsByProfileId(Integer profileId) {
		System.out.println("usuwanie relacji dietary-profile dla: " + profileId);
		Session currentSession = entityManager.unwrap(Session.class);
		Query<ProfileInteresting> theQuery = currentSession.createQuery("delete from ProfileDietary pd where pd.profile.id = :profileId");
		theQuery.setParameter("profileId", profileId);
		theQuery.executeUpdate();
	}

	public Integer getSimilarDietaryCondition(int baseProfileId, int targetProfileId) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Long> querryCountCondition = currentSession
				.createQuery("SELECT count(*) from ProfileDietary pd where (pd.profile.id = : targetProfileId "
						+ "and pd.diet.id IN (SELECT dp.diet.id from ProfileDietary dp where dp.profile.id = :baseProfileId))");
		querryCountCondition.setParameter("targetProfileId", targetProfileId);
		querryCountCondition.setParameter("baseProfileId", baseProfileId);

		Long dietaryCondition = querryCountCondition.getSingleResult();
		Integer intDietaryCondition = dietaryCondition != null ? dietaryCondition.intValue() : null;
		//System.out.println("interestings condition: " + intDietaryCondition);

		return intDietaryCondition;

	}

}
