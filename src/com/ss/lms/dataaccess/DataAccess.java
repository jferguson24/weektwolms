package com.ss.lms.dataaccess;

import java.sql.*;
import java.util.ArrayList;
import com.ss.lms.entites.*;

public abstract class DataAccess 
{
	Connection con;
	
    public DataAccess(String connectionInfo) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");  
        con = DriverManager.getConnection(connectionInfo);
    }
    
    public void close() throws SQLException 
    {
        con.close();
    }
	
	public abstract ArrayList<EntityLibraryBranch> selectAllBranches();

	public abstract ArrayList<EntityAuthor> selectAllAuthors();

	public abstract ArrayList<EntityPublisher> selectAllPublishers();

	public abstract ArrayList<EntityBook> selectAllBooks();

	public abstract Integer selectNumberOfCopies();


}
