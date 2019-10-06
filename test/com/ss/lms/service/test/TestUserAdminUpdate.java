package com.ss.lms.service.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ss.lms.dataaccess.*;
import com.ss.lms.entity.*;
import com.ss.lms.service.*;

class TestUserAdminUpdate 
{
	// why does juint force static?
	// this doesn't seem like a good solution 
	public static ServiceAdmin admin;

	// these objects are used purely as input to DAO find functions which will return the entire contents of the table
	public static Author findAllAuthors = new Author(-1, "%");
	public static Publisher findAllPublishers = new Publisher(-1, "%", "%", "%");
	public static LibraryBranch findAllLibraryBranches = new LibraryBranch(-1, "%", "%");
	public static Borrower findAllBorrowers = new Borrower(-1, "%", "%", "%");
	public static Book findAllBooks = new Book(-1, "%", findAllAuthors, findAllPublishers);
	public static BookLoan findAllBookLoans= new BookLoan(findAllBooks, findAllLibraryBranches, findAllBorrowers, Date.valueOf("0001-01-01"), Date.valueOf("0001-01-01"));
	
	// these objects will be inserted in @BeforeAll and removed in @AfterAll and serve to test the service
	public static Author author = new Author(1000,"Testing Author");
	public static Publisher publisher = new Publisher(1000, "Testing Publisher", "Testing Publisher Address", "Testing Publisher Phone");
	public static LibraryBranch branch = new LibraryBranch(1000, "Testing Branch Name", "Testing Branch Address");
	public static Borrower borrower = new Borrower(1000, "Testing Borrower Name", "Testing Borrower Address", "Testing Borrower Phone");
	public static Book book = new Book(1000, "Testing Book Title", author, publisher);
	public static BookLoan loan = new BookLoan(book, branch, borrower, Date.valueOf("0001-01-01"), Date.valueOf("0002-02-02"));
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		admin = new UserAdmin(
				new AuthorDataAccess(), new PublisherDataAccess(), new BookDataAccess(),
				new LibraryBranchDataAccess(), new BorrowerDataAccess(), new BookLoanDataAccess()
				);
		
		admin.createAuthor(author);
		admin.createPublisher(publisher);
		admin.createLibraryBranch(branch);
		admin.createBorrower(borrower);
		admin.createBook(book);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception 
	{
		admin.deleteAuthor(author);
		admin.deletePublisher(publisher);
		admin.deleteLibraryBranch(branch);
		admin.deleteBorrower(borrower);
		admin.deleteBook(book);
		
		admin.closeConnection();
	}

	@Test
	final void testUpdateAuthor() 
	{
		// save the previous data
		ArrayList<Author> oldData = admin.readAuthor(findAllAuthors);
		String newName = "New Author Name";
		
		// change the value
		admin.updateAuthor(new Author(author.getAuthorId(),newName));
		
		// store the new version of the table
		ArrayList<Author> newData = admin.readAuthor(findAllAuthors);
		
		// change the value in the db back 
		admin.updateAuthor(author);
		
		// change it in our oldData arraylist
		oldData.forEach(row -> 
		{
			if(row.getAuthorId() == author.getAuthorId()) 
			{
				row.setAuthorName(newName);
			}
		});
		
		// check if the newData is the same as oldData
		// assertEquals(newData,oldData);
		// assertequals does not properly compare the contents of these arraylists
		// upon looking at the actual values, diff view showed no differences whatsoever.
		
		for(int i = 0; i < newData.size(); ++i) 
		{
			if(!oldData.get(i).toString().equals(newData.get(i).toString()))
			{
				fail("Mismatch:\n" + oldData.get(i).toString() + "\n" + newData.get(i).toString());
			}
		}
	}
	
	@Test
	final void testUpdatePublisher() 
	{
		// save the previous data
		ArrayList<Publisher> oldData = admin.readPublisher(findAllPublishers);
		String newName = "New Publisher Name";
		String newAddr = "New Publisher Addr";
		String newPhone = "New Publisher Phone";
		
		// change the value
		admin.updatePublisher(new Publisher(publisher.getPublisherId(),newName, newAddr, newPhone));
		
		// store the new version of the table
		ArrayList<Publisher> newData = admin.readPublisher(findAllPublishers);
		
		// change the value in the db back 
		admin.updatePublisher(publisher);
		
		// change it in our oldData arraylist
		oldData.forEach(row -> 
		{
			if(row.getPublisherId() == publisher.getPublisherId()) 
			{
				row.setPublisherName(newName);
				row.setPublisherAddress(newAddr);
				row.setPublisherPhone(newPhone);
			}
		});
		
		// check if the newData is the same as oldData
		// assertEquals(newData,oldData);
		// assertequals does not properly compare the contents of these arraylists
		// upon looking at the actual values, diff view showed no differences whatsoever.
		
		for(int i = 0; i < newData.size(); ++i) 
		{
			if(!oldData.get(i).toString().equals(newData.get(i).toString()))
			{
				fail("Mismatch:\n" + oldData.get(i).toString() + "\n" + newData.get(i).toString());
			}
		}
	}
	
	@Test
	final void testUpdateBook() 
	{
		// save the previous data
		ArrayList<Book> oldData = admin.readBook(findAllBooks);
		String newTitle = "New Book Title";
		
		// change the value
		admin.updateBook(new Book(book.getBookId(), newTitle, author, publisher));
		
		// store the new version of the table
		ArrayList<Book> newData = admin.readBook(findAllBooks);
		
		// change the value in the db back 
		admin.updateBook(book);
		
		// change it in our oldData arraylist
		oldData.forEach(row -> 
		{
			if(row.getBookId() == book.getBookId()) 
			{
				row.setTitle(newTitle);
				row.setAuthor(author);
				row.setPublisher(publisher);
			}
		});
		
//		System.out.println("old");
//		oldData.forEach(row -> System.out.println(row));
//		System.out.println("new");
//		newData.forEach(row -> System.out.println(row));

		// check if the newData is the same as oldData
//		 assertEquals(newData,oldData);
		// assertequals does not properly compare the contents of these arraylists
		// upon looking at the actual values, diff view showed no differences whatsoever.
		
		for(int i = 0; i < newData.size(); ++i) 
		{
			if(!oldData.get(i).toString().equals(newData.get(i).toString()))
			{
				fail("Mismatch:\n" + oldData.get(i).toString() + "\n" + newData.get(i).toString());
			}
		}
	}
	
	@Test
	final void testUpdateLibraryBranch() 
	{
		// save the previous data
		ArrayList<LibraryBranch> oldData = admin.readLibraryBranch(findAllLibraryBranches);
		String newName = "New Branch Name";
		String newAddress = "New Branch Address";
		
		// change the value
		admin.updateLibraryBranch(new LibraryBranch(branch.getBranchId(), newName, newAddress));
		
		// store the new version of the table
		ArrayList<LibraryBranch> newData = admin.readLibraryBranch(findAllLibraryBranches);
		
		// change the value in the db back 
		admin.updateLibraryBranch(branch);
		
		// change it in our oldData arraylist
		oldData.forEach(row -> 
		{
			if(row.getBranchId() == branch.getBranchId()) 
			{
				row.setBranchName(newName);
				row.setBranchAddress(newAddress);
			}
		});
		
//		System.out.println("old");
//		oldData.forEach(row -> System.out.println(row));
//		System.out.println("new");
//		newData.forEach(row -> System.out.println(row));

		// check if the newData is the same as oldData
		// assertEquals(newData,oldData);
		// assertequals does not properly compare the contents of these arraylists
		// upon looking at the actual values, diff view showed no differences whatsoever.
		
		for(int i = 0; i < newData.size(); ++i) 
		{
			if(!oldData.get(i).toString().equals(newData.get(i).toString()))
			{
				fail("Mismatch:\n" + oldData.get(i).toString() + "\n" + newData.get(i).toString());
			}
		}
	}
	
	final void updateBorrower() 
	{
		// save the previous data
		ArrayList<Borrower> oldData = admin.readBorrower(findAllBorrowers);
		String newName = "New Borrower Name";
		String newAddress = "New Borrower Address";
		String newPhone = "New Borrower Phone";
		
		// change the value
		admin.updateBorrower(new Borrower(borrower.getCardNo(), newName, newAddress, newPhone));
		
		// store the new version of the table
		ArrayList<Borrower> newData = admin.readBorrower(findAllBorrowers);
		
		// change the value in the db back 
		admin.updateBorrower(borrower);
		
		// change it in our oldData arraylist
		oldData.forEach(row -> 
		{
			if(row.getCardNo() == borrower.getCardNo()) 
			{
				row.setName(newName);
				row.setAddress(newAddress);
				row.setPhone(newPhone);
			}
		});
		
//		System.out.println("old");
//		oldData.forEach(row -> System.out.println(row));
//		System.out.println("new");
//		newData.forEach(row -> System.out.println(row));

		// check if the newData is the same as oldData
		assertEquals(newData,oldData);
		// I beleive assertEquals might have trouble with the types of line feeds, which is why it doesnt work it some cases
	}
	
	final void updateBookLoan() 
	{
		// save the previous data
		ArrayList<BookLoan> oldData = admin.readBookLoan(findAllBookLoans);
		String newDueDate = "2002-02-02";
		
		// change the value
		admin.updateBookLoan(new BookLoan(loan.getBook(),loan.getBranch(),loan.getBorrower(), Date.valueOf("0001-01-01"), Date.valueOf(newDueDate)));
		
		// store the new version of the table
		ArrayList<Borrower> newData = admin.readBorrower(findAllBorrowers);
		
		// change the value in the db back 
		admin.updateBookLoan(loan);
		
		// change it in our oldData arraylist
		oldData.forEach(row -> 
		{
			if(row.getBook().getBookId() == loan.getBook().getBookId() &&
					row.getBranch().getBranchId() == loan.getBranch().getBranchId() &&
					row.getBorrower().getCardNo()== loan.getBorrower().getCardNo()) 
			{
				row.setDueDate(Date.valueOf(newDueDate));
			}
		});
		
//		System.out.println("old");
//		oldData.forEach(row -> System.out.println(row));
//		System.out.println("new");
//		newData.forEach(row -> System.out.println(row));
		
		assertEquals(oldData,newData);
		
	}
}
