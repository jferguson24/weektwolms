package com.ss.lms.entites;

import java.sql.Date;

public class EntityBookLoan {
	
	EntityBook book;
	EntityLibraryBranch branch;
	EntityBorrower borrower;
	Date dateOut;
	Date dueDate;
	
	public EntityBookLoan(EntityBook book, EntityLibraryBranch branch, EntityBorrower borrower, Date dateOut,Date dueDate) {
		this.book = book;
		this.branch = branch;
		this.borrower = borrower;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
	}
	
	/**
	 * @return the book
	 */
	public EntityBook getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(EntityBook book) {
		this.book = book;
	}

	/**
	 * @return the branch
	 */
	public EntityLibraryBranch getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(EntityLibraryBranch branch) {
		this.branch = branch;
	}

	/**
	 * @return the borrower
	 */
	public EntityBorrower getBorrower() {
		return borrower;
	}

	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(EntityBorrower borrower) {
		this.borrower = borrower;
	}

	public EntityBookLoan() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the dateOut
	 */
	public Date getDateOut() {
		return dateOut;
	}

	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
}