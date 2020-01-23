package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Superhuman;
import com.revature.util.LoggerUtil;

public class SuperhumanDAOPostgres implements SuperhumanDAO {
	
	private LoggerUtil logger = new LoggerUtil();

	public void addSuperhuman(Superhuman superhuman) throws SQLException {
		
		String sql = "insert into superhuman (superhuman_name, primary_ability, alien, sphere_of_influence, alignment_id values (?,?,?,?,?)";
		
		Connection conn = ConnectionFactory.getConnection();
		
		PreparedStatement stmt;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			int rowsUpdated = stmt.executeUpdate();
			
			if (rowsUpdated == 0) {
				logger.debug("a superhuman failed to be added, but an sql exception was not thrown");
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.warn(e.toString());
			}
		}
	}

	public List<Superhuman> getSuperhumans() throws SQLException {
		
		String sql = "select s.superhuman_id, s.superhuman_name, s.primary_ability, s.alien, s.sphere_of_influence, a.alignment from superhuman as s left join alignment as a on s.alignment_id = a.alignment_id";
		
		Connection conn = ConnectionFactory.getConnection();
		
		PreparedStatement stmt;
		
		ArrayList<Superhuman> supers = new ArrayList<Superhuman>();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			Superhuman superhuman;
			
			while(rs.next()) {
				
				superhuman = new Superhuman();
				
				superhuman.setId(rs.getInt("superhuman_id"));
				superhuman.setName(rs.getString("superhuman_name"));
				superhuman.setPrimaryAbility(rs.getString("primary_ability"));
				superhuman.setSphereOfInfluence(rs.getString("sphere_of_influence"));
				superhuman.setAlien(rs.getBoolean("alien"));
				superhuman.setAlignment(rs.getString("alignment"));
				
				supers.add(superhuman);
			}
			
		} catch (SQLException e) {
			logger.debug(e.toString());
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.warn(e.toString());
			}
		}
		
		return supers;
	}

	public void updateSuperhuman(Superhuman superhuman) throws SQLException {

		String sql = "update superhuman set superhuman_name = ?, primary_ability = ?, alien = ?, sphere_of_influence = ?, alignment = ? where superhuman_id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		
		PreparedStatement stmt;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			int rowsUpdated = stmt.executeUpdate();
			
			if (rowsUpdated == 0) {
				logger.debug("a superhuman failed to update, but an sql exception was not thrown");
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.warn(e.toString());
			}
		}
	}

	public void removeSuperhuman(Superhuman suuperhuman) throws SQLException {
		
		String sql = "delete from superhuman where superhuman_id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		
		PreparedStatement stmt;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			int rowsUpdated = stmt.executeUpdate();
			
			if (rowsUpdated == 0) {
				logger.debug("a superhuman failed to be deleted, but an sql exception was not thrown");
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.warn(e.toString());
			}
		}
		
	}

}
