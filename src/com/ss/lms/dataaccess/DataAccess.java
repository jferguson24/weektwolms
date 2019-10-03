package com.ss.lms.dataaccess;

import java.sql.*;
import java.util.ArrayList;

import com.ss.lms.entity.*;

public abstract class DataAccess<T>
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

    public abstract void insert(T entity);

    public abstract ArrayList<T> find(T entity);
    
    public abstract void update(T entity);
    
    public abstract void delete(T entity);
}
