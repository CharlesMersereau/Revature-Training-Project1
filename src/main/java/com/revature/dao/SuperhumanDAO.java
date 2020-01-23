package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.pojo.Superhuman;

public interface SuperhumanDAO {
	
	public void addSuperhuman(Superhuman superhuman) throws SQLException;
	
	public List<Superhuman> getSuperhumans() throws SQLException;
	
	public void updateSuperhuman(Superhuman superhuman) throws SQLException;
	
	public void removeSuperhuman(Superhuman suuperhuman) throws SQLException;

}
