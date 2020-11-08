package com.kamil.dinnerapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.kamil.dinnerapp.entity.Conversation;
import com.kamil.dinnerapp.entity.ConversationParticipant;
import com.kamil.dinnerapp.entity.Matched;
import com.kamil.dinnerapp.entity.NotMatched;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.Swipe;
import com.kamil.dinnerapp.entity.User;
import com.kamil.dinnerapp.entity.UserConnection;
import com.kamil.dinnerapp.service.jointable.ProfileDietaryServiceImpl;
import com.kamil.dinnerapp.service.jointable.ProfileInterestingServiceImpl;

@Service
public class MatchingServiceImpl {

	private InterestingServiceImpl interestingsService;
	private DietaryPreferenceServiceImpl dietaryService;
	private ProfileServiceImpl profileService;
	private CityServiceImpl cityService;
	private UserConnectionServiceImpl userConnectionService;
	private SwipeServiceImpl swipeService;
	private MatchedServiceImpl matchedService;
	private NotMatchedServiceImpl notMatchedService;
	private ConversationServiceImpl conversationService;
	private ConversationParticipantServiceImpl conversationParticipantsService;
	private ProfileDietaryServiceImpl profileDietaryService;
	private ProfileInterestingServiceImpl profileInterestingService;

	@Autowired
	public MatchingServiceImpl(InterestingServiceImpl interestingsService, DietaryPreferenceServiceImpl dietaryService,
			ProfileServiceImpl profileService, CityServiceImpl cityService,
			UserConnectionServiceImpl userConnectionService, SwipeServiceImpl swipeService,
			MatchedServiceImpl matchedService, NotMatchedServiceImpl notMatchedService,
			ConversationServiceImpl conversationService,
			ConversationParticipantServiceImpl conversationParticipantsService,
			ProfileDietaryServiceImpl profileDietaryService, ProfileInterestingServiceImpl profileInterestingService) {

		this.interestingsService = interestingsService;
		this.dietaryService = dietaryService;
		this.profileService = profileService;
		this.cityService = cityService;
		this.userConnectionService = userConnectionService;
		this.swipeService = swipeService;
		this.matchedService = matchedService;
		this.notMatchedService = notMatchedService;
		this.conversationService = conversationService;
		this.conversationParticipantsService = conversationParticipantsService;
		this.profileDietaryService = profileDietaryService;
		this.profileInterestingService = profileInterestingService;

	}

	public int doMatchingWork(Integer profileId, Integer cityid) {
		System.out.println("W METODZIE DO MATCHING WORK");

		//System.out.println("getProfilesForCity - pobranie listy profili z danego miasta: " + cityid);
		// profileId po to żeby nie zwracać osoby która pyta o to
		List<Integer> profiles = cityService.getProfilesForCity(cityid, profileId);
		
		for (Integer targetProfileId : profiles) {
			// System.out.println("getInterestingCondition");
			int interestingsCondition = profileInterestingService.getSimilarInterestingsCondition(profileId,
					targetProfileId);
			// System.out.println("interestCondition is: " + interestingsCondition);

			// System.out.println("getDietaryCondition");
			int dietaryCondition = profileDietaryService.getSimilarDietaryCondition(profileId, targetProfileId);
			// System.out.println("dietaryCondition is: " + dietaryCondition);

			Integer sumConditions = interestingsCondition + dietaryCondition;
			// System.out.println(" summary condition for targetProfile: " + targetProfileId
			// + " is " + sumConditions);
			try {

				if (sumConditions != 0) {

					//System.out.println("SAVING USER CONNECTION");
					
					UserConnection userConnection = userConnectionService.specialSave(profileId, targetProfileId,
							sumConditions);

//					System.out.println("userConnection base user and target User " + userConnection.getBaseProfileId()
//							+ " " + userConnection.getTargetProfileId());

					// Create 2 swaps for connection what was made

					// jeśli nie ma swapów dodajemy swapy
					List<Swipe> swipeList = swipeService.get(profileId, targetProfileId);
					Matched checkMatchedForExistingMatch = matchedService.getMatch(profileId, targetProfileId);
					// System.out.println("checking for existing MATCHED " +
					// checkMatchedForExistingMatch.getProfile1() + " p2: " +
					// checkMatchedForExistingMatch.getProfile2());
					// NotMatched checkNotMatchedForExistingMatch = notMatchedService.get

					// System.out.println("swipe list:");

//					for (Swipe swipe : swipeList) {
//						System.out.println(swipe.getId());
//						
//					}

					if (( swipeList == null || swipeList.size() == 0 ) && checkMatchedForExistingMatch == null) {

						System.out.println("lISTA pUSTA ");

						Profile baseProfile = profileService.get(profileId);
						Swipe baseProfileSwipe = new Swipe(userConnection, baseProfile);
						System.out.println("baseProfile: " + baseProfile.getFirstName() + " " + baseProfile.getLastName());
						swipeService.save(baseProfileSwipe);

						Profile targetProfile = profileService.get(targetProfileId);
						Swipe targetProfileSwipe = new Swipe(userConnection, targetProfile);
						System.out.println("targetProfile: " + targetProfile.getFirstName() + " " + targetProfile.getLastName());
						swipeService.save(targetProfileSwipe);

						System.out.println("saved ");
						System.out.println("listSize: " + profiles.size());
					//	profiles.remove(targetProfileId);						 // może powodować błędy !!! ? ? ? 
						System.out.println("listSize: " + profiles.size());
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1;

	}

	// (pod update) baseProfile, targetProfile, profile, liked! , date! ->
	// jeśli liked == 0 usuwam ze swipe oba rekordy o base profile i target profile
	// == base profile i target profile
	// oraz dodaje rekord do notMatched ( większy profil pierwszy idzie Profile1,
	// Profile2, date)

	// jeśli liked == 1 sprawdz czy drugi rekord o baseProfile == baseprofile i
	// targetProfile == targetProfile czy liked == 1, jeśli nie nic nie rób,
	// wtedy kolejna osoba zdecyduje czy nawiązać znajomość dając 1 i sprawdzając
	// warunki ( wtedy jeśli oba są == 1 przenosze do matched)

	public void doSwipe(Integer id, Integer baseProfileId, Integer targetProfileId, int liked, String date) {
		Integer initProfile = baseProfileId;
		Integer targetProfile = targetProfileId;
		System.out.println("INIT PROFILE  = " + initProfile);
		System.out.println("target PROFILE  = " + targetProfile);

		if (targetProfileId > baseProfileId) {
			Integer targetHolder = targetProfileId;
			targetProfileId = baseProfileId;
			baseProfileId = targetHolder;
		}

		System.out.println("base PROFILE  = " + baseProfileId);
		System.out.println("target PROFILE  = " + targetProfileId);

		if (liked == 1) {
			System.out.println("liked == 1 usówanie i dodanie do not matched");

			NotMatched notMatched = new NotMatched(baseProfileId, targetProfileId, date);
			notMatchedService.save(notMatched);
			System.out.println("przed usunięciem obu i po dodaniu do not matched ");
			// swipeService.deleteBothSwipes(baseProfileId, targetProfileId);
			List<Swipe> swipeList = swipeService.get(baseProfileId, targetProfileId);

			for (Swipe swipe : swipeList) {
				System.out.println("usuwam");
				System.out.println(swipe.getId());

				// swipeService.delete(swipe.getId());
				swipeService.delete(swipe);

			}

		} else if (liked == 2) {
			System.out.println("liked  == 2 SPRAWDZANIE CZY TARGET RÓWNIEŻ DAŁ 2 ");
			// pobierz z bazy userConnection dla zadanych danych

			// UserConnection userConnection = userConnectionService.get(baseProfileId,
			// targetProfileId);
			// wazne
			Swipe targetSwipe = swipeService.get(baseProfileId, targetProfileId, targetProfile);
			int alsoLikes = targetSwipe.getLiked();

			// jeśli osoba również polubiła czyli ma liked ma wartość 2 to wtedy nawet nie
			// edytuje aktualnego wpisu tylko odrazu dodaje do tabeli amtched
			// jako sparowane osoby oraz usówam oba rekordy z tabeli swipes
			if (alsoLikes == 2) {

				System.out.println("AlsoLikes == 2");

				// tworzenie conversacji dla nowo sparowanych osób
				// DO PRZETESTOWANIA @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

				Conversation conversation = new Conversation();

				conversation.setName("przykladowa nazwa");
				System.out.println("po stworzeniu conversation: " + conversation.getName());
				conversation = conversationService.save(conversation);

				System.out.println("po wykonaniu save");

				// tworzenie nowego wpisu w tabeli matched
				System.out.println("tworzenie matched");
				Matched matched = new Matched(baseProfileId, targetProfileId, conversation, date);
				matchedService.save(matched);

				System.out.println("przed usunięciem obu i po dodaniu do matched ");
				// pobranie z tabeli Swipe obu rekordów do usunięcia
				List<Swipe> swipeList = swipeService.get(baseProfileId, targetProfileId);

				for (Swipe swipe : swipeList) {

					System.out.println("usuwam");
					System.out.println(swipe.getId());
					// usówanie
					swipeService.delete(swipe);

				}

				// Utworzenie Conversation Participants dla sparowanych osób oraz dodanie ich

				ConversationParticipant Participant1 = new ConversationParticipant(conversation,
						swipeList.get(0).getProfile(), 2);
				ConversationParticipant Participant2 = new ConversationParticipant(conversation,
						swipeList.get(1).getProfile(), 2);

				System.out.println("dodaje participanta nr 1");
				conversationParticipantsService.save(Participant1);
				System.out.println("dodaje participanta nr 2");
				conversationParticipantsService.save(Participant2);

				// conversationParticipantsService.save(conversationParticipant);

			} else {
				System.out.println("AlsoLikes != 2");
				Profile currentProfile = profileService.get(initProfile);
				Swipe currentSwipe = swipeService.get(baseProfileId, targetProfileId, initProfile);
				currentSwipe.setLiked(liked);
				currentSwipe.setSwipDate(date);

				swipeService.update(currentSwipe);
			}

		}

	}

}
