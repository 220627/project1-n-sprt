package com.revature.models;

public class User {
	
	private int ersUserId;
	private String ersUsername;
	private String ersPassword;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private int userRoleIdFk;
	private Role userRole;
	
	public User() {
		super();
	}

	
	
	
	public User(int ersUserId, String ersUsername, String userFirstName, String userLastName) {
		super();
		this.ersUserId = ersUserId;
		this.ersUsername = ersUsername;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
	}




	public User(int ersUserId, String ersUsername, String ersPassword, String userFirstName, String userLastName,
			String userEmail, int userRoleIdFk) {
		super();
		this.ersUserId = ersUserId;
		this.ersUsername = ersUsername;
		this.ersPassword = ersPassword;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userRoleIdFk = userRoleIdFk;
	}

	public User(int ersUserId, String ersUsername, String userFirstName, String userLastName, String userEmail,
			int userRoleIdFk) {
		super();
		this.ersUserId = ersUserId;
		this.ersUsername = ersUsername;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userRoleIdFk = userRoleIdFk;
	}
	
	public User(int ersUserId, String ersUsername, String ersPassword, String userFirstName, String userLastName,
			String userEmail, int userRoleIdFk, Role userRole) {
		super();
		this.ersUserId = ersUserId;
		this.ersUsername = ersUsername;
		this.ersPassword = ersPassword;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userRoleIdFk = userRoleIdFk;
		this.userRole = userRole;
	}
	
	public User(int ersUserId, String ersUsername, String userFirstName, String userLastName, int userRoleIdFk) {
		super();
		this.ersUserId = ersUserId;
		this.ersUsername = ersUsername;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userRoleIdFk = userRoleIdFk;
		
	}
	
	
	@Override
	public String toString() {
		return "User [ersUserId=" + ersUserId + ", ersUsername=" + ersUsername + ", ersPassword=" + ersPassword
				+ ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userEmail=" + userEmail
				+ ", userRoleIdFk=" + userRoleIdFk + ", userRole=" + userRole + "]";
	}

	public int getErsUserId() {
		return ersUserId;
	}

	public void setErsUserId(int ersUserId) {
		this.ersUserId = ersUserId;
	}

	public String getErsUsername() {
		return ersUsername;
	}

	public void setErsUsername(String ersUsername) {
		this.ersUsername = ersUsername;
	}

	public String getEraPassword() {
		return ersPassword;
	}

	public void setEraPassword(String eraPassword) {
		this.ersPassword = eraPassword;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserRoleIdFk() {
		return userRoleIdFk;
	}

	public void setUserRoleIdFk(int userRoleIdFk) {
		this.userRoleIdFk = userRoleIdFk;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	};
	
	
}
