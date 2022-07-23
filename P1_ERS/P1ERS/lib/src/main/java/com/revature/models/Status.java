package com.revature.models;

public class Status {
	
	private int reimbStatusId;
	private String reimbStatus;
	
	
	public Status(String reimbStatus) {
		super();
		this.reimbStatus = reimbStatus;
	}
	
	public Status(int reimbStatusId, String reimbStatus) {
		super();
		this.reimbStatusId = reimbStatusId;
		this.reimbStatus = reimbStatus;
	
	}

	@Override
	public String toString() {
		return "Status [reimbStatusId=" + reimbStatusId + ", reimbStatus=" + reimbStatus + "]";
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}
	
	
	
	
}
