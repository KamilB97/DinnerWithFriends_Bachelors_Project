package com.kamil.dinnerapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.ConversationParticipantDAOImpl;
import com.kamil.dinnerapp.entity.Conversation;
import com.kamil.dinnerapp.entity.ConversationParticipant;
import com.kamil.dinnerapp.entity.DietaryPreference;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.jointable.ProfileDietary;
import com.kamil.dinnerapp.entity.send.SendMarkerCordsAndDiet;
import com.kamil.dinnerapp.entity.send.SendParticipant;
import com.kamil.dinnerapp.entity.send.SendParticipantsLongLat;
import com.kamil.dinnerapp.entity.send.SendProfileBrief;

@Service
public class ConversationParticipantServiceImpl {

	private ConversationParticipantDAOImpl conversationParticipanDAO;
	private DietaryPreferenceServiceImpl dietaryPreferenceService;

	@Autowired
	public ConversationParticipantServiceImpl(ConversationParticipantDAOImpl conversationParticipanDAO,
			DietaryPreferenceServiceImpl dietaryPreferenceService) {
		this.conversationParticipanDAO = conversationParticipanDAO;
		this.dietaryPreferenceService = dietaryPreferenceService;
	
	}

	@Transactional
	public ConversationParticipant get(Integer id) {

		ConversationParticipant conversationParticipant = conversationParticipanDAO.get(id);
		return conversationParticipant;
	}

	@Transactional /// !!!!!!!!!!!!!!!!!!!!!!!! Integer conversationId, Integer profileId
	public void save(ConversationParticipant conversationParticipant) {

		conversationParticipanDAO.save(conversationParticipant);

	}

	@Transactional
	public void update(ConversationParticipant conversationParticipnat) {
		conversationParticipanDAO.update(conversationParticipnat);

	}

	@Transactional
	public void delete(Integer id) {
		conversationParticipanDAO.delete(id);

	}

	@Transactional
	public List<SendProfileBrief> getParticipantsForConversation(Integer conversationId) {
		List<SendProfileBrief> participantsList = new ArrayList<SendProfileBrief>();

		List<Profile> profilesList = conversationParticipanDAO.getParticipantsForConversation(conversationId);
		for (Profile profile : profilesList) {

			SendProfileBrief profileBrief = new SendProfileBrief(profile.getId(), profile.getFirstName(),
					profile.getLastName());
			participantsList.add(profileBrief);
		}
		return participantsList;

	}
	@Transactional
	public List<SendProfileBrief> getParticipantsWithImgForConversation(Integer conversationId) {
		List<SendProfileBrief> participantsList = new ArrayList<SendProfileBrief>();

		List<Profile> profilesList = conversationParticipanDAO.getParticipantsForConversation(conversationId);
		for (Profile profile : profilesList) {

			SendProfileBrief profileBrief = new SendProfileBrief(profile.getId(), profile.getFirstName(),
					profile.getLastName());
			profileBrief.setImage(profile.getImage().getUrl());
			participantsList.add(profileBrief);
		}
		return participantsList;

	}
	@Transactional
	public ConversationParticipant getParticipantForConversation(Integer conversationId, Integer profileId) {
		
		ConversationParticipant participant = conversationParticipanDAO.getParticipantForConversation(conversationId,profileId);
		
		return participant;

	}

	@Transactional
	public SendMarkerCordsAndDiet getParticipantsLongLatDiet(Integer conversationId) {
		List<SendParticipantsLongLat> participantsList = new ArrayList<SendParticipantsLongLat>();

		List<Profile> profilesList = conversationParticipanDAO.getParticipantsForConversation(conversationId);
		List<Integer> dietaryIdList = new ArrayList<Integer>();
		for (Profile profile : profilesList) {

			SendParticipantsLongLat participant = castProfileToSendParticipants(profile);
			System.out.println(participant);

//			List<ProfileDietary> dietaryPreferences = profile.getProfileDietaryPreferences();
//			for (ProfileDietary profileDIetary : dietaryPreferences) {
//				dietaryIdList.add(profileDIetary.getDiet().getId());
//				System.out.println("dodawanie do listy: " + profileDIetary.getDiet().getId());
//			}
//			System.out.println("dietaryList: " + dietaryIdList.toString());

			participantsList.add(participant);
		}
		ArrayList<Profile> profilesWithHighestLenghtBetween = new ArrayList<Profile>();
		ArrayList<Profile> profilesWithSecondHighestLenghtBetween = new ArrayList<Profile>();
		int counter = 0;

		double highestLenght = 0.0;
		double secondHighestLenght = 0.0;

		for (int i = 0; i < profilesList.size() - 1; i++) {

			for (int j = 1 + counter; j < profilesList.size(); j++) {
				System.out.println("j: " + j + " i: " + i);

				double x2 = Double.parseDouble(profilesList.get(j).getLatitude());
				double y2 = Double.parseDouble(profilesList.get(j).getLongitude());
				double x1 = Double.parseDouble(profilesList.get(i).getLatitude());
				double y1 = Double.parseDouble(profilesList.get(i).getLatitude());

				Double vectorLenght = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

				if (highestLenght == 0.0) {
					highestLenght = vectorLenght;

					profilesWithHighestLenghtBetween.add(profilesList.get(j));
					profilesWithHighestLenghtBetween.add(profilesList.get(i));

					profilesWithSecondHighestLenghtBetween.add(null);
					profilesWithSecondHighestLenghtBetween.add(null);
				}

				else if (highestLenght != 0 && highestLenght <= vectorLenght) {

					secondHighestLenght = highestLenght;
					highestLenght = vectorLenght;

					profilesWithSecondHighestLenghtBetween.set(0, profilesWithHighestLenghtBetween.get(0));
					profilesWithSecondHighestLenghtBetween.set(1, profilesWithHighestLenghtBetween.get(1));

					profilesWithHighestLenghtBetween.set(0, profilesList.get(j));
					profilesWithHighestLenghtBetween.set(1, profilesList.get(i));

				}

			}
			counter++;

		}

		SendMarkerCordsAndDiet markerCords = new SendMarkerCordsAndDiet();

		List<SendParticipantsLongLat> participantsToReturn = new ArrayList<SendParticipantsLongLat>();
		
		if (profilesWithHighestLenghtBetween.get(0) != null) { // if (profilesWithHighestLenghtBetween != null) {
			System.out.println("2 participantów");

			Profile profile1 = profilesWithHighestLenghtBetween.get(0);
			double p1Latitude = Double.parseDouble(profile1.getLatitude());
			double p1Longitude = Double.parseDouble(profile1.getLongitude());

			Profile profile2 = profilesWithHighestLenghtBetween.get(1);
			double p2Latitude = Double.parseDouble(profile2.getLatitude());
			double p2Longitude = Double.parseDouble(profile2.getLongitude());
			double middleLatitudeP1P2 = (p1Latitude + p2Latitude) / 2;
			double middleLongitudeP1P2 = (p1Longitude + p2Longitude) / 2;
			System.out.println("1stmiddle coords: lat/lon: " + middleLatitudeP1P2 + "/" + middleLongitudeP1P2);

			markerCords.setLatitude(Double.toString(middleLatitudeP1P2));
			markerCords.setLongitude(Double.toString(middleLongitudeP1P2));

			if (profilesWithSecondHighestLenghtBetween.get(0) != null) {

				System.out.println("więcej niż 2 participantów");

				Profile profile3 = profilesWithSecondHighestLenghtBetween.get(0);
				double p3Latitude = Double.parseDouble(profile1.getLatitude());
				double p3Longitude = Double.parseDouble(profile1.getLongitude());

				Profile profile4 = profilesWithSecondHighestLenghtBetween.get(1);
				double p4Latitude = Double.parseDouble(profile2.getLatitude());
				double p4Longitude = Double.parseDouble(profile2.getLongitude());
				double middleLatitudeP3P4 = (p3Latitude + p4Latitude) / 2;
				double middleLongitudeP3P4 = (p3Longitude + p4Longitude) / 2;
				System.out.println("2nd middle coords: lat/lon: " + middleLatitudeP3P4 + "/" + middleLongitudeP3P4);

				double latitudeBeteenMiddlePoints = (middleLatitudeP1P2 + middleLatitudeP3P4) / 2;
				double longitudeBeteenMiddlePoints = (middleLongitudeP1P2 + middleLongitudeP3P4) / 2;

				markerCords.setLatitude(Double.toString(latitudeBeteenMiddlePoints));
				markerCords.setLongitude(Double.toString(longitudeBeteenMiddlePoints));

			}

		}
		Integer mostLikedDietId = getMostOfftenOccuringDietId(profilesList);
		DietaryPreference diet = dietaryPreferenceService.get(mostLikedDietId);
		
		markerCords.setDietaryName(diet.getName());
		
		

		return markerCords;

	}

	@Transactional
	public void deleteByProfileId(Integer conversationId, Integer profileId) {
		conversationParticipanDAO.deleteByProfileId(conversationId, profileId);

	}

	
	private Integer getMostOfftenOccuringDietId(List<Profile> profilesList) {

		ArrayList<Integer> preferencesArray = getProfilesPreferencesIds(profilesList);

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : preferencesArray) {
			Integer count = map.get(i);
			map.put(i, count != null ? count + 1 : 0);
		}
		
		Map.Entry<Integer, Integer> maxEntry = null;
		//Map.Entry<Integer, Integer> beforeMaxEntry = null;

		for (Map.Entry<Integer, Integer> entry : map.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) >= 0)
		    {
		    //	beforeMaxEntry = maxEntry;
		        maxEntry = entry;
		    }
		}
		System.out.println("maxEntry: " + maxEntry.getKey() + " val: " + maxEntry.getValue());
		
		return maxEntry.getKey();
	}

	private ArrayList<Integer> getProfilesPreferencesIds(List<Profile> profilesList) {

		ArrayList<Integer> array = new ArrayList<Integer>();
		for (Profile profile : profilesList) {

			List<ProfileDietary> dietaryPreferences = profile.getProfileDietaryPreferences();

			for (ProfileDietary profileDIetary : dietaryPreferences) {

				array.add(profileDIetary.getDiet().getId());
				System.out.println("dodawanie do listy: " + profileDIetary.getDiet().getId());

			}

		}
		System.out.println("dietaryList: " + array.toString());

		return array;
	}
	
	private SendParticipantsLongLat castProfileToSendParticipants(Profile profile) {

		SendParticipantsLongLat participant = new SendParticipantsLongLat();
		participant.setName(profile.getFirstName());
		participant.setSurname(profile.getLastName());
		participant.setProfileId(profile.getId());
		participant.setLatitude(profile.getLatitude());
		participant.setLongitude(profile.getLongitude());

		return participant;
	}

}
