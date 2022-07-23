package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAO implements UserDAOInterface {

	@Override
	public User getUserByID(User user) {

		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from ers_users where ers_user_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getErsUserId());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs != null) {
				User u = new User(
						rs.getInt("ers_user_id"),
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id_fk")
						);
				
				return u;
			} else { 
				System.out.println("There was a problem creating your user");
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
