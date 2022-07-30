package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.util.ConnectionUtil;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
	
		try (Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("CONNECTED SUCCSSFULLY");
			
			
		} catch(SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		
		Javalin app = Javalin.create(
					
					//the config lambda lets us specify certain configurations for our Javalin app.
					config -> {
						config.enableCorsForAllOrigins(); //this lets us process HTTP requests from any origin
					}
					
					).start(3000);
		
		ReimbursementController rc = new ReimbursementController();
		AuthController ac = new AuthController();
		
		app.get("/reimbursement/status/:reimb_status_id", rc.getReimbursementByStatusHandler);
		app.post("/reimbursement/new-reimbursement", rc.insertNewReimbursementHandler);
		app.get("/reimbursement/all", rc.getAllReimbursementsHandler);
		app.post("/users/login", ac.loginHandler);
		app.patch("/reimbursement/finalize", rc.reimbursementResolutionHandler);
		
	
	}

}
