package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Status;
import com.revature.util.ConnectionUtil;

public class StatusDAO {
	public Status getStatusById(int id) {
		
	
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			
			String sql = "select * from ers_reimbursement_status where reimb_status_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			System.out.println("status id: " + id);
			
			
			while (rs.next()) {
				Status s = new Status(rs.getInt("reimb_status_id"),
									  rs.getString("reimb_status"));
			
				
				return s;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
