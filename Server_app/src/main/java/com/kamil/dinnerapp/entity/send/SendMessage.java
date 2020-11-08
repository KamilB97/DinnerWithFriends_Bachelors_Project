package com.kamil.dinnerapp.entity.send;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SendMessage {

	private SendProfileBrief profile;

	private Integer conversationId;

	private MessageInfo messageInfo;

	public SendMessage() {
	}

	public SendMessage(SendProfileBrief profile, Integer conversationId, Integer messageId, String messageText,
			String date) {
		this.conversationId = conversationId;
		this.profile = profile;
		this.messageInfo = new MessageInfo(messageId, messageText, date);
	}

	public Integer getConversationId() {
		return conversationId;
	}

	public void setConversationId(Integer conversationId) {
		this.conversationId = conversationId;
	}

	public SendProfileBrief getProfile() {
		return profile;
	}

	public void setProfile(SendProfileBrief profile) {
		this.profile = profile;
	}

	public MessageInfo getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(Integer messageId, String messageText, String date) {
		this.messageInfo = new MessageInfo(messageId, messageText, date);
	}
	@JsonIgnore
	public String getMessageTxt() {
		return this.messageInfo.getMessageTxt();
	}
//	
//	public String getDate() {
//		return this.messageInfo.getDate();
//	}
//	
//	public Integer getMessageId() {
//		return this.messageInfo.getMessageId();
//	}
	
	
	@Override
	public String toString() {
		return "SendMessage [profile=" + profile + ", conversationId=" + conversationId.toString() + ", messageInfo="
				+ messageInfo.toString() + "]";
	}

	class MessageInfo {

		private Integer messageId;

		private String messageTxt;

		private String date;

		public MessageInfo() {
		}

		public MessageInfo(Integer messageId, String messageTxt, String date) {
			this.messageId = messageId;
			this.messageTxt = messageTxt;
			this.date = date;
		}

		public String getMessageTxt() {
			return messageTxt;
		}

		public void setMessageTxt(String messageTxt) {
			this.messageTxt = messageTxt;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public Integer getMessageId() {
			return messageId;
		}

		public void setMessageId(Integer messageId) {
			this.messageId = messageId;
		}

		@Override
		public String toString() {
			return "MessageInfo [messageId=" + messageId + ", messageTxt=" + messageTxt + ", date=" + date + "]";
		}

	}

}
