package com.ss.lms.service;

import java.sql.SQLException;
import java.util.*;

import com.ss.lms.dataaccess.DataAccess;
import com.ss.lms.entity.*;

public abstract class Service 
{
	public DataAccess dataAccess;

	public Service(DataAccess dataAccess) 
	{
		this.dataAccess = dataAccess;
	}
	
	public void closeConnection() 
	{
		try {
			dataAccess.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Author> getAllAuthors()
	{
		return null;//dataAccess.selectAllAuthors();
	}

	public ArrayList<Publisher> getAllPublishers()
	{
		return null;//dataAccess.selectAllPublishers();
	}
	
	public ArrayList<Book> getAllBooks()
	{
		return null;//dataAccess.selectAllBooks();
	}
	
	public Integer getNumberOfCopies(BookCopy bookCopy) 
	{
		return null;//dataAccess.selectNumberOfCopies();
	}
	
	public ArrayList<LibraryBranch> getAllBranches()
	{
		return null;//dataAccess.selectAllBranches();
	}
	
	public abstract Book getBook();
	
}
