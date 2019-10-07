package com.ss.lms.service;

import java.util.ArrayList;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopy;
import com.ss.lms.entity.LibraryBranch;

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
