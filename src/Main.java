import java.sql.SQLException;
import java.util.Scanner;

import com.ss.lms.dataaccess.*;
import com.ss.lms.presentation.*;
import com.ss.lms.service.*;

public class Main {

	/**
	 * @param args
	 */
    private static Scanner scanner = new Scanner( System.in );
//    static PresentationLibrarian libMenu;
//    static PresentationAdmin adminMenu;
//    static PresentationBorrower borrowerMenu;
	
	public static void main(String[] args) {
		System.out.println("Hi");
		try {
			startMenu();
		}
		catch(ClassNotFoundException|SQLException e)
		{
			System.out.println("Exc");
			e.printStackTrace();
		}
	}
	//
	public static void startMenu() throws ClassNotFoundException, SQLException {
		while(true) {
			System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you?");
			System.out.println("1. Librarian");
			System.out.println("2. Administrator");
			System.out.println("3. Borrower");
			System.out.println("4. Quit");
			System.out.println("Enter your selection:");
			boolean check = false;
			while(check == false) {
				String input = scanner.nextLine();
				
				switch(input) {
				case "1":
//					libMenu = new PresentationLibrarian();
					//libMenu.start();
					check = true;
					break;
				case "2":
					//adminMenu = new PresentationAdmin();
					//adminMenu.start();
					check = true;
					break;
				case "3":
					//borrowerMenu = new PresentationBorrower();
					//borrowerMenu.start();
					check = true;
					break;
				case "4":
					return;
				default:
					System.out.println("Enter a valid choice.");
				}
			}
		}
	}
}


