package com.ss.lms.entites;

public class EntityBookCopy {

	EntityBook book;
	EntityLibraryBranch branch;
	Integer noOfCopies;
	
	public EntityBookCopy() {
		
	}
	
	public EntityBookCopy(EntityBook book, EntityLibraryBranch branch, Integer noOfCopies) {
		this.book = book;
		this.branch = branch;
		this.noOfCopies = noOfCopies;
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
	 * @return the noOfCopies
	 */
	public Integer getNoOfCopies() {
		return noOfCopies;
	}

	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
	
}