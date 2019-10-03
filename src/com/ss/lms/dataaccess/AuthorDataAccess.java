package com.ss.lms.dataaccess;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.Author;

public class AuthorDataAccess extends DataAccess<Author> {

	public AuthorDataAccess(String connectionInfo) throws SQLException, ClassNotFoundException {
		super(connectionInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Author entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Author> find(Author entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Author entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Author entity) {
		// TODO Auto-generated method stub
		
	}

}
