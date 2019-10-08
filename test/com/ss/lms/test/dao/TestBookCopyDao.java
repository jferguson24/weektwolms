package com.ss.lms.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ss.lms.dataaccess.BookCopyDataAccess;
import com.ss.lms.dataaccess.DataAccess;
import com.ss.lms.entity.*;

class TestBookCopyDao {

	static DataAccess<BookCopy> bookCopyDao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bookCopyDao = new BookCopyDataAccess();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testInsert() {

	}

	@Test
	public void testFind() {

	}

	@Test
	public void testUpdate() {

	}

	@Test
	public void testDelete()  {

	}
	

    public void testPackageResultSet() {

    }
    
	@Test // Should not be called, book copy uses a composite key.
	public void testGeneratePrimaryKey() 
	{

	}
}
