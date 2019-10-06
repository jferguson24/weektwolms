package com.ss.lms.dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.ss.lms.entity.*;


public class BookLoanDataAccess extends DataAccess<BookLoan> {

	public BookLoanDataAccess() throws SQLException, ClassNotFoundException {
		super();
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
		System.out.println(sql);
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
		ResultSet result;
		sql = "select * from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?";
				 
		
		query = con.prepareStatement(sql);
		query.setInt(1,entity.getBook().getBookId());
		query.setInt(2,entity.getBranch().getBranchId());
		query.setInt(3,entity.getBorrower().getCardNo());
		
		result = query.executeQuery();
		
		return packageResultSet(result) ;
	}
	/*
	 * Return book
	 * */
	@Override
	public void update(BookLoan entity) throws SQLException {
		PreparedStatement query;
		String sql;
		
		sql = "update tbl_book_copies set noOfCopies = noOfCopies+1 where branchId = ? and bookId = ?";
		query = con.prepareStatement(sql);
		query.setInt(1,entity.getBranch().getBranchId());
		query.setInt(2,entity.getBook().getBookId());	

		query.executeUpdate();
		
		delete(entity);
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
		String sql;
		PreparedStatement query;
		ArrayList<BookLoan> bookLoans = new ArrayList<>();

		while (result.next()) {
			//Author
			sql = "select * from tbl_author, tbl_book where bookId = ? and authorId = authId";
			query = con.prepareStatement(sql);
			query.setInt(1, result.getInt(1));
			ResultSet resultAuthor = query.executeQuery();
			resultAuthor.next();
			Author author =  new Author();
			author.setAuthorId(resultAuthor.getInt(1));
			author.setAuthorName(resultAuthor.getString(2));
			
			//Publisher
			sql = "select * from tbl_publisher, tbl_book where bookId = ? and authId = authId";
			query = con.prepareStatement(sql);
			query.setInt(1, result.getInt(1));
			ResultSet resultPublisher = query.executeQuery();
			resultPublisher.next();
			Publisher publisher = new Publisher(resultPublisher.getInt(1), resultPublisher.getString(2),
						resultPublisher.getString(3), resultPublisher.getString(4));
			
			//Book
			sql = "select * from tbl_book where bookId = ?";
			query = con.prepareStatement(sql);
			query.setInt(1, result.getInt(1));
			ResultSet resultBook = query.executeQuery();
			resultBook.next();
			Book book = new Book(resultBook.getInt(1),resultBook.getString(2), author, publisher);
		
			
			//Borrower
			sql = "select * from tbl_borrower where cardNo = ?";
			query = con.prepareStatement(sql);
			query.setInt(1, result.getInt(3));
			ResultSet resultBorrow = query.executeQuery();
			resultBorrow.next();
			Borrower borrower = new Borrower(resultBorrow.getInt(1), resultBorrow.getString(2),
					resultBorrow.getString(3),resultBorrow.getString(4));

			
			//Library
			
			sql = "select * from tbl_library_branch where branchId = ? "; 
			query = con.prepareStatement(sql);
			query.setInt(1, result.getInt(2));
			ResultSet resultBranch = query.executeQuery();
			resultBranch.next();
			LibraryBranch branch = new LibraryBranch(resultBranch.getInt(1), resultBranch.getString(2),
					resultBranch.getString(3)); 
			
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBook(book);
			bookLoan.setBranch(branch);		
			bookLoan.setBorrower(borrower);		
			bookLoan.setDateOut(result.getDate("dateOut"));		
			bookLoan.setDueDate(result.getDate("dueDate"));		
			bookLoans.add(bookLoan);
		}
		return bookLoans;

	}

}
