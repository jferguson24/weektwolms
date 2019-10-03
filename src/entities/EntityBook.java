package com.ss.lms.entities;

public class EntityBook {
	
	private Integer bookId;
	private String title;
	private Integer authorId;
	private Integer publisherId;
	
	public EntityBook() {
		
	}
	
	public EntityBook(Integer bookId, String title, Integer authorId, Integer publisherId) {
		this.bookId = bookId;
		this.title = title;
		this.authorId = authorId;
		this.publisherId = publisherId;
	}

	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return the pubisherId
	 */
	public Integer getPubisherId() {
		return publisherId;
	}

	/**
	 * @param pubisherId the pubisherId to set
	 */
	public void setPubisherId(Integer pubisherId) {
		this.publisherId = pubisherId;
	}


	
}