package com.revature.dao;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.postgresql.core.BaseStatement;

import com.revature.pojo.Superhuman;

@RunWith(MockitoJUnitRunner.class)
public class SuperhumanDAOPostgresTest {
	
	private SuperhumanDAOPostgres superDAO;
	
	private Superhuman superhuman;
	
	@Mock
	private Connection conn;
	
	@Spy
	private PreparedStatement createStmt = ConnectionFactory.getConnection("test").prepareStatement("insert into superhuman (superhuman_name, primary_ability, alien, sphere_of_influence, alignment_id) values (?,?,?,?,?)");
	
	@Spy
	private PreparedStatement getStmt = ConnectionFactory.getConnection("test").prepareStatement("select s.superhuman_id, s.superhuman_name, s.primary_ability, s.alien, s.sphere_of_influence, a.alignment, a.alignment_id from superhuman as s left join alignment as a on s.alignment_id = a.alignment_id order by superhuman_id");
	
	@Spy
	private PreparedStatement updateStmt = ConnectionFactory.getConnection("test").prepareStatement("update superhuman set superhuman_name = ?, primary_ability = ?, alien = ?, sphere_of_influence = ?, alignment_id = ? where superhuman_id = ?");

	@Spy
	private PreparedStatement deleteStmt = ConnectionFactory.getConnection("test").prepareStatement("delete from superhuman where superhuman_id = ?");
	
	@Before
	public void setUp() throws SQLException {
		
		superDAO = new SuperhumanDAOPostgres();
		
		superDAO.setConn(conn);
		
		superhuman = new Superhuman();
	}
	
	@Test
	public void addSuperhuman() throws SQLException {
		
		when(conn.prepareStatement("insert into superhuman (superhuman_name, primary_ability, alien, sphere_of_influence, alignment_id) values (?,?,?,?,?)")).thenReturn(createStmt);
		
		superhuman.setName("Charles");
		superhuman.setPrimaryAbility("tall");
		superhuman.setAlien(true);
		superhuman.setSphereOfInfluence("here");
		superhuman.setAlignmentId(2);
		
		try {
			superDAO.addSuperhuman(superhuman);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		verify(createStmt).setString(1,"Charles");
		verify(createStmt).setString(2,"tall");
		verify(createStmt).setBoolean(3,true);
		verify(createStmt).setString(4,"here");
		verify(createStmt).setInt(5,2);
		verify(createStmt).executeUpdate();
	}
	
	@Test
	public void getSuperhumans() throws SQLException {
		
		when(conn.prepareStatement("select s.superhuman_id, s.superhuman_name, s.primary_ability, s.alien, s.sphere_of_influence, a.alignment, a.alignment_id from superhuman as s left join alignment as a on s.alignment_id = a.alignment_id order by superhuman_id")).thenReturn(getStmt);
		
		superDAO.getSuperhumans("superhuman_id");
		
		verify(getStmt).executeQuery();
	}
	
	@Test
	public void updateSuperhuman() throws SQLException {
		
		when(conn.prepareStatement("update superhuman set superhuman_name = ?, primary_ability = ?, alien = ?, sphere_of_influence = ?, alignment_id = ? where superhuman_id = ?")).thenReturn(updateStmt);
		
		superhuman.setId(-1);
		superhuman.setName("Awahl");
		superhuman.setPrimaryAbility("unlimited eating");
		superhuman.setAlien(true);
		superhuman.setSphereOfInfluence("EL");
		superhuman.setAlignmentId(5);

		superDAO.updateSuperhuman(superhuman);
		
		verify(updateStmt).executeUpdate();
	}
	
	@Test
	public void deleteSuperhuman() throws SQLException {
		
		when(conn.prepareStatement("delete from superhuman where superhuman_id = ?")).thenReturn(deleteStmt);
		
		superDAO.removeSuperhuman(-1);
		
		verify(deleteStmt).executeUpdate();
	}
	
	public SuperhumanDAOPostgresTest() throws SQLException {
		super();
	}

}
