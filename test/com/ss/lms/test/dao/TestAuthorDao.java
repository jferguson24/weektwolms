package com.ss.lms.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ss.lms.dataaccess.*;
import com.ss.lms.entity.Author;

class TestAuthorDao {

	static DataAccess authorsDao;

	// used to find all authors
	public static Author findAllAuthors = new Author(-1, "%");
	
	// used as input to the dao for testing
	public static Author author = new Author(1000,"Testing Author");
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		authorsDao = new AuthorDataAccess();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception 
	{
		authorsDao.close();
	}

	@Test
	final void testInsert() 
	{
		
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testFind() 
	{
		
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testUpdate() 
	{
		
		
		fail("Not yet implemented"); // TODO
	}
	

	@Test
	final void testDelete() 
	{
		
		
		fail("Not yet implemented"); // TODO
	}
	
	
	
}
