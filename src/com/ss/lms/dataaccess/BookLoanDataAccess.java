package com.ss.lms.dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.ss.lms.entity.*;


public class BookLoanDataAccess extends DataAccess<BookLoan> {

	public BookLoanDataAccess(String connectionInfo) throws SQLException, ClassNotFoundException {
		super(connectionInfo);
		// TODO Auto-generated constructor stub
	}
	/*
	 * Adding book to borrower
	 * */
	@Override
	public void insert(BookLoan entity) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement query;
		String sql;
		sql = "insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values (?,?,?,now(),now() + INTERVAL 7 DAY)";
				 
		
		query = con.prepareStatement(sql);
		query.setInt(1,entity.getBook().getBookId());
		query.setInt(2,entity.getBranch().getBranchId());
		query.setInt(3,entity.getBorrower().getCardNo());
		query.executeUpdate();
		//update Subtracted Copies;
		
		sql = "update tbl_book_copies set noOfCopies = noOfCopies-1 where branchId = ? and bookId = ?";
		query = con.prepareStatement(sql);
		query.setInt(1,entity.getBranch().getBranchId());
		query.setInt(2,entity.getBook().getBookId());
		
		query.executeUpdate();
	}

	@Override
	public ArrayList<BookLoan> find(BookLoan entity) throws SQLException {
		PreparedStatement query;
		String sql;
		sql = "select * from tbl_book_loans where bookId LIKE ? and branchId Like ? and cardNo Like ?";
				 
		
		query = con.prepareStatement(sql);
		query.setInt(1,entity.getBook().getBookId());
		query.setInt(2,entity.getBranch().getBranchId());
		query.setInt(3,entity.getBorrower().getCardNo());
		query.executeUpdate();
		
		
		ArrayList<BookLoan> bookLoans = new ArrayList<>();	
		
		
		return null ;
	}
	/*
	 * Return book
	 * */
	@Override
	public void update(BookLoan entity) throws SQLException {
		PreparedStatement query;
		String sql;
		sql = "update tbl_book_loans set dateIn = now() where bookId = ? and branchId = ? and cardNo = ?";
		 
		
		query = con.prepareStatement(sql);
		query.setInt(1,entity.getBook().getBookId());
		query.setInt(2,entity.getBranch().getBranchId());
		query.setInt(3,entity.getBorrower().getCardNo());
		query.executeUpdate();
		//update Add Copy;
		
		sql = "update tbl_book_copies set noOfCopies = noOfCopies+1 where branchId = ? and bookId = ?";
		query = con.prepareStatement(sql);
		query.setInt(1,entity.getBranch().getBranchId());
		query.setInt(2,entity.getBook().getBookId());	

		query.executeUpdate();
	}
	
	@Override
	public void delete(BookLoan entity) throws SQLException {
		
		PreparedStatement query;
		String sql;
		sql = "delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?";
		 
		
		query = con.prepareStatement(sql);
		query.setInt(1,entity.getBook().getBookId());
		query.setInt(2,entity.getBranch().getBranchId());
		query.setInt(3,entity.getBorrower().getCardNo());
		query.executeUpdate();
	}
	@Override
	public ArrayList<BookLoan> packageResultSet(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<BookLoan> bookLoans = new ArrayList<>();

		while (result.next()) {
			LibraryBranch branch = new LibraryBranch();
			branch.setBranchId(result.getInt("branchId"));
			branch.setBranchAddress(result.getString("branchAddress"));
			branch.setBranchName(result.getString("branchAddress"));
			
			Book book = new Book();
//			book.setAuthor(author);
//			book.setBookId(bookId);
//			book.setPublisher(publisher);
//			book.setTitle(title);
			
			
			Borrower borrower = new Borrower();
			
			
			BookLoan bookLoan = new BookLoan();
			bookLoan.getBook().setBookId(result.getInt("bookId"));
			bookLoan.getBranch().setBranchId(result.getInt("branchId"));		
			bookLoan.getBorrower().setCardNo(result.getInt("cardNo"));		
			bookLoan.setDateOut(result.getDate("dateOut"));		
			bookLoan.setDueDate(result.getDate("dueDate"));		
			bookLoans.add(bookLoan);
		}
		return bookLoans;

	}

}
