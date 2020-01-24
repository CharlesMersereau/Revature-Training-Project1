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
			supers = superDAO.getSuperhumans("superhuman_id");
		} catch (SQLException e) {
			throw e;
		}
		
		return supers;
	}
	
	public List<Superhuman> getSuperhumans(String sortBy) throws SQLException {
		
		List<Superhuman> supers = null;
		
		try {
			supers = superDAO.getSuperhumans(sanitizeSortBy(sortBy));
		} catch (SQLException e) {
			throw e;
		}
		
		return supers;
	}
	
	public void addSuperhuman(Superhuman superhuman) throws SQLException {
		try {
			superDAO.addSuperhuman(superhuman);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void updateSuperhuman(Superhuman superhuman) throws SQLException {
		
		try {
			superDAO.updateSuperhuman(superhuman);
		} catch (SQLException e) {
			throw e;
		}
		
	}
	
	public void deleteSuperhuman(Integer id) throws SQLException {
		
		try {
			superDAO.removeSuperhuman(id);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public String sanitizeSortBy(String sortBy) {
		
		if (sortBy == null) {
			return "superhuman_id";
		} else if (sortBy.equals("superhuman_name") || sortBy.equals("alien") || sortBy.equals("alignment_id")) {
			return sortBy;
		} else {
			return "superhuman_id";
		}
		
	}

}
