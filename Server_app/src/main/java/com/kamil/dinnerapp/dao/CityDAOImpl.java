package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.City;
import com.kamil.dinnerapp.entity.Interesting;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.UserConnection;
import com.kamil.dinnerapp.entity.jointable.ProfileCity;

@Repository
public class CityDAOImpl implements StaticResourceDAO<City, Integer> {

	private EntityManager entityManager;

	@Autowired
	public CityDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public City get(Integer cityId) {
		System.out.println("w city dao: cityid= " + cityId);
		Session currentSession = entityManager.unwrap(Session.class);
		Query<City> theQuery = currentSession.createQuery("from City c where c.id = :cityId ", City.class);
		theQuery.setParameter("cityId", cityId);
		City city = theQuery.getSingleResult();
		return city;
	}

	public List<City> getAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<City> theQuery = currentSession.createQuery("from City", City.class);
		List<City> cities = theQuery.getResultList();
		return cities;
	}

	public List<Integer> getAllCityId() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Integer> theQuery = currentSession.createQuery("SELECT c.id from City c");
		List<Integer> cityIdList = theQuery.getResultList();
		return cityIdList;
	}
	public List<City> getCitiesForProvince(Integer provinceId){
		Session currentSession = entityManager.unwrap(Session.class);
		Query<City> theQuery = currentSession.createQuery("from City c where c.province.id = :provinceId", City.class);
		theQuery.setParameter("provinceId", provinceId);
		List<City> cities = theQuery.getResultList();
		return cities;
	}

	public List<Integer> getProfilesForCity(int cityId, int profileId) {
		Session currentSession = entityManager.unwrap(Session.class);

		// pobranie listy profili które mieszkaja w tym samym mieście
		Query<Integer> query = currentSession.createQuery(

				"SELECT pc.profile.id " + "from ProfileCity pc " + "WHERE pc.city.id = :cityId"
						+ " and pc.profile.id != :profileId",
				Integer.class);

		query.setParameter("cityId", cityId);
		query.setParameter("profileId", profileId);
		List<Integer> profiles = query.getResultList();
	
		return profiles;
	}

	public List<Integer> getProfilesForCity(int cityId) {
		Session currentSession = entityManager.unwrap(Session.class);

		// pobranie listy profili które mieszkaja w tym samym mieście
		Query<Integer> query = currentSession.createQuery(

				"SELECT pc.profile.id " + "from ProfileCity pc " + "WHERE pc.city.id = :cityId", Integer.class);

		query.setParameter("cityId", cityId);
		List<Integer> profiles = query.getResultList();
		
		return profiles;

	}

	public Integer getCityIdByName(String cityName) {

		Session currentSession = entityManager.unwrap(Session.class);
		Query<Integer> query = currentSession.createQuery("SELECT c.id from City c where c.name = :cityName",
				Integer.class);
		query.setParameter("cityName", cityName);
		Integer cityId = query.getSingleResult();
		return cityId;

	}

}
