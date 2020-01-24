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
	private Connection conn = ConnectionFactory.getConnection();

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public SuperhumanDAOPostgres() throws SQLException {
		super();
	}

	public void addSuperhuman(Superhuman superhuman) throws SQLException {

		String sql = "insert into superhuman (superhuman_name, primary_ability, alien, sphere_of_influence, alignment_id) values (?,?,?,?,?)";

		PreparedStatement stmt;

		try {

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, superhuman.getName());
			stmt.setString(2, superhuman.getPrimaryAbility());
			stmt.setBoolean(3, superhuman.isAlien());
			stmt.setString(4, superhuman.getSphereOfInfluence());
			stmt.setInt(5, superhuman.getAlignmentId());

			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated == 0) {
//				logger.debug("a superhuman failed to be added, but an sql exception was not thrown");
			} else {
				logger.info("Superhuman added to the database: " + superhuman);
			}

		} catch (SQLException e) {
//			logger.equals("SQLException in addSuperhuman: " +e.getMessage());
			throw e;
		}
	}

	public List<Superhuman> getSuperhumans(String sortBy) throws SQLException {

		String sql = "select s.superhuman_id, s.superhuman_name, s.primary_ability, s.alien, s.sphere_of_influence, a.alignment, a.alignment_id from superhuman as s left join alignment as a on s.alignment_id = a.alignment_id order by "
				+ sortBy;

		PreparedStatement stmt;

		ArrayList<Superhuman> supers = new ArrayList<Superhuman>();

		try {

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			Superhuman superhuman;

			while (rs.next()) {

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
//			logger.equals("SQLException in getSuperhumans: " +e.getMessage());
			throw e;
		}

		return supers;
	}

	public void updateSuperhuman(Superhuman superhuman) throws SQLException {

		String sql = "update superhuman set superhuman_name = ?, primary_ability = ?, alien = ?, sphere_of_influence = ?, alignment_id = ? where superhuman_id = ?";

		PreparedStatement stmt;

		try {

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, superhuman.getName());
			stmt.setString(2, superhuman.getPrimaryAbility());
			stmt.setBoolean(3, superhuman.isAlien());
			stmt.setString(4, superhuman.getSphereOfInfluence());
			stmt.setInt(5, superhuman.getAlignmentId());
			stmt.setInt(6, superhuman.getId());

			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated == 0) {
//				logger.debug("a superhuman failed to update, but an sql exception was not thrown");
			} else {
//				logger.info("Superhuman updated: " + superhuman);
			}

		} catch (SQLException e) {
//			logger.equals("SQLException in updateSuperhuman: " +e.getMessage());
			throw e;
		}
	}

	public void removeSuperhuman(Integer id) throws SQLException {

		String sql = "delete from superhuman where superhuman_id = ?";

		PreparedStatement stmt;

		try {

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated == 0) {
//				logger.debug("a superhuman failed to be deleted, but an sql exception was not thrown");
			} else {
//				logger.info("Superhuman removed from the database: [" + id +"]");
			}

		} catch (SQLException e) {
//			logger.equals("SQLException in removeSuperhuman: " +e.getMessage());
			throw e;
		}

	}

}
