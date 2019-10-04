package com.ss.lms.service;

import java.sql.SQLException;
import java.util.*;

import com.ss.lms.dataaccess.*;
import com.ss.lms.entity.*;

public interface ServiceBorrower
{
	public DataAccess<LibraryBranch> libraryBranchDao = null;
	public DataAccess<Borrower> borrowerDao = null;
	public DataAccess<BookCopy> bookCopyDao = null;
	public DataAccess<BookLoan> bookLoanDao = null;
	
	default public void closeConnection() 
	{
		try 
		{
			libraryBranchDao.close();
			borrowerDao.close();
			bookCopyDao.close();
			bookLoanDao.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*************************************************
	 * 
	 * ALL CREATE OPERATIONS
	 * 
	 *************************************************/

	public void createBookLoan(BookLoan bookLoan);
	
	/*************************************************
	 * 
	 * ALL READ OPERATIONS
	 * 
	 *************************************************/

	public ArrayList<LibraryBranch> readLibraryBranch(LibraryBranch libraryBranch);
	
	public ArrayList<Borrower> readBorrower(Borrower borrower);

	public ArrayList<BookLoan> readBookLoan(BookLoan bookLoan);

	public ArrayList<BookCopy> readBookCopy(BookCopy bookCopy);
	
	/*************************************************
	 * 
	 * ALL UPDATE OPERATIONS
	 * 
	 *************************************************/

	public void updateBookCopy(BookLoan bookCopy);
}
