package com.kamil.dinnerapp.service;

import java.util.List;

public interface GenericService<T,E> {
	T get(E id); // Or Optionall ?
	List<T> getAll();
	void save(T t); // czy coś zwracać 
	void delete(E id);

}
