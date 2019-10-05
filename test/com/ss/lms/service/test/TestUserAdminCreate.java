package com.ss.lms.service.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ss.lms.dataaccess.AuthorDataAccess;
import com.ss.lms.dataaccess.BookCopyDataAccess;
import com.ss.lms.dataaccess.BookDataAccess;
import com.ss.lms.dataaccess.BookLoanDataAccess;
import com.ss.lms.dataaccess.BorrowerDataAccess;
import com.ss.lms.dataaccess.LibraryBranchDataAccess;
import com.ss.lms.dataaccess.PublisherDataAccess;
import com.ss.lms.service.*;

class TestUserAdminCreate 
{
	private static ServiceAdmin admin;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		admin = new UserAdmin(
				new AuthorDataAccess(), new PublisherDataAccess(), new BookDataAccess(),
				new LibraryBranchDataAccess(), new BorrowerDataAccess(),
				new BookCopyDataAccess(), new BookLoanDataAccess()
				);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception 
	{
		admin.closeConnection();
	}

	@Test
	final void testUpdateAuthor() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	final void testUpdatePublisher() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	final void testUpdateBook() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
