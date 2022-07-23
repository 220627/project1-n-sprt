package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Reimbursement;
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
						rs.getTimestamp("reimb_resolved"),
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
}

	

