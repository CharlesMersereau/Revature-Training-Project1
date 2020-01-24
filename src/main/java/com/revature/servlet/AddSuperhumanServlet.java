package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Superhuman;
import com.revature.superhuman.SuperhumanService;

public class AddSuperhumanServlet extends HttpServlet {
	
	private static SuperhumanService supers;
       
    
    public AddSuperhumanServlet() {
        super();
        try {
			supers = new SuperhumanService();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Superhuman superhuman = new Superhuman();
		
		superhuman.setName(request.getParameter("name"));
		superhuman.setPrimaryAbility(request.getParameter("primaryAbility"));
		superhuman.setAlien("true".equals(request.getParameter("alien")));
		superhuman.setSphereOfInfluence(request.getParameter("sphereOfInfluence"));
		superhuman.setAlignmentId(Integer.parseInt(request.getParameter("alignmentId")));
		
		try {
			supers.addSuperhuman(superhuman);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("super-human");
	}

}
