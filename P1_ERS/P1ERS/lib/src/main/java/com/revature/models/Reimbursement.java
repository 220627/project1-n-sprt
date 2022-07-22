package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	
	private int reimb_id;
	private int reimb_amt;
	private Timestamp reimb_submitted;
	private Timestamp reimb_resolved;
	private String reimb_receipt;
	private int reimb_author_fk;
	private int resolver_fk;
	private int reimb_status_id_fk;
	private int reimb_type_id_fk;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimb_id, int reimb_amt, Timestamp reimb_submitted, Timestamp reimb_resolved,
			String reimb_receipt, int reimb_author_fk, int resolver_fk, int reimb_status_id_fk, int reimb_type_id_fk) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amt = reimb_amt;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author_fk = reimb_author_fk;
		this.resolver_fk = resolver_fk;
		this.reimb_status_id_fk = reimb_status_id_fk;
		this.reimb_type_id_fk = reimb_type_id_fk;
	}

	public Reimbursement(int reimb_author_fk, int resolver_fk, int reimb_status_id_fk, int reimb_type_id_fk) {
		super();
		this.reimb_author_fk = reimb_author_fk;
		this.resolver_fk = resolver_fk;
		this.reimb_status_id_fk = reimb_status_id_fk;
		this.reimb_type_id_fk = reimb_type_id_fk;
	}

	public Reimbursement(String reimb_receipt) {
		super();
		this.reimb_receipt = reimb_receipt;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", reimb_amt=" + reimb_amt + ", reimb_submitted="
				+ reimb_submitted + ", reimb_resolved=" + reimb_resolved + ", reimb_receipt=" + reimb_receipt
				+ ", reimb_author_fk=" + reimb_author_fk + ", resolver_fk=" + resolver_fk + ", reimb_status_id_fk="
				+ reimb_status_id_fk + ", reimb_type_id_fk=" + reimb_type_id_fk + "]";
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public int getReimb_amt() {
		return reimb_amt;
	}

	public void setReimb_amt(int reimb_amt) {
		this.reimb_amt = reimb_amt;
	}

	public Timestamp getReimb_submitted() {
		return reimb_submitted;
	}

	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public Timestamp getReimb_resolved() {
		return reimb_resolved;
	}

	public void setReimb_resolved(Timestamp reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public String getReimb_receipt() {
		return reimb_receipt;
	}

	public void setReimb_receipt(String reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}

	public int getReimb_author_fk() {
		return reimb_author_fk;
	}

	public void setReimb_author_fk(int reimb_author_fk) {
		this.reimb_author_fk = reimb_author_fk;
	}

	public int getResolver_fk() {
		return resolver_fk;
	}

	public void setResolver_fk(int resolver_fk) {
		this.resolver_fk = resolver_fk;
	}

	public int getReimb_status_id_fk() {
		return reimb_status_id_fk;
	}

	public void setReimb_status_id_fk(int reimb_status_id_fk) {
		this.reimb_status_id_fk = reimb_status_id_fk;
	}

	public int getReimb_type_id_fk() {
		return reimb_type_id_fk;
	}

	public void setReimb_type_id_fk(int reimb_type_id_fk) {
		this.reimb_type_id_fk = reimb_type_id_fk;
	}
	
	
	
	
}
