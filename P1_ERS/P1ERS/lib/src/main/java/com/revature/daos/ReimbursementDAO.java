package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAO {
	
	public ArrayList<Reimbursement> getAllReimbursements(){
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_reimbursement";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Reimbursement> rArr = new ArrayList<>();
			
			while(rs.next()) {
				StatusDAO sDAO = new StatusDAO();
				UserDAO uDAO = new UserDAO();
				Reimbursement r = new Reimbursement(
						
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_receipt"),
						rs.getString("reimb_type")
						);
				
	
				r.setReimbStatus(sDAO.getStatusById(rs.getInt("reimb_status_id_fk")));
				r.setReimbResolver(uDAO.userRecordById(rs.getInt("reimb_resolver_fk")));		
				r.setReimbAuthor(uDAO.userRecordById(rs.getInt("reimb_author_fk")));
				
				rArr.add(r);
			
			}
			return rArr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Reimbursement getReimbursementById(int id) {
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "select * from ers_reimbursement where reimb_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement( 
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
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
	
	
	public ArrayList<Reimbursement> getApprovedRecords(int statusFk) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			System.out.println("(GRBS): Arg val: var: 'statusfk': " + statusFk);
			
			String sql = "select * from ers_reimbursement where reimb_status_id_fk = ?;";
			
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			UserDAO uDAO = new UserDAO();
			StatusDAO sDAO = new StatusDAO();
			
			
			
			ArrayList<Reimbursement> rArr = new ArrayList<>();
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amoumt"),
						rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_receipt"),
						rs.getString("reimb_type"),
						sDAO.statusRecordById(rs.getInt("reimb_status_id_fk")),
						uDAO.userRecordById(rs.getInt("reimb_author_fk")),
						uDAO.userRecordById(rs.getInt("reimb_resolver_fk"))
						);
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
					+ "reimb_submitted, reimb_author_fk, reimb_type, reimb_status_id_fk) values "
					+ "(?, ?, ?, ?, ?);";
			
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reimb.getReimbAmt());
			ps.setTimestamp(2, Timestamp.from(Instant.now()));
			ps.setInt(3, reimb.getReimbAuthorFk());
			ps.setString(4, reimb.getReimbType());
			ps.setInt(5, 2);
			
			ps.executeUpdate();
			
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
	
	
	public boolean finalizeResolved(int reimbId) {
		
		
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "update ers_reimbursement set reimb_resolved = ? where reimb_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setTimestamp(1, Timestamp.from(Instant.now()));
			ps.setInt(2, reimbId);
			
			
			return true;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean finalizeResolver(int resolverId, int reimbId) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "update ers_reimbursement set reimb_resolver_fk = ? where reimb_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, resolverId);
			ps.setInt(2, reimbId);
			
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return false;
		
	}
	
	public boolean finalizeReceipt(int reimbId) {
		
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "update ers_reimbursement set reimb_receipt = ? where reimb_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ReimbursementDAO rDAO = new ReimbursementDAO();
			
			Reimbursement reimb = rDAO.getReimbursementById(reimbId);
			
			String receipt = "Your recipt for Reimbursement ID " + reimb.getReimbId() + ": \n" + 
			"Submitted " + reimb.getReimbSubmitted() + " Resolved " + reimb.getReimbResolved() + "\n" +
					"Author " + reimb.getReimbAuthor() + "Resolver " + reimb.getReimbResolver();
			
			ps.setString(1, receipt);
			ps.setInt(2, reimbId);
			
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return false;
	}
	
	public boolean finalizeStatus(int reimbStatusIdFk, int reimbId) {
		
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "update ers_reimbursement set reimb_status_id_fk = ? where reimb_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reimbStatusIdFk);
			ps.setInt(2, reimbId);
			
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}

	public boolean finalizeRecord (int statusIdFk, int reimbId, int resolverIdFk) {
		
		
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			ReimbursementDAO rDAO = new ReimbursementDAO();
			
			if (rDAO.finalizeStatus(statusIdFk, reimbId) &&
					rDAO.finalizeResolved(reimbId) &&
					rDAO.finalizeResolver(resolverIdFk, reimbId) &&
					rDAO.finalizeReceipt(reimbId)) {
				
					return true;
			}
			 
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
}


	

