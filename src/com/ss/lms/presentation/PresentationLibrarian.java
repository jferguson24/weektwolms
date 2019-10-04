package com.ss.lms.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
//import java.sql.SQLException;
//import java.util.Arrays;
import java.util.Scanner;
//import java.util.stream.Stream;

import com.ss.lms.dataaccess.BookCopyDataAccess;
import com.ss.lms.dataaccess.BookDataAccess;
import com.ss.lms.dataaccess.DataAccess;
import com.ss.lms.dataaccess.LibraryBranchDataAccess;
import com.ss.lms.entity.*;
import com.ss.lms.service.*;

public class PresentationLibrarian extends Presentation {

	private static Scanner scanner;

	public PresentationLibrarian() throws SQLException, ClassNotFoundException {
		super(new UserLibrarian(new BookDataAccess(), new LibraryBranchDataAccess(), new BookCopyDataAccess()));
		
		scanner = new Scanner( System.in );
		
		menu();
		
	}
	//
	//Displays librarians choices and gets the user input
	public void menu() {
		while(true) {
			System.out.println("\n\nLibrarian Menu.");
			System.out.println("1. Enter a branch you manage");
			System.out.println("2. Quit to previous");
			boolean check = false;
			while (check == false) {
				String input = scanner.nextLine();
				switch(input) {
				case "1":
					branches();
					check = true;
					break;
				case "2":
					return;
				default:
					System.out.println("Enter a valid choice.");
				}
			}
		}
	}
	
	//branches() will allow the user to select which branch they want to interact with
	public void branches(){
		while(true) {
			ArrayList<LibraryBranch> branches;

			branches = librarian.readLibraryBranch(new LibraryBranch(-1,"%","%"));
			System.out.println("Choose your branch:");
			int i = 1;
			for(LibraryBranch branch : branches) {
				System.out.println(i + ") " + branch.getBranchName() + ", " + branch.getBranchAddress());
				i++;
			}
			System.out.println(i + ") Quit to previous");
//			Stream<String> branchStream = Arrays.stream(branches);
//			branchStream.forEach((str) -> System.out.println(str);
			

			System.out.println("Enter your branch:");
			int branchId = 0;
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("Enter your branch: ");
			    scanner.next();
			}

			branchId = scanner.nextInt();
			scanner.nextLine();
			if(branchId == i) {
				return;
			}
			for(LibraryBranch branch : branches)
			{
				if(branch.getBranchId() == branchId) {
					branchOptions(branch);
				}
			}
		}
	}
	
	//branchOptions will allow the user to select if they wish to update the details of the library
	//	or add copies of Book to the Branch
	public void branchOptions(LibraryBranch branch) {
		while(true) {
			System.out.println("1) Update the details of the Library");
			System.out.println("2) Add copies of Book to the Branch");
			System.out.println("3) Quit to previous");
			boolean check = false;
			while (check == false) {
				String input = scanner.nextLine();
				switch(input) {
				case "1":
					branchUpdate(branch);
					check = true;
					break;
				case "2":
					copies(branch.getBranchId());
					check = true;
					break;
				case "3":
					return;
				default:
					System.out.println("Invalid input.");
				}
			}
		}
	}
	
	//branchUpdate() takes in the selected branch entity
	//The user can then input changes they want to make and it will call on the service layer to update those changes
	public void branchUpdate(LibraryBranch branch) {
		System.out.println("You have chosen to update the Branch with Branch Id: " + branch.getBranchId() + " and Branch Name: " + branch.getBranchName());
		System.out.println("Enter 'quit' at any prompt to cancel operation.");
		System.out.println("Please enter new branch name or enter N/A for no change:");
		String name = scanner.nextLine();
		if("quit".equals(name)) {
			return;
		}
		System.out.println("Please enter new branch address or enter N/A for no change:");
		String address = scanner.nextLine();
		if("quit".equals(address)) {
			return;
		}
		if(!"N/A".equals(name) && !"N/A".equals(address)) {
			System.out.println("Updating Name and Address.");
			if(librarian.updateLibrary(branch.getBranchId(), name, address)) {
				System.out.println("Update Successful");
			}
		}
		else if(!"N/A".equals(address)) {
			System.out.println("Updating Address.");
			if(librarian.updateLibraryAddress(branch.getBranchId(), address)) {
				System.out.println("Update Successful");
			}
		}
		else if(!"N/A".equals(name)) {
			System.out.println("Updating Name.");
			if(librarian.updateLibraryName(branch.getBranchId(), name)) {
				System.out.println("Update Successful");
			}
		}
		else {
			System.out.println("Nothing to update.");
		}
	}
	
	public void copies(int branchId) {
		System.out.println("Pick the Book you want to add copies of, to your branch:");
		while(true) {
			ArrayList<Book> copies = librarian.getBookEntities();

			//Choosing which book you want to add copies of
			System.out.println("Choose your Book:");
			int i = 1;
			for(Book book : copies) {
				System.out.println(i + ") " + book.getTitle() + " by " + librarian.getAuthorName(book));
				i++;
			}
			System.out.println(i + ") Quit to previous");
			System.out.println("Enter your book:");
			
			//Getting a valid integer book ID
			int bookId = 0;
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("Enter your book: ");
			    scanner.next();
			}
			bookId = scanner.nextInt();
			scanner.nextLine();
			
			//Creating a book with the information given to pass the supporting functions

			//Checking if the entered value is the quit option
			if(bookId == i) {
				return;
			}
			//If the entered value is within the available id's then it will go on to add copies
			if(bookId <= copies.size()) {
				addCopies(copies.get(bookId-1), branchId);
			}
		}
	}
	
	//addCopies gets the new number of copies desired and calls the service to update the database
	public void addCopies(Book book, int branchId) {
		System.out.println("Existing number of books: " + librarian.getNumberOfCopies(book, branchId));
		System.out.println("Enter new number of copies: ");
		int numCopies= 0;
		//Gets a valid integer for the new number of copies
		while(!scanner.hasNextInt()) {
			System.out.println("Please enter a valid Integer.");
			System.out.print("Enter your book: ");
		    scanner.next();
		}

		numCopies = scanner.nextInt();
		scanner.nextLine();
		librarian.changeCopies(book, branchId, numCopies);
		
	}
	
}
