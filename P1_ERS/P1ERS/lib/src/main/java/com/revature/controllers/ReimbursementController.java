package com.revature.controllers;


import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.internal.GsonBuildConfig;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.ResolutionDTO;

import io.javalin.http.Handler;

public class ReimbursementController {
	
	public Handler allDeniedRecordsHandler = (ctx) -> {
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		
		ArrayList<Reimbursement> rArr = rDAO.allDeniedRecords();
		
		Gson gson = new Gson();
		
		String records = gson.toJson(rArr);
		
		ctx.result(records);
		
		ctx.status(200);
	};
	
	
	
	public Handler allApprovedRecordsHandler = (ctx)	-> {
		
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		ArrayList<Reimbursement> rArr = rDAO.allApprovedRecords();
		
		
		Gson gson = new Gson();
		
		String records = gson.toJson(rArr);
		
		System.out.println("debug. in allApproveRecordsHandler. records=[" + records + "]");
		
		ctx.result(records);
		
		ctx.status(200);
		
		
	};
	
	public Handler allPendingRecordsHandler=(ctx) -> {
		ReimbursementDAO rDAO = new ReimbursementDAO();
ArrayList<Reimbursement> rArr = rDAO.allPendingRecords();
		
		System.out.println("Debug. (AARPRH): field: ArrayList<Reimbursement> rArr = rDAO.allApprovedRecords(). Val: " + rArr);
		
		Gson gson = new Gson();
		
		String records = gson.toJson(rArr);
		
		System.out.println("Entering allApprovedRecordsHandler");
		System.out.println("Debug. (AAPRH): field: String records = gson.ToJson(rArr). Val: " + records);
		
		ctx.result(records);
		
		ctx.status(200);
	};
	
	public Handler insertNewReimbursementHandler = (ctx) -> {
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		System.out.println("got here");
		
		Reimbursement newReimb = gson.fromJson(body, Reimbursement.class);
		
		
		if (rDAO.newReimbursement(newReimb)) {
			ctx.status(202);
		} else {
			ctx.status(406);
		}
		
		
		
	};
	
	public Handler allReimbursementRecordsHandler = (ctx) -> {
		
		ReimbursementDAO rDAO = new ReimbursementDAO();
		
		
		ArrayList<Reimbursement> rArr = rDAO.allRecords();//rDAO.getAllReimbursements();
		
		System.out.println(rArr.toString());
		
		
		if (rArr != null) {
			
			Gson gson = new Gson();
			String reimbursements = gson.toJson(rArr);
			ctx.result(reimbursements);
			ctx.status(200);
			
		} 
		
	};
	
	public Handler allRecordsByUserHandler = (ctx) -> {

		ReimbursementDAO rDAO = new ReimbursementDAO();
		
		int authorId = Integer.parseInt(ctx.pathParam("userId"));
		
		Gson gson = new Gson();
		
		
		ArrayList<Reimbursement> rArr = rDAO.allRecordsByUser(authorId);
		
		String records = gson.toJson(rArr);
		
		ctx.result(records);
		ctx.status(200);
		
	};
	
public Handler allPendingByUserHandler = (ctx) -> {
	
		ReimbursementDAO rDAO = new ReimbursementDAO();
		
		int authorId = Integer.parseInt(ctx.pathParam("userId"));
	
		
		Gson gson = new Gson();
		
		
		ArrayList<Reimbursement> rArr = rDAO.allPendingByUser(authorId);
		
		String records = gson.toJson(rArr);
		
		ctx.result(records);
		ctx.status(200);
		
	};
	
	public Handler finalizationHandler = (ctx) -> {
		
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
				finalizeDTO.getResolverId()
				))
		{
			
			ctx.status(202);
		}
		
		
		
		
		
	};
	
	
}
