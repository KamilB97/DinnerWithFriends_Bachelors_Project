package com.kamil.dinnerapp.entity.send;

public class SendUpdatePassword {

	private Integer userId;
	private String oldPassword;
	private String newPassword;

	public SendUpdatePassword() {
	}

	public SendUpdatePassword(Integer userId, String oldPassword, String newPassword) {
		this.userId = userId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "SendUpdatePassword [userId=" + userId + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword
				+ "]";
	}

}
