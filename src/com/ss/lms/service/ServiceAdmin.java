package com.ss.lms.service;

import java.util.ArrayList;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopy;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;

public interface ServiceAdmin
{
	public void closeConnection();
	
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
