package com.kamil.dinnerapp.entity.send;

public class SendEmail {
	private String email;

	public SendEmail() {
	}

	public SendEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SendEmail [email=" + email + "]";
	}
	

}
