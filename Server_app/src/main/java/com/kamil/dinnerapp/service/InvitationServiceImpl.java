package com.kamil.dinnerapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.InvitationDAOImpl;
import com.kamil.dinnerapp.entity.Invitation;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.send.SendInvitation;
import com.kamil.dinnerapp.entity.send.SendProfileBrief;

@Service
public class InvitationServiceImpl {
	
	private InvitationDAOImpl invitationDAO;
	private ProfileServiceImpl profileService;
	
	@Autowired
	public InvitationServiceImpl(
			InvitationDAOImpl invitationDAO,
			ProfileServiceImpl profileService) {
		this.invitationDAO = invitationDAO;
		this.profileService = profileService;
	}
	
	@Transactional
	public Invitation get(Integer id) {
		Invitation invitation = invitationDAO.get(id);
		return invitation;
	}
	
	@Transactional
	public List<SendInvitation> getAllInvitationsForProfile(Integer id) {
		
		List<Invitation> invitationList = invitationDAO.getAllInvitationsForProfile(id);
		List<SendInvitation> sendInvitationList= new ArrayList<SendInvitation>();
		for(Invitation invitation : invitationList) {
			
			SendInvitation sendInvitation = new SendInvitation();
			sendInvitation.setInvitationId(invitation.getId());
			SendProfileBrief profileBrief = new SendProfileBrief();
			profileBrief.setName(invitation.getSender().getFirstName());
			profileBrief.setSurname(invitation.getSender().getLastName());
			profileBrief.setProfileId(invitation.getSender().getId());
			if(invitation.getSender().getImage() != null) {
			profileBrief.setImage(invitation.getSender().getImage().getUrl());
			}
			sendInvitation.setProfile(profileBrief);
			
			sendInvitationList.add(sendInvitation);
			
		}
		return sendInvitationList;
		
	}

	@Transactional
	public void save(Invitation invitation) {

		invitationDAO.save(invitation);
		
	}
	
	@Transactional
	public void save(Integer senderId, Integer receiverId) {
		
		Invitation invitation = new Invitation();
		Profile sender = profileService.get(senderId);
		Profile receiver = profileService.get(receiverId);
		
		invitation.setSender(sender);
		invitation.setReceiver(receiver);

		invitationDAO.save(invitation);
		
	}
	
	@Transactional
	public void delete(Integer profile1Id, Integer profile2Id) {
		invitationDAO.delete(profile1Id, profile2Id);
	}
	@Transactional
	public void delete(Integer invitationId) {
		
		invitationDAO.delete(invitationId);
	}
	
	@Transactional
	public void delete(Invitation invitation) {
		
		invitationDAO.delete(invitation);
		
	}
}
