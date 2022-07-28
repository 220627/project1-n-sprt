package com.revature.models;

public class ResolutionDTO {

	private int reimbId;
	private int resolverId;
	private int statusId;
	
	
	public ResolutionDTO(int reimbId, int resolverId, int statusId) {
		super();
		this.reimbId = reimbId;
		this.resolverId = resolverId;
		this.statusId = statusId;
	}


	@Override
	public String toString() {
		return "ResolutionDTO [reimbId=" + reimbId + ", resolverId=" + resolverId + ", statusId=" + statusId + "]";
	}


	public int getReimbId() {
		return reimbId;
	}


	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}


	public int getResolverId() {
		return resolverId;
	}


	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}


	public int getStatusId() {
		return statusId;
	}


	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	
	
	
	
	
	
	
}
