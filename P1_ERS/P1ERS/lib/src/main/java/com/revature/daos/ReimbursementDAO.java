package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
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
	
	
	public Reimbursement getReimbursementByStatus(Status status) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_reimbursement where reimb_status_id_fk = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, status.getReimbStatusId());
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimbSubmitted"),
						rs.getString("reimb_receipt"),
						rs.getString("reimb_type")
						);
				
				//set up SETS to equal author_fk and status_id fk
				//if reimbursement status is approved
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
			
			ResultSet rs = ps.executeQuery();
			
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
}

	

