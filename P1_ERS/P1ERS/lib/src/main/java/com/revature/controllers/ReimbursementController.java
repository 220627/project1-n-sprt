package com.revature.controllers;


import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.ResolutionDTO;

import io.javalin.http.Handler;

public class ReimbursementController {
	
	ReimbursementDAO rDAO = new ReimbursementDAO();
	
	public Handler getReimbursementByStatusHandler	= (ctx)	-> {
		
		int statusId = Integer.parseInt(ctx.pathParam("reimb_status_id"));
		
		System.out.println("statusId " + statusId);
		
		System.out.println("statusId in the HANDLER : " + statusId);
		
		ArrayList<Reimbursement> rArr = rDAO.getReimbursementsByStatus(statusId);
		
		Gson gson = new Gson();
		
		String JSONReimbursements = gson.toJson(rArr);
		System.out.println("JSON IN THE HANDLER: " + JSONReimbursements);
		
		ctx.result(JSONReimbursements);
		
		ctx.status(200);
		
		
	};
	
	public Handler insertNewReimbursementHandler = (ctx) -> {
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		System.out.println("got here");
		
		Reimbursement newReimb = gson.fromJson(body, Reimbursement.class);
		
		
		if (rDAO.insertNewReimbursement(newReimb)) {
			ctx.status(202);
		} else {
			ctx.status(406);
		}
		
		
		
	};
	
	public Handler getAllReimbursementsHandler = (ctx) -> {
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		
		
		ArrayList<Reimbursement> rArr = rDAO.getAllReimbursements();//rDAO.getAllReimbursements();
		
		System.out.println(rArr.toString());
		
		
		if (rArr != null) {
			
			Gson gson = new Gson();
			String reimbursements = gson.toJson(rArr);
			ctx.result(reimbursements);
			ctx.status(200);
			
		} else {
			ctx.status(400);
		}
		
	};
	
	public Handler reimbursementResolutionHandler = (ctx) -> {
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		ResolutionDTO finalizeDTO = gson.fromJson(body, ResolutionDTO.class);
		
		//our resolveReimbursement method actually calls four different methods.
		//the resolution object is constructed out of the body of the POST request.
		//resolverObj contains the fields needed to call resolveReimbursement.
		//the parameters call for three arguments, status, reimbid, and resolverid
		//these are passed into the resolveReimbursement
		
		if (rDAO.finalizeRecord(
				finalizeDTO.getStatusId(),   
				finalizeDTO.getReimbId(), 
				finalizeDTO.getResolverId())) 
		{
			
			ctx.status(202);
		}
		
		
		
		
		
	};
	
	
}
