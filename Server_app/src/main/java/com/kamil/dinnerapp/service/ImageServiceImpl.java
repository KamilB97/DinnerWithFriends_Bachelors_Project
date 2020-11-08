package com.kamil.dinnerapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.ImageDAOImpl;
import com.kamil.dinnerapp.entity.Image;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.send.SendImage;

@Service
public class ImageServiceImpl  {

	private ImageDAOImpl imageDAO;
	private ProfileServiceImpl profileService;
	
	
	@Autowired
	public ImageServiceImpl(ImageDAOImpl imageDAO,
			ProfileServiceImpl profileService) {
		this.imageDAO = imageDAO;
		this.profileService = profileService;
	}
	@Transactional
	public Image get(Integer id) {
		Image image = imageDAO.get(id);
		return image;
	}

	@Transactional
	public Integer save(SendImage sendImage) throws Exception {
		
		Image plaintImage = new Image(sendImage.getImageUrl());
		Integer profileId = sendImage.getProfileId();
		Profile profile = profileService.get(profileId);
		
		Integer imageId = imageDAO.save(plaintImage);
		Image image = this.get(imageId);
		//System.out.println("FEEDBACK: IMAGE SERVICE IMG ID: " + image.getId() );
	//	System.out.println(image.getUrl());
		profile.setImage(image);
		//System.out.println("Profile:");
		//System.out.println(profile.toString());
		
		profileService.update(profile);
		
		return image.getId();
		
	}
	@Transactional
	public void delete(Image image) {
		imageDAO.delete(image);
		
	}
	@Transactional
	public Integer update(SendImage image) throws Exception {
		//Image plaintImage = new Image(image.getImageUrl());
		Integer profileId = image.getProfileId();
		Profile profile = profileService.get(profileId);
		
		Image profileImage = profile.getImage();
		if( profileImage == null) {
			System.out.println("brak zdjęcia dodaję zdjęcie:");
		return this.save(image);
			
		}
			System.out.println("jest zdjęcie, updatuje zdjęcie");
		profileImage.setUrl(image.getImageUrl());
		//plaintImage.setId(imageId);
		
		imageDAO.update(profileImage);
		return profileImage.getId();
		
	}
	
	

}
