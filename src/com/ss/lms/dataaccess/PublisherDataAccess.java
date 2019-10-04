package com.ss.lms.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ss.lms.entity.Publisher;

public class PublisherDataAccess extends DataAccess<Publisher> 
{
	// TODO testing that any of these functions work.
	// TODO handle Integer.MAX_VALUE cases.
	public PublisherDataAccess(String connectionInfo) throws SQLException, ClassNotFoundException 
	{
		super(connectionInfo);
	}

	@Override
	public void insert(Publisher entity) throws SQLException 
	{
		PreparedStatement query;
		String sql = "INSERT INTO tbl_publisher(publisherId,publisherName,publisherAddress,publisherPhone) "
				+ "VALUES ?, ?, ?, ?;";
		
		query = con.prepareStatement(sql);
		query.setInt(1, entity.getPublisherId());
		query.setString(2, entity.getPublisherName());
		query.setString(3, entity.getPublisherAddress());
		query.setString(4, entity.getPublisherPhone());
		
		query.executeUpdate();
	}

	@Override
	public ArrayList<Publisher> find(Publisher entity) throws SQLException 
	{
		ArrayList<Publisher> publishers = new ArrayList<Publisher>();
		ResultSet result;
		PreparedStatement query;

		String sql = "SELECT * FROM tbl_publisher "
				+ "WHERE publisherId = ?" // index 1
				+ "AND publisherName LIKE ?" // index 2
				+ "AND publisherAddress LIKE ?" // index 3
				+ "AND publisherAddress LIKE ?;"; // index 4
		
		
		
		query = con.prepareStatement(sql);
		query.setInt(1, entity.getPublisherId());
		query.setString(2, entity.getPublisherName());
		query.setString(3, entity.getPublisherAddress());
		query.setString(4, entity.getPublisherPhone());
		
		// TODO package result into POJO ArrayList
		result = query.executeQuery();
		
		return packageResultSet(result);
	}

	@Override
	public void update(Publisher entity) throws SQLException 
	{
		PreparedStatement query;
		String sql = "UPDATE tbl_publisher SET "
				+ "publisherName = ?, " // index 1
				+ "publisherAddress = ?, " // index 2
				+ "publisherPhone = ? " // index 3
				+ "WHERE publisherId = ?;"; // index 4
		
		query = con.prepareStatement(sql);
		query.setString(1, entity.getPublisherName());
		query.setString(2, entity.getPublisherAddress());
		query.setString(3, entity.getPublisherPhone());
		query.setInt(4, entity.getPublisherId());
		
		query.executeUpdate();
	}

	@Override
	public void delete(Publisher entity) throws SQLException 
	{
		PreparedStatement query;
		String sql = "DELETE FROM tbl_publisher WHERE "
				+ "publisherId = ?;"; // index 1
		
		query = con.prepareStatement(sql);
		query.setInt(1, entity.getPublisherId());
		
		query.executeUpdate();
	}

	@Override
	public ArrayList<Publisher> packageResultSet(ResultSet result) throws SQLException 
	{
		ArrayList<Publisher> output = new ArrayList<Publisher>();
		Publisher publisher;
		
		// TODO why dont this work?
		// output.addAll(ResultSetUtils.getCollection(new Publisher(), result));
		
		while(result.next()) 
		{
			publisher = new Publisher();
			
			publisher.setPublisherId(result.getInt("publisherId")); // get pk
			publisher.setPublisherName(result.getString("publisherName")); // get name
			publisher.setPublisherAddress(result.getString("publisherAddress")); // get address
			publisher.setPublisherPhone(result.getString("publisherPhone")); // get phone
			
			output.add(publisher);
		}
		
		return output;
	}

}







