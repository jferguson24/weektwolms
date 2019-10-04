package com.ss.lms.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.Date;

import com.ss.lms.service.*;

public abstract class Presentation 
{
	protected final Scanner scanner;
	protected ServiceAdmin administrator;
	protected ServiceLibrarian librarian;
	protected ServiceBorrower borrower;
	
	public Presentation(ServiceAdmin administrator)
	{
		this.scanner = new Scanner(System.in);
		this.administrator = administrator;
		this.menu();
		this.scanner.close();
		this.administrator.closeConnection();
	}
	
	public Presentation(ServiceLibrarian librarian)
	{
		this.scanner = new Scanner(System.in);
		this.librarian = librarian;
		this.menu();
		this.scanner.close();
		this.librarian.closeConnection();
	}
	
	public Presentation(ServiceBorrower borrower)
	{
		this.scanner = new Scanner(System.in);
		this.borrower = borrower;
		this.menu();
		this.scanner.close();
		this.borrower.closeConnection();
	}
	
	public abstract void menu();
	
	// Forces the user to input a String. replaces "N/A" with "%"
	public String getStringFieldFromUser(String fieldName) 
	{
		System.out.println("Insert data for " + fieldName + ". Enter \"quit\" to go back to operation screen");
		do
		{
			return scanner.nextLine().replaceAll("N/A", "%");
		}
		while(scanner.hasNextLine());
	}
	
	// Forces the user to input an integer, "N/A" maps to %, "quit" maps to Integer.MIN_VALUE
	public Integer getIntegerFieldFromUser(String fieldName) 
	{
		while(true) 
		{
			if(scanner.hasNextInt()) 
			{
				return scanner.nextInt();
			}
			else if("N/A".equals(scanner.next())) 
			{
				return -1;
			}
			else if("quit".equals(scanner.next())) 
			{
				return Integer.MIN_VALUE;
			}
			else
			{
				System.out.println(fieldName + " must be an integer");
			}
		}
	}
	
	// returns a string representation of a 2d array, with brackets
	public static String make2DArrayListLegible(ArrayList<ArrayList<String>> input) 
	{	
		StringBuilder output = new StringBuilder();

		input.stream().forEach
		(
			row -> output.append(Arrays.toString(row.toArray()) + "\n")
		);
		
		return output.toString();
	}
}
