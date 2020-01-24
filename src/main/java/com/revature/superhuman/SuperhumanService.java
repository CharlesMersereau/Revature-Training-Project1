package com.revature.superhuman;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.SuperhumanDAO;
import com.revature.dao.SuperhumanDAOPostgres;
import com.revature.pojo.Superhuman;

public class SuperhumanService {
	
	private static SuperhumanDAO superDAO;
	
	public SuperhumanService() throws SQLException {
		super();
		superDAO = new SuperhumanDAOPostgres();
	}
	
	public void setSuperDAO(SuperhumanDAO superDAO) {
		this.superDAO = superDAO;
	}
	
	public List<Superhuman> getSuperhumans() throws SQLException {
		
		List<Superhuman> supers = null;
		
		try {
			supers = superDAO.getSuperhumans();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return supers;
	}
	
	public void addSuperhuman(Superhuman superhuman) throws SQLException {
		try {
			superDAO.addSuperhuman(superhuman);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void updateSuperhuman(Superhuman superhuman) throws SQLException {
		
		try {
			superDAO.updateSuperhuman(superhuman);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public void deleteSuperhuman(Integer id) throws SQLException {
		
		try {
			superDAO.removeSuperhuman(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
