package com.revature.services;

import com.revature.daos.AuthDAO;
import com.revature.models.UserDTO;

public class AuthService {
		
	AuthDAO aDAO = new AuthDAO();
	
	public UserDTO login(String username, String password) {
		
		UserDTO uDTO = aDAO.login(username, password);
		System.out.println("udto inside the auth service: " + uDTO);
		if( uDTO != null) {
			return uDTO; //if the username and password get a "true" from the DAO, send the username back.
		}
		
		return null; //if login fails, return null
		
	}
}
