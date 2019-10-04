package com.ss.lms.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Borrower;

public class BorrowerDataAccess extends DataAccess<Borrower>  {

	public BorrowerDataAccess(String connectionInfo) throws SQLException, ClassNotFoundException {
		super(connectionInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Borrower entity) throws SQLException {
		PreparedStatement query;
		String sql;
		sql = "insert into tbl_borrower (cardNo, name, address, phone) values (?,?,?,?)";
				 
		
		query = con.prepareStatement(sql);
		query.setInt(1,entity.getCardNo());
		query.setString(2,entity.getName());
		query.setString(3,entity.getAddress());
		query.setString(4,entity.getPhone());
		
		query.executeUpdate();
	}

	@Override
	public ArrayList<Borrower> find(Borrower entity) {
		ArrayList<Borrower> borrower =  new ArrayList<>();
		
		return null;
	}

	@Override
	public void update(Borrower entity) throws SQLException {
		PreparedStatement query;
		String sql;
		sql = "update tbl_borrower set borrowerName = ? where cardNo = ?";
			
		query = con.prepareStatement(sql);
		query.setString(1,entity.getName());
		query.setInt(2,entity.getCardNo());
		query.executeUpdate();
	}

	@Override
	public void delete(Borrower entity) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement query;
		String sql;
		sql = "delete from tbl_borrower where cardNo = ?";
			
		query = con.prepareStatement(sql);
		query.setInt(1,entity.getCardNo());
		query.executeUpdate();
	}

	@Override
	public List<Borrower> packageResultSet(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
