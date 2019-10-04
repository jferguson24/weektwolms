package com.ss.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.dataaccess.*;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopy;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;

public class UserAdmin implements ServiceAdmin
{
	private DataAccess<Author> authorDao;
	private DataAccess<Publisher> publisherDao;
	private DataAccess<Book> bookDao;
	private DataAccess<LibraryBranch> libraryBranchDao;
	private DataAccess<Borrower> borrowerDao;
	private DataAccess<BookCopy> bookCopyDao;
	private DataAccess<BookLoan> bookLoanDao;
	
	public UserAdmin(
			AuthorDataAccess authorDao, PublisherDataAccess publisherDao, BookDataAccess bookDao,
			LibraryBranchDataAccess libraryDao, BorrowerDataAccess borrowerDao,
			BookCopyDataAccess bookCopyDao, BookLoanDataAccess bookLoanDao) 
	{
		// "jdbc:mysql://localhost:3306/library","root",""
		this.authorDao = authorDao;
		this.publisherDao = publisherDao;
		this.bookDao = bookDao;
		this.libraryBranchDao = libraryDao;
		this.borrowerDao = borrowerDao;
		this.bookCopyDao = bookCopyDao;
		this.bookLoanDao = bookLoanDao;
	}
	
	public void closeConnection() 
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
	
	@Override
	public void createAuthor(Author author) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPublisher(Publisher entityPublisher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createLibraryBranch(LibraryBranch userLibraryBranch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBorrower(Borrower borrower) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBookLoan(BookLoan bookLoan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBookCopy(BookCopy bookCopy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Author> readAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Publisher> readPublisher(Publisher publisher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> readBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<LibraryBranch> readLibraryBranch(LibraryBranch libraryBranch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Borrower> readBorrower(Borrower userBorrower) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BookLoan> readBookLoan(BookLoan bookLoan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BookCopy> readBookCopy(BookCopy bookCopy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAuthor(Author author) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePublisher(LibraryBranch publisher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLibraryBranch(Publisher libraryBranch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBorrower(UserBorrower borrower) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBookLoan(BookLoan bookLoan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBookCopy(BookLoan bookCopy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAuthor(Author author) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePublisher(LibraryBranch publisher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLibraryBranch(Publisher libraryBranch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBorrower(UserBorrower borrower) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBookLoan(BookLoan bookLoan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBookCopy(BookLoan bookCopy) {
		// TODO Auto-generated method stub
		
	}

}
