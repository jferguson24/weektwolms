package com.ss.lms.service;

import java.sql.SQLException;
import java.util.*;

import com.ss.lms.dataaccess.*;
import com.ss.lms.entity.*;

public interface ServiceLibrarian
{
	public DataAccess<Book> bookDao = null;
	public DataAccess<LibraryBranch> libraryBranchDao = null;
	public DataAccess<BookCopy> bookCopyDao = null;
	
	default public void closeConnection() 
	{
		try 
		{
			libraryBranchDao.close();
			bookCopyDao.close();
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
	
	public void createBookCopy(BookCopy bookCopy);
	
	/*************************************************
	 * 
	 * ALL READ OPERATIONS
	 * 
	 *************************************************/

	public ArrayList<Book> readBook(Book book);
	
	public ArrayList<LibraryBranch> readLibraryBranch(LibraryBranch libraryBranch);
	
	public ArrayList<BookCopy> readBookCopy(BookCopy bookCopy);
	
	/*************************************************
	 * 
	 * ALL UPDATE OPERATIONS
	 * 
	 *************************************************/
	
	public void updateLibraryBranch(Publisher libraryBranch);
	
	public void updateBookCopy(BookLoan bookCopy);
	
	/*************************************************
	 * 
	 * ALL DELETE OPERATIONS
	 * 
	 *************************************************/
	
	public void deleteBookCopy(BookLoan bookCopy);
}
