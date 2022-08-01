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
		app.get("/reimbursement/status/pending", rc.allPendingRecordsHandler);
		app.get("/reimbursement/status/approved", rc.allApprovedRecordsHandler);
		app.get("/reimbursement/status/denied", rc.allDeniedRecordsHandler);
		app.post("/reimbursement/new-reimbursement", rc.insertNewReimbursementHandler);
		app.get("/reimbursement/all", rc.allReimbursementRecordsHandler);
		app.post("/users/login", ac.loginHandler);
		app.patch("/reimbursement/finalize", rc.finalizationHandler);
		app.get("/reimbursement/user/all/:userId", rc.allRecordsByUserHandler);
		app.get("/reimbursement/user/pending/:userId", rc.allPendingByUserHandler);
	}

}
