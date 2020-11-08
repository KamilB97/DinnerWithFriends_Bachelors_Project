package com.kamil.dinnerapp.dao;

import java.util.List;

public interface StaticResourceDAO<T, E> {
	
	T get(E id); // Or Optionall ?
	List<T> getAll();
	
}