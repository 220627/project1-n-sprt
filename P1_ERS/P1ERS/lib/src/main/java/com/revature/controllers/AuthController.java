package com.revature.controllers;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.UserDTO;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class AuthController {

	AuthService as = new AuthService();
	
	public static HttpSession ses;
	
	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		LoginDTO lDTO = gson.fromJson(body, LoginDTO.class);
		
		UserDTO uDTO = as.login(lDTO.getUsername(), lDTO.getPassword()); //this will either be a String or Null.
		
		System.out.println("uDTO in auth controller: " + uDTO);
		
		if(uDTO != null) { //if login is successful...
			
			ses = ctx.req.getSession(); //this is how we create new sessions
			
			String convertedUDTO = gson.toJson(uDTO);
			System.out.println("convertedUDTO: " + convertedUDTO);
			
			ctx.result(convertedUDTO);
			
			ctx.status(202); //202 stands for "accepted"
			
		} else {
			ctx.status(401); //401 stands for "unauthorized"
		}
		
	};
	
	
}
