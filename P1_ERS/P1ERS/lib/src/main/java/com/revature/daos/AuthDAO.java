package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.UserDTO;
import com.revature.util.ConnectionUtil;

public class AuthDAO {
	
	
public UserDTO login(String username, String password) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
		String sql = "select ers_user_id, ers_username, user_role_id_fk from ers_users where ers_username = ? and ers_password = ?;";
			
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		//if anything gets returned at all, we know a user exists with that username/password pair. so we can return true
		if(rs.next()) {
			UserDTO uDTO = new UserDTO(
					rs.getInt("ers_user_id"),
					rs.getString("ers_username"),
					rs.getInt("user_role_id_fk")
					);
			
			return uDTO;
		}
			
		} catch (SQLException e) {
			System.out.println("LOGIN FAILED");
			e.printStackTrace();
		}
		
		return null;
		
	}
}
