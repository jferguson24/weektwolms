package com.ss.lms.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.*;

public class LibraryBranchDataAccess extends DataAccess<LibraryBranch> {
	public LibraryBranchDataAccess(String connectionInfo) throws SQLException, ClassNotFoundException {
		super(connectionInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(LibraryBranch entity) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement query;
		String sql;
		//query = con.createStatement();
		sql = "insert into tbl_library_branch (branchId, branchName, branchAddress)"
				+ "values (?,?,?);";

		query = con.prepareStatement(sql);
		query.setInt(1, entity.getBranchId());
		query.setString(2, entity.getBranchName());
		query.setString(3, entity.getBranchName());
		
		query.executeUpdate();
		
	}

	@Override
	public ArrayList<LibraryBranch> find(LibraryBranch entity) throws SQLException {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void update(LibraryBranch entity) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement query;
		String sql;
		
		String newBranchName = entity.getBranchName();
		String newBranchAddress = entity.getBranchAddress();
//		
//		sql = "select * from tbl_library_branch "
//				+ "where branchId = ?";
//		query = con.prepareStatement(sql);
//		query.setInt(1, entity.getBranchId());
//		
//		ResultSet result = query.executeQuery();
		
//		if(newBranchName == "%") {
//			newTitle = result.getString(2);
//		}
//		if(authorId == -1) {
//			authorId = result.getInt(3);
//		}
//		if(publisherId == -1) {
//			publisherId = result.getInt(4);
//		}
		
		sql = "update tbl_library_branch set branchName = ? "
				+ "and branchAddress = ? "
				+ " where branchId = ?";
		query = con.prepareStatement(sql);
		query.setString(1, newBranchName);
		query.setString(2, newBranchAddress);
		query.setInt(3, entity.getBranchId());
		

		query.executeUpdate();
	}

	@Override
	public void delete(LibraryBranch entity) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement query;
		String sql;
		sql = "delete from tbl_book where bookId = ?";
		query = con.prepareStatement(sql);

		query.setInt(1, entity.getBranchId());
		
		query.executeUpdate();
	}
	
    public ArrayList<LibraryBranch> packageResultSet(ResultSet result) throws SQLException{
    	ArrayList<LibraryBranch> branchList = new ArrayList<LibraryBranch>();
		while(result.next()) { //BranchID,BranchName, BranchAddress
			LibraryBranch branch = new LibraryBranch(result.getInt(1), result.getString(2), result.getString(3));
			branchList.add(branch); 
		}	
    	return branchList;
    }
}
