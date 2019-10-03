package com.ss.lms.service;

import com.ss.lms.entites.*;

import java.sql.SQLException;
import java.util.*;

import com.ss.lms.dataaccess.DataAccess;

public abstract class Service 
{
	public  DataAccess dataAccess;

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
	
	public ArrayList<EntityAuthor> getAllAuthors()
	{
		return dataAccess.selectAllAuthors();
	}

	public ArrayList<EntityPublisher> getAllPublishers()
	{
		return dataAccess.selectAllPublishers();
	}
	
	public ArrayList<EntityBook> getAllBooks()
	{
		return dataAccess.selectAllBooks();
	}
	
	public Integer getNumberOfCopies(EntityBookCopy bookCopy) 
	{
		return dataAccess.selectNumberOfCopies();
	}
	
	public ArrayList<EntityLibraryBranch> getAllBranches()
	{
		return dataAccess.selectAllBranches();
	}
	
	public abstract EntityBook getBook();
	
}
