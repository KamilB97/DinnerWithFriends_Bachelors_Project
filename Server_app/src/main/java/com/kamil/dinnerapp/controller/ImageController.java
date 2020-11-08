package com.kamil.dinnerapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.dinnerapp.entity.Image;
import com.kamil.dinnerapp.entity.send.SendFriend;
import com.kamil.dinnerapp.entity.send.SendImage;
import com.kamil.dinnerapp.service.ImageServiceImpl;

@RestController
@RequestMapping("/api")
public class ImageController {
	
	private ImageServiceImpl imageService;
	
	@Autowired
	public ImageController(ImageServiceImpl imageService) {
	this.imageService = imageService;
	}
	
	@GetMapping("/images/{imageId}") // działa
	public Image getImage(@PathVariable Integer imageId){
		
		return imageService.get(imageId);
			
	}
	
	@PostMapping("/images") //działa
	public Map<String, Integer> saveImage(@RequestBody SendImage image) throws Exception{
		System.out.println("image: " + image.getProfileId() + " url: " + image.getImageUrl());
		
		Integer imageId = imageService.save(image);
		
		Map<String, Integer> map = new HashMap();
		map.put("imageId", imageId);
		return map;
			
	}
	@PutMapping("/images") //działa
	public Map<String, Integer> updateImage(@RequestBody SendImage image) throws Exception{
		
		Integer imageId = imageService.update(image);
		
		Map<String, Integer> map = new HashMap();
		map.put("imageId", imageId);
		return map;
		
	}
	
}
