package com.kamil.dinnerapp.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Image;

@Repository
public class ImageDAOImpl {

	private EntityManager entityManager;

	@Autowired
	public ImageDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	public Image get(int id) {

		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Image.class, id);
	}

	public Integer save(Image image) throws Exception {
	//	System.out.println("Image::save - " + image.getId());
	//	System.out.println(image.getUrl());
		Session currentSession = entityManager.unwrap(Session.class);
		//System.out.println(image.getUrl());
		//if (image.getId() != 0) {
		//	System.out.println("image::save inside condition check: id == 0");
		//	image.setId(0);
	//	}
		try {
			System.out.println("zapisuje");
			Integer id = (Integer) currentSession.save(image);
			System.out.println("save image: " + id);
			
			return id;
		} catch (Exception e) {
			throw e;
		}
	}

	public void update(Image image) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println("Image DAO: UPDATE METHOD");
		try {
			currentSession.saveOrUpdate(image);
		} catch (Exception e) {
			throw e;
		}
	}

	public void delete(Image image) {

		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("delete from Image where id:=imageId");
		query.setParameter("imageId", image.getId());
		query.executeUpdate();

	}

}
