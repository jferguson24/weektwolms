package com.ss.lms.presentation;

import java.util.Scanner;

import com.ss.lms.service.Service;

public abstract class Presentation 
{
	protected final Scanner scanner;
	protected final Service service;
	
	public Presentation(Service service) 
	{
		this.scanner = new Scanner(System.in);
		this.service = service;
		this.menu();
		this.scanner.close();
		this.service.closeConnection();
	}
	
	public abstract void menu();
	
	public String getStringFieldFromUser(String fieldName) 
	{
		System.out.println("Insert data for " + fieldName + ". Enter \"quit\" to go back to operation screen");
		do
		{
			return scanner.nextLine();
		}
		while(scanner.hasNextLine());
	}
	
	public Integer getIntegerFieldFromUser() 
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
