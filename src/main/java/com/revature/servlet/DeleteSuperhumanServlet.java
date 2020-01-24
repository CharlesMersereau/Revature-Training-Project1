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


public class DeleteSuperhumanServlet extends HttpServlet {
	
	private static SuperhumanService supers;
	
    public DeleteSuperhumanServlet() {
        super();
        try {
			supers = new SuperhumanService();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	PrintWriter pw = response.getWriter();
    	
    	pw.write("<head><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
				"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
				"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script></head>");
		pw.write("<div class='jumbotron' style='text-align:center'><h1>Superhuman Registration</h1>\r\n</div>");
		pw.write("<div class='container'>");
    	pw.write("<form method='post' action='/Project1/delete-super-human' style='width:50%;margin:auto;'>");
    	pw.write("<div class='form-group'>");
    	pw.write("<select class='mdb-select md-form' name='id'><option value='' disabled selected>Superhuman to update</option>");
    	
    	List<Superhuman> registeredSupers = new ArrayList<Superhuman>();
    	
    	try {
    		registeredSupers = supers.getSuperhumans();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    
    	for (Superhuman superhuman : registeredSupers) {
    		pw.write("<option value='" + superhuman.getId() + "'>" + superhuman.getName() + "</option>");
    	}
    	
    	pw.write("</select></div>");
    	pw.write("<button type='submit' class='btn btn-default'>Submit</button></form></div>");
    	
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			supers.deleteSuperhuman(Integer.parseInt(request.getParameter("id")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("super-human");
		
	}

}
