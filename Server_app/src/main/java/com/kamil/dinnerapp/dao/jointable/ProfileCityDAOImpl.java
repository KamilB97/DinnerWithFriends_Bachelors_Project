package com.kamil.dinnerapp.dao.jointable;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.jointable.ProfileCity;

@Repository
public class ProfileCityDAOImpl {

	private EntityManager entityManager;

	@Autowired
	public ProfileCityDAOImpl(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	public ProfileCity get(Integer profileId) {
		System.out.println("id " + profileId);
		ProfileCity pc = null;
		Session currentSession = entityManager.unwrap(Session.class);
		// currentSession.delete(profileCity);
		try {
			Query<ProfileCity> theQuery = currentSession.createQuery("from ProfileCity where profile.id = : profileId",
					ProfileCity.class);
			theQuery.setParameter("profileId", profileId);
			pc = theQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}

		//System.out.println(pc.getCity().getName() + " " + pc.getProfile().getFirstName());
		return pc;

	}

	public void addRelationProfileCity(ProfileCity profileCity) {

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(profileCity);

	}

	public void deleteRelationProfileCity(ProfileCity profileCity) {
		// entityManager.merge(profileCity);
//		System.out.println("delete relation: " + profileCity.getCity().getName() + " profile: "
//				+ profileCity.getProfile().getId());
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(profileCity);
//		Query theQuery = currentSession.createQuery("delete ProfileCity where profile.id = :profileId");
//		theQuery.setParameter("profileId", profileCity.getProfile().getId());
//		System.out.println(profileCity.getProfile().getId());
//		theQuery.executeUpdate();

	}

	public void updateRelationProfileCity(ProfileCity profileCity) {
		entityManager.merge(profileCity);
		System.out.println("delete relation: " + profileCity.getCity().getName() + " profile: "
				+ profileCity.getProfile().getId());
		Session currentSession = entityManager.unwrap(Session.class);
		// currentSession.delete(profileCity);
		Query theQuery = currentSession
				.createQuery("update ProfileCity pc set pc.city.id = :cityId where pc.profile.id = : profileId");
		theQuery.setParameter("cityId", profileCity.getCity().getId());
		theQuery.setParameter("profileId", profileCity.getProfile().getId());
		theQuery.executeUpdate();

	}

}
