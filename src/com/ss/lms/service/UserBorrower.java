package com.ss.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.lms.dataaccess.*;
import com.ss.lms.entity.*;


public class UserBorrower implements ServiceBorrower {
	
	public DataAccess<LibraryBranch> libraryBranchDao;
	public DataAccess<Borrower> borrowerDao;
	public DataAccess<BookCopy> bookCopyDao;
	public DataAccess<BookLoan> bookLoanDao;
	
	
	public UserBorrower(LibraryBranchDataAccess libraryBranchDao,BorrowerDataAccess borrowerDao,
			BookCopyDataAccess bookCopyDao, BookLoanDataAccess bookLoanDao) {
		this.libraryBranchDao = libraryBranchDao;
		this.borrowerDao = borrowerDao;
		this.bookCopyDao =  bookCopyDao;
		this.bookLoanDao = bookLoanDao;
	}
	
	public void closeConnection() 
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
	
	
	
	public void createBookLoan(BookLoan bookLoan) {
		// TODO Auto-generated method stub
		BookLoan bookloaner = new BookLoan(	bookLoan.getBook(),bookLoan.getBranch(),
				bookLoan.getBorrower(),bookLoan.getDateOut(),bookLoan.getDueDate());
		
		
		try {
			BookLoanDataAccess DaoBookloan = new BookLoanDataAccess();
			DaoBookloan.insert(bookloaner);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<LibraryBranch> readLibraryBranch(LibraryBranch libraryBranch) {
		// TODO Auto-generated method stub
		ArrayList<LibraryBranch> branches = null;
		try {
				branches = new ArrayList<LibraryBranch>(libraryBranch.find(libraryBranch));
		}
		
		
		return ;
	}

	@Override
	public ArrayList<UserBorrower> readBorrower(UserBorrower borrower) {
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
	public void updateBookCopy(BookLoan bookCopy) {
		// TODO Auto-generated method stub
		
	}

}
