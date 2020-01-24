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
    
    	List<Superhuman> registeredSupers = new ArrayList<Superhuman>();
    	
    	PrintWriter pw = response.getWriter();
    	
    	pw.write("<head><title>Superhuman Update Form</title><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
				"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
				"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script></head>");
		pw.write("<div class='jumbotron' style='text-align:center'><h1><a href='/Project1/index.html' style='text-decoration:none;color:inherit'>Superhuman Registration</a></h1></div>");
		pw.write("<div class='container' syle='text-align:center;'>");
    	
    	try {
    		
    		registeredSupers = supers.getSuperhumans();
    		
        	if (registeredSupers.isEmpty()) {
        		
        		pw.write("<h2>No Superhumans to update</h2>");
        		
        	} else {
        		
        		if (request.getParameter("id")==null) {
        			
        			pw.write("<form method='get' action='/Project1/update-super-human' style='width:50%;margin:auto;'>");
        			pw.write("<div class='form-group'>");
                	pw.write("<select class='mdb-select md-form' name='id' style='height:35px;width:200px;padding:5px' required><option value='' disabled selected>Superhuman to update</option>");
                	
                	for (Superhuman superhuman : registeredSupers) {
                		pw.write("<option value='" + superhuman.getId() + "'>" + superhuman.getName() + "</option>");
                	}
                	
                	pw.write("</select></div>");
                	pw.write("<button type='submit' class='btn btn-default'>Choose this Superhuman</button></form>");
                	
        		} else {
        			
        			try {
        				int id = Integer.parseInt(request.getParameter("id"));
        				
        				Superhuman s = null;
            			
            			for (Superhuman superhuman : registeredSupers) {
            				if (superhuman.getId() == id) {
            					s = superhuman;
            					
            					pw.write("<form method='post' action='/Project1/update-super-human' style='width:50%;margin:auto;'>");
                    			pw.write("<div style='display:none'><input name='id' value='" + request.getParameter("id") + "'></input></div>");
                            	pw.write("<div class='form-group'><label for='name'>Name:</label><input type='text' class='form-control' name='name' value='" + s.getName() + "' required></div>");
                            	pw.write("<div class='form-group'><label for='primaryAbility'>Primary Ability:</label><input type='text' class='form-control' name='primaryAbility' value='" + s.getPrimaryAbility() + "' required></div>");
                            	pw.write("<div class='form-group'><label for='sphereOfInfluence'>Sphere of Influence:</label><input type='text' class='form-control' name='sphereOfInfluence' value='" + s.getSphereOfInfluence() + "' required></div>");
                            	pw.write("<div class='form-group'><label style='margin:auto 10px auto auto;'><input type='checkbox' name='alien' value='true'  " + (s.isAlien() ? "checked='true'" : "")  + "' style='margin:0 5px;'>Alien</label>");
                            	pw.write("<select class='mdb-select md-form' name='alignmentId' style='height:35px;width:180px;padding:5px;float:right;' required><option value='' disabled>Alignment</option>");
                            	System.out.println(s.getAlignment().equals("vigilante"));
                            	pw.write("<option value='1' " + ( (s.getAlignment().equals("hero") ) ? "selected" : "") + ">hero</option>" 
                            			+ "<option value='2' " + ( (s.getAlignment().equals("vigilante") ) ? "selected" : "") + ">vigilante</option>"
                            			+ "<option value='3' " + ( (s.getAlignment().equals("antihero") ) ? "selected" : "") + ">antihero</option>"
                            			+ "<option value='4' " + ( (s.getAlignment().equals("neutral") ) ? "selected" : "") + ">neutral</option>"
                            			+ "<option value='5' " + ( (s.getAlignment().equals("villain") ) ? "selected" : "") + ">villain</option>");
                            	pw.write("</select></div>");
                            	pw.write("<button type='submit' class='btn btn-default'>Update</button></form>");
            					
            				}
            			}
            			
            			if (s == null) {
            				response.sendRedirect("/Project1/update-super-human");
            			}
        				
        			} catch (NumberFormatException e) {
        				response.sendRedirect("update-super-human");
        			}
        			
        		}
        		
        		
        	}
        	
    	} catch (SQLException e) {
    		e.printStackTrace();
    		pw.write("<h3>Error finding Superhumans to update</h3>");
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
