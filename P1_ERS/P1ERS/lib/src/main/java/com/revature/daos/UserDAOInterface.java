package com.revature.daos;

import com.revature.models.User;

public interface UserDAOInterface {
	
	User getUserByID(User user);
	
	User getUserByUsername(User user, boolean passToggle);
	
	
}
