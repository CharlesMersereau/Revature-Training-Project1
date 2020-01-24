package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Superhuman;
import com.revature.superhuman.SuperhumanService;

public class UpdateSuperhumanServlet extends HttpServlet {
	
	private SuperhumanService supers;

    public UpdateSuperhumanServlet() {
        super();
        try {
			supers = new SuperhumanService();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	PrintWriter pw = response.getWriter();
    	
    	
    	List<Superhuman> registeredSupers = new ArrayList<Superhuman>();
    	
    	try {
    		registeredSupers = supers.getSuperhumans();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	pw.write("<head><title>Superhuman Update Form</title><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
				"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
				"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script></head>");
		pw.write("<div class='jumbotron' style='text-align:center'><h1><a href='/Project1/index.html' style='text-decoration:none;color:inherit'>Superhuman Registration</a></h1></div>");
		pw.write("<div class='container' syle='text-align:center;'>");
    	
    	if (registeredSupers.isEmpty()) {
    		
    		pw.write("<h2>No Superhumans to show</h2>");
    		
    	} else {
    		
    		pw.write("<form method='post' action='/Project1/update-super-human' style='width:50%;margin:auto;'>");
        	pw.write("<div class='form-group'>");
        	pw.write("<select class='mdb-select md-form' name='id'><option value='' disabled selected>Superhuman to update</option>");
        	
        	for (Superhuman superhuman : registeredSupers) {
        		pw.write("<option value='" + superhuman.getId() + "'>" + superhuman.getName() + "</option>");
        	}
        	
        	pw.write("</select></div>");
        	pw.write("<div class='form-group'><label for='name'>Name:</label><input type='text' class='form-control' name='name' required></div>");
        	pw.write("<div class='form-group'><label for='primaryAbility'>Primary Ability:</label><input type='text' class='form-control' name='primaryAbility' required></div>");
        	pw.write("<div class='form-group'><label for='sphereOfInfluence'>Sphere of Influence:</label><input type='text' class='form-control' name='sphereOfInfluence' required></div>");
        	pw.write("<div class='form-group'><label><input type='checkbox' name='alien' value='true'>Alien</label>");
        	pw.write("<select class='mdb-select md-form' name='alignmentId'><option value='' disabled selected>Alignment</option>");
        	pw.write("<option value='1'>hero</option><option value='2'>vigilante</option><option value='3'>antihero</option><option value='4'>neutral</option><option value='5'>villain</option>");
        	pw.write("</select></div>");
        	pw.write("<button type='submit' class='btn btn-default'>Submit</button></form>");
    	}
    
    	pw.write("</div>");
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Superhuman superhuman = new Superhuman();
		
		superhuman.setId(Integer.parseInt(request.getParameter("id")));
		superhuman.setName(request.getParameter("name"));
		superhuman.setPrimaryAbility(request.getParameter("primaryAbility"));
		superhuman.setAlien("true".equals(request.getParameter("alien")));
		superhuman.setSphereOfInfluence(request.getParameter("sphereOfInfluence"));
		superhuman.setAlignmentId(Integer.parseInt(request.getParameter("alignmentId")));
		
		try {
			supers.updateSuperhuman(superhuman);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("super-human");	
	}

}
