package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	
	private int reimbId;
	private int reimbAmt;
	private Timestamp reimbSubmitted;
	private Timestamp reimbResolved;
	private String reimbReceipt;
	private String reimbType;
	private int reimbAuthorFk;
	private int resolverFk;
	private int reimbStatusIdFk;
	private Status reimbStatus;
	private User reimbAuthor;
	private User reimbResolver;
	
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbAmt, String reimbType, int reimbAuthorFk, int reimbStatusIdFk) {
		super();
		this.reimbAmt = reimbAmt;
		this.reimbType = reimbType;
		this.reimbAuthorFk = reimbAuthorFk;
		this.reimbStatusIdFk = reimbStatusIdFk;
	}
	

	public Reimbursement(int reimbId, int reimbAmt, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbReceipt, String reimbType, Status reimbStatus, User reimbAuthor, User reimbResolver) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbReceipt = reimbReceipt;
		this.reimbType = reimbType;
		this.reimbStatus = reimbStatus;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
	}

	
	public Reimbursement(int reimbId, int reimbAmt, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbReceipt, String reimbType, int reimbAuthorFk, int resolverFk, int reimbStatusIdFk) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbReceipt = reimbReceipt;
		this.reimbType = reimbType;
		this.reimbAuthorFk = reimbAuthorFk;
		this.resolverFk = resolverFk;
		this.reimbStatusIdFk = reimbStatusIdFk;
	}


	public Reimbursement(int reimbId, int reimbAmt, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbReceipt, String reimbType, int reimbAuthorFk, int resolverFk, int reimbStatusIdFk,
			Status reimbStatus, User reimbAuthor, User reimbResolver) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbReceipt = reimbReceipt;
		this.reimbType = reimbType;
		this.reimbAuthorFk = reimbAuthorFk;
		this.resolverFk = resolverFk;
		this.reimbStatusIdFk = reimbStatusIdFk;
		this.reimbStatus = reimbStatus;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
	}


	


	public Reimbursement(int reimbId, int reimbAmt, Timestamp reimbSubmitted,
			String reimbReceipt, String reimbType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbReceipt = reimbReceipt;
		this.reimbType = reimbType;
	}
	
	


	public Reimbursement(int reimbId, int reimbAmt, Timestamp reimbSubmitted, String reimbType, int reimbAuthorFk,
			int resolverFk, int reimbStatusIdFk, Status reimbStatus, User reimbAuthor) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbType = reimbType;
		this.reimbAuthorFk = reimbAuthorFk;
		this.resolverFk = resolverFk;
		this.reimbStatusIdFk = reimbStatusIdFk;
		this.reimbStatus = reimbStatus;
		this.reimbAuthor = reimbAuthor;
	}

	
	
	public Reimbursement(int reimbId, int reimbAmt, Timestamp reimbSubmitted, String reimbType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbType = reimbType;
	}

	
	
	

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmt=" + reimbAmt + ", reimbSubmitted=" + reimbSubmitted
				+ ", reimbResolved=" + reimbResolved + ", reimbReceipt=" + reimbReceipt + ", reimbType=" + reimbType
				+ ", reimbAuthorFk=" + reimbAuthorFk + ", resolverFk=" + resolverFk + ", reimbStatusIdFk="
				+ reimbStatusIdFk + ", reimbStatus=" + reimbStatus + ", reimbAuthor=" + reimbAuthor + ", reimbResolver="
				+ reimbResolver + "]";
	}


	public int getReimbId() {
		return reimbId;
	}


	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}


	public int getReimbAmt() {
		return reimbAmt;
	}


	public void setReimbAmt(int reimbAmt) {
		this.reimbAmt = reimbAmt;
	}


	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}


	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}


	public Timestamp getReimbResolved() {
		return reimbResolved;
	}


	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}


	public String getReimbReceipt() {
		return reimbReceipt;
	}


	public void setReimbReceipt(String reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}


	public String getReimbType() {
		return reimbType;
	}


	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}


	public int getReimbAuthorFk() {
		return reimbAuthorFk;
	}


	public void setReimbAuthorFk(int reimbAuthorFk) {
		this.reimbAuthorFk = reimbAuthorFk;
	}


	public int getResolverFk() {
		return resolverFk;
	}


	public void setResolverFk(int resolverFk) {
		this.resolverFk = resolverFk;
	}


	public int getReimbStatusIdFk() {
		return reimbStatusIdFk;
	}


	public void setReimbStatusIdFk(int reimbStatusIdFk) {
		this.reimbStatusIdFk = reimbStatusIdFk;
	}


	public Status getReimbStatus() {
		return reimbStatus;
	}


	public void setReimbStatus(Status reimbStatus) {
		this.reimbStatus = reimbStatus;
	}


	public User getReimbAuthor() {
		return reimbAuthor;
	}


	public void setReimbAuthor(User reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}


	public User getReimbResolver() {
		return reimbResolver;
	}


	public void setReimbResolver(User reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	
	
	
	
}
