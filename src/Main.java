import java.sql.SQLException;
import java.util.Scanner;

import com.ss.lms.presentation.*;

public class Main {

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		Presentation pres;
		String input;
		
		try 
		{
			do {
				System.out.println("/*MAIN > USERS**********************************************************************************************/");
				System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you?");
				System.out.println("1. Librarian");
				System.out.println("2. Administrator");
				System.out.println("3. Borrower");
				System.out.println("4. Quit");
				System.out.println("Enter your selection:");

				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
				input = scanner.nextLine();
				
				switch(input) {
				case "1":
					pres = new PresentationLibrarian();
					break;
				case "2":
					pres = new PresentationAdmin();
					break;
				case "3":
					//borrowerMenu = new PresentationBorrower();
					break;
				case "4":
					return;
				default:
					System.out.println("Enter a valid choice.");
				}

			}
			while(scanner.hasNextLine());
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}

