package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAO {
	
	
	public Reimbursement getReimbursementById(Reimbursement r) {
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "select * from ers_reimbursement where reimb_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, r.getReimbId());
			
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement( 
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimbSubmitted"),
						rs.getString("reimb_receipt"),
						rs.getString("reimb_type")
//---- FINISH WRITNG!!!!---- YOU NEED GETELEMENT BY IDs FOR ALL OBJECTS!!!! 
						);
				
				return reimb;
			}
			
			
			
			
			
		} catch (SQLException e) {
			System.out.println("could not connect to database");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Reimbursement getReimbursementsByStatus(Status status) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_reimbursement where reimb_status_id_fk = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, status.getReimbStatusId());
			
			ResultSet rs = ps.executeQuery();
			
			UserDAO uDAO = new UserDAO();
			StatusDAO sDAO = new StatusDAO();
			
			if (status.getReimbStatus() == "Pending" ){
				while (rs.next()) {
					Reimbursement reimb = new Reimbursement(
							rs.getInt("reimb_id"),
							rs.getInt("reimb_amount"),
							rs.getTimestamp("reimbSubmitted"),
							rs.getString("reimb_type")
							);
					
					//here we only give certain details; this is an unresolved request
					
					//we get our users by id to populate those object fields in 
					//the reimbursement class
					
					
					User u = uDAO.getAuthorById(rs.getInt("reimb_author_fk"));
					
					
					reimb.setReimbAuthor(u);
				}
			
			} else if (status.getReimbStatus() == "Denied" || status.getReimbStatus() == "Approved") {
			
				while (rs.next()) {
					Reimbursement reimb = new Reimbursement(
							rs.getInt("reimb_id"),
							rs.getInt("reimb_amount"),
							rs.getTimestamp("reimb_submitted"),
							rs.getTimestamp("reimb_resolved"),
							rs.getString("reimb_receipt"),
							rs.getString("reimb_type")
							
							);
					
					Status s = sDAO.getStatusById(rs.getInt("reimb_status_id_fk"));
					User us = uDAO.getUserByID(rs.getInt("reimb_resolver_fk"));
					User u = uDAO.getAuthorById(rs.getInt("reimb_author_fk"));
					reimb.setReimbAuthor(u);
					reimb.setReimbResolver(us);
				
			}
				
			
				
				
				//set up SETS to equal author_fk and status_id fk
				//if reimbursement status is approved
				return reimb;
			}
			
			
		} catch (SQLException e) {
			System.out.println("Couldn't connect to your database");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean insertNewReimbursement(Reimbursement reimb) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			

			String sql = "insert into ers_reimbursement (reimb_amount, "
					+ "reimb_submitted, reimb_author_fk, reimb_type) values "
					+ "(?, ?, ?, ?);";
			
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reimb.getReimbAmt());
			ps.setTimestamp(2, reimb.getReimbSubmitted());
			ps.setInt(3, reimb.getReimbAuthorFk());
			ps.setString(4, reimb.getReimbType());
			
			ps.executeQuery();
			
			//if (rs != null) {
				//ReimbursementDAO rDAO = new ReimbursementDAO();
				//Reimbursement returnedReimb = rDAO.getReimbursementById; BASICALLY we trying to getReimbursementById if result set
				//comes back successfully. we can do this in the controller by instatiating a rdao and
				//using the getReimbursementById() method. then we can send a copy of the inserted record up
				//to the front end
			return true;
			} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
		
	}
	
	public boolean updateResolver(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "update ers_reimbursement set reimb_resolver_fk = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return false;
		
	}
	
	public boolean updateReceipt(Reimbursement reimb) {
		
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "update ers_reimbursement set reimb_receipt = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			reimb.setReimbReceipt("Your recipt for Reimbursement ID " + reimb.getReimbId() + ": \n" + 
			"Submitted " + reimb.getReimbSubmitted() + " Resolved " + reimb.getReimbResolved() + "\n" +
					"Author " + reimb.getReimbAuthor() + "Resolver " + reimb.getReimbResolver());
			
			ps.setString(1, reimb.getReimbReceipt());
			
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return false;
	}
	
	public boolean updateReimbursementStatus(int id) {
		
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "update ers_reimbursement set reimb_status_id_fk = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}
	
}

	

