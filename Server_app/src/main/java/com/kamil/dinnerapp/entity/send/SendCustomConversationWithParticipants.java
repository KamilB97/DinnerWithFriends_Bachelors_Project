package com.kamil.dinnerapp.entity.send;

import java.util.ArrayList;

public class SendCustomConversationWithParticipants {
	
private SendCustomConversation customConversation;

private ArrayList<SendProfileBrief> participants;

public SendCustomConversationWithParticipants() {
	// TODO Auto-generated constructor stub
}


public SendCustomConversationWithParticipants(SendCustomConversation customConversation,
		ArrayList<SendProfileBrief> participants) {
	this.customConversation = customConversation;
	this.participants = participants;
}


public SendCustomConversation getCustomConversation() {
	return customConversation;
}

public void setCustomConversation(SendCustomConversation customConversation) {
	this.customConversation = customConversation;
}

public ArrayList<SendProfileBrief> getParticipants() {
	return participants;
}

public void setParticipants(ArrayList<SendProfileBrief> participants) {
	this.participants = participants;
}



}
