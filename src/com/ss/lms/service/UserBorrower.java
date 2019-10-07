package com.ss.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.lms.dataaccess.*;
import com.ss.lms.entity.*;

public class UserBorrower implements ServiceBorrower {

	public DataAccess<LibraryBranch> libraryBranchDao = null;
	public DataAccess<Borrower> borrowerDao = null;
	public DataAccess<BookCopy> bookCopyDao = null;
	public DataAccess<BookLoan> bookLoanDao = null;

	public UserBorrower(LibraryBranchDataAccess libraryBranchDao, BorrowerDataAccess borrowerDao,
			BookCopyDataAccess bookCopyDao, BookLoanDataAccess bookLoanDao) {
		this.libraryBranchDao = libraryBranchDao;
		this.borrowerDao = borrowerDao;
		this.bookCopyDao = bookCopyDao;
		this.bookLoanDao = bookLoanDao;
	}

	public void closeConnection() {
		try {
			libraryBranchDao.close();
			borrowerDao.close();
			bookCopyDao.close();
			bookLoanDao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createBookLoan(BookLoan bookLoan) {
		try

		{

			// get current book book copies for the specific book loan

			ArrayList<BookCopy> currentBookCopies = bookCopyDao.find(new BookCopy(

					bookLoan.getBook(),

					bookLoan.getBranch(),

					-1));

			// make sure there is only one row
			if (currentBookCopies.size() != 1 || currentBookCopies.get(0).getNoOfCopies() < 1)
			{

				System.out.println("Couldn't find unique entry in Book Copies\nOR the library has no copies");

				return;

			}

			// create the book loan

			bookLoanDao.insert(bookLoan);

			// increment the noOfCopy in BookCopy

			bookCopyDao.update(new BookCopy(

					bookLoan.getBook(),

					bookLoan.getBranch(),

					currentBookCopies.get(0).getNoOfCopies() - 1));

		}

		catch (SQLException e)

		{

			e.printStackTrace();

		}

	}

	@Override
	public ArrayList<LibraryBranch> readLibraryBranch(LibraryBranch libraryBranch) {
		// TODO Auto-generated method stub
		ArrayList<LibraryBranch> branches = null;
		try {
			branches = new ArrayList<LibraryBranch>(libraryBranchDao.find(libraryBranch));
			return branches;
		} catch (SQLException e) {
			System.out.println("Invalid Query");
		}
		return branches;
	}

	@Override
	public ArrayList<Borrower> readBorrower(Borrower borrower) {
		// TODO Auto-generated method stub
		ArrayList<Borrower> borrow = null;
		try {
			borrow = new ArrayList<Borrower>(borrowerDao.find(borrower));
			return borrow;
		} catch (SQLException e) {
			System.out.println("Invalid Query");
		}
		return borrow;

	}

	@Override

	public ArrayList<BookLoan> readBookLoan(BookLoan bookLoan) {
		ArrayList<BookLoan> bookLoanList = null;
		try {
			bookLoanList = new ArrayList<BookLoan>(bookLoanDao.find(bookLoan));
			return bookLoanList;
		} catch (SQLException e) {
			System.out.println("Invalid Query");
		}
		return bookLoanList;
	}

	@Override
	public ArrayList<BookCopy> readBookCopy(BookCopy bookCopy) {
		ArrayList<BookCopy> copies = null;
		try {
			copies = new ArrayList<BookCopy>(bookCopyDao.find(bookCopy));
			return copies;
		} catch (SQLException e) {
			System.out.println("Invalid Query");
		}
		return copies;
	}

	@Override
	public void updateBookCopy(BookLoan bookLoan) {
		ArrayList<BookCopy> copyList = new ArrayList<BookCopy>();
		try {

			BookCopyDataAccess DaoBookCopy = new BookCopyDataAccess();
			BookCopy copy = new BookCopy(bookLoan.getBook(), bookLoan.getBranch(), -1);
			copyList = DaoBookCopy.find(copy);
			copy.setNoOfCopies(copyList.get(0).getNoOfCopies() + 1);
			DaoBookCopy.insert(copy);

			BookLoanDataAccess DaoBookloan = new BookLoanDataAccess();
			DaoBookloan.delete(bookLoan);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void returnBookLoan(BookLoan bookLoan) {
		try {
			// get current book book copies for the specific book loan
			ArrayList<BookCopy> currentBookCopies = bookCopyDao
					.find(new BookCopy(bookLoan.getBook(), bookLoan.getBranch(), -1));

			if (currentBookCopies.size() != 1) {
				System.out.println("Couldn't find unique entry in Book Copies");
				return;
			}

			// delete the book loan
			bookLoanDao.delete(bookLoan);

			// increment the noOfCopy in BookCopy
			bookCopyDao.update(new BookCopy(bookLoan.getBook(), bookLoan.getBranch(),
					currentBookCopies.get(0).getNoOfCopies() + 1));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
