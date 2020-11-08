package com.kamil.dinnerapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.GenericDao;
import com.kamil.dinnerapp.dao.UserConnectionDAOImpl;
import com.kamil.dinnerapp.entity.UserConnection;

@Service
public class UserConnectionServiceImpl implements GenericService<UserConnection, Integer> {
	
	private UserConnectionDAOImpl connectionDAO;
	
	@Autowired
	public UserConnectionServiceImpl(UserConnectionDAOImpl connectionDAO) {
		this.connectionDAO = connectionDAO;
	}

	@Override
	@Transactional
	public UserConnection get(Integer id) {
		UserConnection connection = connectionDAO.get(id);
		return connection;
	}
	@Transactional
	public UserConnection get(Integer baseProfileId, Integer targetProfileId) {
		
		if(targetProfileId > baseProfileId) {
			Integer targetHolder = targetProfileId;
			targetProfileId = baseProfileId;
			baseProfileId = targetHolder;
		}
		
		UserConnection userConnection = connectionDAO.get(baseProfileId, targetProfileId);
		
		return userConnection;
	
	}

	@Override
	@Transactional
	public List<UserConnection> getAll() {
		List<UserConnection> connections = connectionDAO.getAll();
		return connections;
	}

	@Override
	@Transactional
	public void save(UserConnection connection) {
		//System.out.println("save User CONNECTION");
		connectionDAO.save(connection);
		
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		connectionDAO.delete(id);
		
	}
	@Transactional
	public  UserConnection specialSave(Integer baseProfileId, Integer targetProfileId, Integer sumCondition) {
		
		if(targetProfileId > baseProfileId) {
			Integer targetHolder = targetProfileId;
			targetProfileId = baseProfileId;
			baseProfileId = targetHolder;
		}
		
		 UserConnection userConnection = new UserConnection(baseProfileId, targetProfileId,sumCondition);
		// System.out.println(" created userConnection: " + userConnection.getBaseProfileId() +" "+ userConnection.getTargetProfileId() + " " + userConnection.getFitCondition());
		 
		// userConnection.setFitCondition(sumCondition);
		 
		// System.out.println("SAVING USER CONNECTION");
		  
		 return connectionDAO.save(userConnection);
		 	
	}
	
	

}
