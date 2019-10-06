package com.ss.lms.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.ss.lms.entity.Borrower;

public class BorrowerDataAccess extends DataAccess<Borrower>  {

	public BorrowerDataAccess() throws SQLException, ClassNotFoundException {
		super();
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
	public ArrayList<Borrower> find(Borrower entity) throws SQLException {
		//ArrayList<Borrower> borrower =  new ArrayList<>();
		ResultSet result;
		PreparedStatement query;
		
		String sql = "select * from tbl_borrower where cardNo = ?";
				
		query = con.prepareStatement(sql);
		query.setInt(1, entity.getCardNo());
		
		result = query.executeQuery();	
					
		return packageResultSet(result);
	}

	@Override
	public void update(Borrower entity) throws SQLException {
		PreparedStatement query;
		String sql;
		sql = "update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?";
			
		query = con.prepareStatement(sql);
		query.setString(1,entity.getName());
		query.setString(2,entity.getAddress());
		query.setString(3,entity.getPhone());
		query.setInt(4,entity.getCardNo());
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
	public ArrayList<Borrower> packageResultSet(ResultSet result) throws SQLException {
		ArrayList<Borrower>  borrowerSet = new ArrayList<>();
		Borrower borrower;
		while(result.next()) {

			borrower  = new Borrower();
			borrower.setAddress(result.getString(3));
			borrower.setCardNo(result.getInt("cardNo"));
			borrower.setName(result.getString("name"));
			borrower.setPhone(result.getString("phone"));
			borrowerSet.add(borrower);
		}
		
		
		
		return borrowerSet;
	}

}
