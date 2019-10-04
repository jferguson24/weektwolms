package com.ss.lms.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Publisher;

public class AuthorDataAccess extends DataAccess<Author> 
{

	public AuthorDataAccess(String connectionInfo) throws SQLException, ClassNotFoundException 
	{
		super(connectionInfo);
	}

	@Override
	public void insert(Author entity) throws SQLException 
	{
		PreparedStatement query;
		String sql = "INSERT INTO tbl_author(authorId,authorName) "
				+ "VALUES ?, ?;";
		
		query = con.prepareStatement(sql);
		query.setInt(1, entity.getAuthorId());
		query.setString(2, entity.getAuthorName());
		
		query.executeUpdate();
	}

	@Override
	public ArrayList<Author> find(Author entity) throws SQLException 
	{
		ArrayList<Publisher> publishers = new ArrayList<Publisher>();
		ResultSet result;
		PreparedStatement query;

		String sql = "SELECT * FROM tbl_author"
				+ "WHERE authorId = ?" // index 1
				+ "AND authorName LIKE ?;"; // index 2
		
		query = con.prepareStatement(sql);
		query.setInt(1, entity.getAuthorId());
		query.setString(2, entity.getAuthorName());
		
		// TODO package result into POJO ArrayList
		result = query.executeQuery();
		
		return packageResultSet(result);
	}

	@Override
	public void update(Author entity) throws SQLException 
	{
		PreparedStatement query;
		String sql = "UPDATE tbl_author SET "
				+ "authorName = ? " // index 1
				+ "WHERE publisherId = ?;"; // index 2
		
		query = con.prepareStatement(sql);
		query.setString(1, entity.getAuthorName());
		query.setInt(4, entity.getAuthorId());
		
		query.executeUpdate();
	}

	@Override
	public void delete(Author entity) throws SQLException 
	{
		PreparedStatement query;
		String sql = "DELETE FROM tbl_author WHERE "
				+ "authorId = ?;"; // index 1
		
		query = con.prepareStatement(sql);
		query.setInt(1, entity.getAuthorId());
		
		query.executeUpdate();
	}

	@Override
	public ArrayList<Author> packageResultSet(ResultSet result) throws SQLException 
	{
		ArrayList<Author> output = new ArrayList<Author>();
		Author publisher;
		
		// TODO why dont this work? 
		// output.addAll(ResultSetUtils.getCollection(new Publisher(), result));
		// https://commons.apache.org/dormant/scaffold/apidocs/org/apache/commons/scaffold/sql/ResultSetUtils.html
		
		while(result.next()) 
		{
			publisher = new Author();
			
			publisher.setAuthorId(result.getInt("authorId")); // get pk
			publisher.setAuthorName(result.getString("authorName")); // get name
			
			output.add(publisher);
		}
		
		return output;
	}

}
