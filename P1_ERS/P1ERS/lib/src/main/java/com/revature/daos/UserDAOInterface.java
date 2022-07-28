package com.revature.daos;

import com.revature.models.User;

public interface UserDAOInterface {
	
	User getUserByID(int id);
	
	User getUserByUsername(User user);
	
	
}
