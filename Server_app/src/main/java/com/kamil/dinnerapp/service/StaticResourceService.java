package com.kamil.dinnerapp.service;

import java.util.List;

public interface StaticResourceService<T, E> {
	
		T get(E id); // Or Optionall ?
		List<T> getAll();
	}
