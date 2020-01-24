package com.revature.superhuman;

import static org.mockito.Mockito.verify;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.dao.SuperhumanDAO;
import com.revature.pojo.Superhuman;

@RunWith(MockitoJUnitRunner.class)
public class SuperhumanServiceTest {
	
	private SuperhumanService supers;
	
	private Superhuman superhuman;
	
	@Mock
	SuperhumanDAO superDAO;
	
	@Before
	public void setUp() throws SQLException {
		
		superhuman = new Superhuman();
		
		supers = new SuperhumanService();
		
		supers.setSuperDAO(superDAO);
	}
	
	@Test
	public void addSuperhuman() throws SQLException {
		
		superhuman.setName("Awahl");
		superhuman.setPrimaryAbility("unlimited eating");
		superhuman.setAlien(true);
		superhuman.setSphereOfInfluence("EL");
		superhuman.setAlignmentId(5);
		
		
		supers.addSuperhuman(superhuman);
		
		verify(superDAO).addSuperhuman(superhuman);
		
	}
	
	@Test
	public void getSuperhumans() throws SQLException {

		supers.getSuperhumans();
		
		verify(superDAO).getSuperhumans();
	}
	
	@Test
	public void updateSuperhumans() throws SQLException {
		
		superhuman.setId(-1);
		superhuman.setName("Awahl");
		superhuman.setPrimaryAbility("unlimited eating");
		superhuman.setAlien(true);
		superhuman.setSphereOfInfluence("EL");
		superhuman.setAlignmentId(5);
		
		supers.updateSuperhuman(superhuman);
		
		verify(superDAO).updateSuperhuman(superhuman);
	}
	
	@Test
	public void deleteSuperhumans() throws SQLException {
		
		superhuman.setId(-1);
		
		supers.deleteSuperhuman(-1);
		
		verify(superDAO).removeSuperhuman(-1);
	}
	
	/*
	 * Constructor to handle SQLException
	 */
	public SuperhumanServiceTest() throws SQLException {
		super();
	}

}
