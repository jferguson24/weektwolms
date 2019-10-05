package com.ss.lms.service;

import java.sql.SQLException;
import java.util.*;

import com.ss.lms.entity.*;

public interface ServiceLibrarian
{
	
	public void closeConnection() ;

	
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
	
	public void updateLibraryBranch(LibraryBranch libraryBranch);
	
	public void updateBookCopy(BookCopy bookCopy);
	
	/*************************************************
	 * 
	 * ALL DELETE OPERATIONS
	 * 
	 *************************************************/
	
	public void deleteBookCopy(BookCopy bookCopy);
}
