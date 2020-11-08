package com.kamil.dinnerapp.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T, E> {
	
	T get(E id); // Or Optionall ?
	List<T> getAll();
	void save(T t); // czy coś zwracać 
	void delete(E id);

}
