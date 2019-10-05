package com.ss.lms.presentation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.dataaccess.BookCopyDataAccess;
import com.ss.lms.dataaccess.BookLoanDataAccess;
import com.ss.lms.dataaccess.BorrowerDataAccess;
import com.ss.lms.dataaccess.LibraryBranchDataAccess;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;
import com.ss.lms.service.UserBorrower;

public class PresentationBorrower extends Presentation {

	public PresentationBorrower() throws ClassNotFoundException, SQLException {
		super(new UserBorrower(new LibraryBranchDataAccess(),new BorrowerDataAccess(),
			new BookCopyDataAccess(), new BookLoanDataAccess()));
		}
	
	
	public boolean validate() {
		int attempts = 0;
		do {
			System.out.println("Enter your Card Number: ");
			if(scanner.hasNext()) {
				if(scanner.nextInt() == 3)
					return true; 
				else
					System.out.println("Invalid Card Number");
			}
			else {
				scanner.nextLine();
				attempts++;
			}
		}while(attempts < 3);
		
		return false;
}
	
	public void checkOut() {
		System.out.println("Enter branch you wish to check out from");
		
		Borrower bow = new Borrower(2, "Jeff", "CA", "412");
		Publisher pub = new Publisher(55, "Loop", "DA", "545");
		Author auth = new Author(62, "Ricky");
		Book bok = new Book(2, "Uptown", auth, pub);
		LibraryBranch branch = new LibraryBranch(2, "Soup", "Gibby");
		Date dateOut = new Date(1994, 12, 5);
		Date dueDate = new Date(1994, 12, 5);;
		
		
		BookLoan bol = new BookLoan(bok, branch, bow, dateOut, dueDate);
		BookLoanDataAccess DaoBook = null;
		ArrayList<BookLoan>  check = null;
		try {
			 DaoBook = new BookLoanDataAccess();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			check = DaoBook.find(bol);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(check.get(0).getBorrower().getName());
	}
	public void returnBook() {
		System.out.println("Enter branch you wish to return book to");
		// branches
	}

	@Override
	public void menu() {
		// TODO Auto-generated method stub
		if(validate())
			System.out.println("its a number");
		else
			System.out.println("its a character");
		boolean check = false;
		do{
			System.out.println("Welcome to the Library's Borrowing Menu.");
			System.out.println("1) Check out a Book");
			System.out.println("2) Return a Book");
			System.out.println("3) Quit to previous");
			
			if(scanner.hasNextInt()) {
				
				switch(super.scanner.nextInt()) {
					case 1:
					//TODO service layer get branches
						checkOut();
						check = true;
						break;
					case 2:
						
						returnBook();
						check = true;
						break;
					case 3:
						return;
					default:
						System.out.println("Enter a valid choice.");
				}
			}
			else {
				System.out.println("Invalid character");
				super.scanner.nextLine();
			}
			
		}while (check == false);

	}
	
	
}
