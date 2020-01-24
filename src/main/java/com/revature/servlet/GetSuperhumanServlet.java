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

public class GetSuperhumanServlet extends HttpServlet {
	
	private SuperhumanService supers;
	
	public GetSuperhumanServlet() {
		super();
		try {
			supers = new SuperhumanService();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Superhuman> registeredSupers = new ArrayList<Superhuman>();
		
		String sortBy = request.getParameter("sortBy");
		
		PrintWriter pw = response.getWriter();
		
		pw.write("<head><title>View Superhuman</title><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
				"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
				"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script></head>");
		pw.write("<div class='jumbotron' style='text-align:center'><h1><a href='/Project1/index.html' style='text-decoration:none;color:inherit'>Superhuman Registration</a></h1>\r\n</div>");
		pw.write("<div class='container' style='width:60%;margin:auto;'>");
		
		try {
			
			registeredSupers = supers.getSuperhumans(sortBy);
			
			if (registeredSupers.isEmpty()) {
				pw.write("<h2>No Superhumans to show</h2>");
			} else {
				pw.write("<form method='get' action='/Project1/super-human'>");
				pw.write("<div class='form-group' style='height:30px;'><button class='btn btn-primary' type='submit' style='margin:0 10px;height:30px;width:60px;'>Sort</button><select class='mdb-select md-form' name='sortBy' style='height:30px;width:120px;'>");
				pw.write("<option value='' disabled>Sort by</option>");
				pw.write("<option value='superhuman_id'>Normal</option>");
				pw.write("<option value='superhuman_name'>Name</option>");
				pw.write("<option value='alien'>Alien</option>");
				pw.write("<option value='alignment_id'>Alignment</option>");
				pw.write("</select></div></form>");
				pw.write("<table class='table' style='width:100%;'>");
				pw.write("<tr>");
				pw.write("<th scope='col'>#</th>");
				pw.write("<th scope='col'>Name</th>");
				pw.write("<th scope='col'>Primary Ability</th>");
				pw.write("<th scope='col'>Alien</th>");
				pw.write("<th scope='col'>Sphere Of Influence</th>");
				pw.write("<th scope='col'>Alignment</th>");
				pw.write("</tr>");
				
				for (int i = 0; i < registeredSupers.size(); i++) {
					Superhuman superhuman = registeredSupers.get(i);
					int row = i + 1;
					
					pw.write("<tr>");
					pw.write("<th scope='row'>" + row + "</th>");
					pw.write("<td>" + superhuman.getName() + "</td>");
					pw.write("<td>" + superhuman.getPrimaryAbility() + "</td>");
					pw.write("<td>" + superhuman.isAlien() + "</td>");
					pw.write("<td>" + superhuman.getSphereOfInfluence() + "</td>");
					pw.write("<td>" + superhuman.getAlignment() + "</td>");
					pw.write("</tr>");
				}
				
				pw.write("</table>");
			}
			
		} catch (SQLException e) {
			pw.write("<h3>Error loading Superhumans</h3>");
		}

		pw.write("</div>");
		
	}

}
