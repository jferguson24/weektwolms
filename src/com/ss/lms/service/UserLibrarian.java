package com.ss.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.dataaccess.BookCopyDataAccess;
import com.ss.lms.dataaccess.BookDataAccess;
import com.ss.lms.dataaccess.DataAccess;
import com.ss.lms.dataaccess.LibraryBranchDataAccess;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopy;
import com.ss.lms.entity.LibraryBranch;

public class UserLibrarian implements ServiceLibrarian{

	private DataAccess<Book> bookDao = null;
	private DataAccess<LibraryBranch> libraryBranchDao = null;
	private DataAccess<BookCopy> bookCopyDao = null;
	
	public UserLibrarian(BookDataAccess bookDao, LibraryBranchDataAccess libraryBranchDao, BookCopyDataAccess bookCopyDao) {
		this.bookDao = bookDao;
		this.libraryBranchDao = libraryBranchDao;
		this.bookCopyDao = bookCopyDao;
	}
	
	//Closes the connection to the sql
	@Override
	public void closeConnection() 
	{
		try 
		{
			bookDao.close();
			libraryBranchDao.close();
			bookCopyDao.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Creates a copy of the bookCopy by calling the data access layer function .insert()
	@Override
	public void createBookCopy(BookCopy bookCopy) {
		try {
			bookCopyDao.insert(bookCopy);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Reads a book by calling the data access function .find()
	@Override
	public ArrayList<Book> readBook(Book book) {
		ArrayList<Book> books = null;
		try {
			books = new ArrayList<Book>(bookDao.find(book));
			return books;
		}
		catch(SQLException e) {
			System.out.println("Invalid Query");
		}
		return books;
	}

	//Reads a library branch by calling the data access function .find()
	@Override
	public ArrayList<LibraryBranch> readLibraryBranch(LibraryBranch libraryBranch) {
		ArrayList<LibraryBranch> branches = null;
		try {
			branches = new ArrayList<LibraryBranch>(libraryBranchDao.find(libraryBranch));
			return branches;
		}
		catch(SQLException e) {
			System.out.println("Invalid Query");
		}
		return branches;
	}

	//Reads a bookCopy by calling the data access function .find()
	@Override
	public ArrayList<BookCopy> readBookCopy(BookCopy bookCopy) {
		ArrayList<BookCopy> copies = null;
		try {
			copies = new ArrayList<BookCopy>(bookCopyDao.find(bookCopy));
			return copies;
		}
		catch(SQLException e) {
			System.out.println("Invalid Query");
		}
		return copies;
	}

	//Updates a library branch by calling the .update() function in the data access layer
	//Does the business logic to not regard %'s
	@Override
	public void updateLibraryBranch(LibraryBranch libraryBranch) {
		try {
			LibraryBranch oldBranch = libraryBranchDao.find(new LibraryBranch(libraryBranch.getBranchId(), "%", "%")).get(0);

			if(libraryBranch.getBranchAddress().equals("%")) {
				libraryBranch.setBranchAddress(oldBranch.getBranchAddress());
			}
			if(libraryBranch.getBranchName().contentEquals("%")) {
				libraryBranch.setBranchName(oldBranch.getBranchName());
			}
			libraryBranchDao.update(libraryBranch);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//Updates a bookCopy
	//Add data is available so no %'s need to be accounted
	@Override
	public void updateBookCopy(BookCopy bookCopy) {
		try {
			bookCopyDao.update(bookCopy);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Deletes a book by calling the .delete() function in the data access layer
	@Override
	public void deleteBookCopy(BookCopy bookCopy) {
		try {
			bookCopyDao.delete(bookCopy);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
