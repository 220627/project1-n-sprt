package com.revature.models;

public class UserDTO {
	
	private int userId;
	private String userUsername;
	private int roleId;
	
	public UserDTO(int roleId) {
		super();
		this.roleId = roleId;
	}
	
	public UserDTO(int userId, String userUsername, int roleId) {
		super();
		this.userId = userId;
		this.userUsername = userUsername;
		this.roleId = roleId;
	}
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userUsername=" + userUsername + ", roleId=" + roleId + "]";
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserUsername() {
		return userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
