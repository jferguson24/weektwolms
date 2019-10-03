package com.ss.lms.presentation;

import java.util.Scanner;

import com.ss.lms.service.Service;

public interface Presentation 
{
	public static final Scanner scanner = new Scanner(System.in);
	public static final Service service = null;
	
	public void menu();
	
	default public String getStringFieldFromUser(String fieldName) 
	{
		System.out.println("Insert data for " + fieldName + ". Enter \"quit\" to go back to operation screen");
		do
		{
			return scanner.nextLine();
		}
		while(scanner.hasNextLine());
	}
	
	default public Integer getIntegerFieldFromUser() 
	{
		while(true) 
		{
			if(scanner.hasNextInt()) 
			{
				return scanner.nextInt();
			}
			else if("N/A".equals(scanner.next())) 
			{
				return Integer.MAX_VALUE;
			}
			else if("quit".equals(scanner.next())) 
			{
				return Integer.MIN_VALUE;
			}
			else
			{
				System.out.println("Publisher ID must be an integer");
			}
		}
	}
	
	
	
}
