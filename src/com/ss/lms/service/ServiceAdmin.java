package com.ss.lms.service;

import java.sql.SQLException;
import java.util.*;

import com.ss.lms.dataaccess.*;
import com.ss.lms.entity.*;

public interface ServiceAdmin
{
	public DataAccess<Author> authorDao = null;
	public DataAccess<Publisher> publisherDao = null;
	public DataAccess<Book> bookDao = null;
	public DataAccess<LibraryBranch> libraryBranchDao = null;
	public DataAccess<UserBorrower> borrowerDao = null;
	public DataAccess<BookCopy> bookCopyDao = null;
	public DataAccess<BookLoan> bookLoanDao = null;
	
	default public void closeConnection() 
	{
		try 
		{
			authorDao.close();
			publisherDao.close();
			bookDao.close();
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

	public void createAuthor(Author author);
	
	public void createPublisher(Publisher entityPublisher);
	
	public void createBook(Book book);
	
	public void createLibraryBranch(LibraryBranch userLibraryBranch);

	public void createBorrower(Borrower borrower);

	public void createBookLoan(BookLoan bookLoan);
	
	public void createBookCopy(BookCopy bookCopy);
	
	/*************************************************
	 * 
	 * ALL READ OPERATIONS
	 * 
	 *************************************************/

	public ArrayList<Author> readAuthor(Author author);
	
	public ArrayList<Publisher> readPublisher(Publisher publisher);
	
	public ArrayList<Book> readBook(Book book);
	
	public ArrayList<LibraryBranch> readLibraryBranch(LibraryBranch libraryBranch);
	
	public ArrayList<Borrower> readBorrower(Borrower userBorrower);

	public ArrayList<BookLoan> readBookLoan(BookLoan bookLoan);

	public ArrayList<BookCopy> readBookCopy(BookCopy bookCopy);
	
	/*************************************************
	 * 
	 * ALL UPDATE OPERATIONS
	 * 
	 *************************************************/
	
	public void updateAuthor(Author author);
	
	public void updatePublisher(LibraryBranch publisher);
	
	public void updateBook(Book book);

	public void updateLibraryBranch(Publisher libraryBranch);

	public void updateBorrower(UserBorrower borrower);

	public void updateBookLoan(BookLoan bookLoan);

	public void updateBookCopy(BookLoan bookCopy);
	
	/*************************************************
	 * 
	 * ALL DELETE OPERATIONS
	 * 
	 *************************************************/
	
	public void deleteAuthor(Author author);
	
	public void deletePublisher(LibraryBranch publisher);
	
	public void deleteBook(Book book);

	public void deleteLibraryBranch(Publisher libraryBranch);

	public void deleteBorrower(UserBorrower borrower);

	public void deleteBookLoan(BookLoan bookLoan);

	public void deleteBookCopy(BookLoan bookCopy);
}
