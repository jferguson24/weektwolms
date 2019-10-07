package com.ss.lms.dataaccess;

import java.sql.*;
import java.util.List;

public abstract class DataAccess<T>
{
    Connection con;

    public DataAccess() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");  
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false","root","");
    }

    public void close() throws SQLException 
    {
        con.close();
    }

    public abstract void insert(T entity) throws SQLException;

    public abstract List<T> find(T entity) throws SQLException;
    
    public abstract void update(T entity) throws SQLException;
    
    public abstract void delete(T entity) throws SQLException;
    
    public abstract List<T> packageResultSet(ResultSet result) throws SQLException;
}
