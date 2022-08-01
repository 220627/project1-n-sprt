package com.revature.models;

import java.sql.Timestamp;

public class ResolutionDTO {

	private int reimbId;
	private int resolverId;
	private int statusId;
	private Timestamp resolved;
	
	
	public ResolutionDTO(int reimbId, int resolverId, int statusId, Timestamp resolved) {
		super();
		this.reimbId = reimbId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.resolved = resolved;
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


	public Timestamp getResolved() {
		return resolved;
	}


	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
	
	
	
	
	
	
	
	
}
