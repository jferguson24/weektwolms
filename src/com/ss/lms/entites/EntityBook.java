package com.ss.lms.entites;

public class EntityBook {
	
	private Integer bookId;
	private String title;
	private EntityAuthor author;
	private EntityPublisher publisher;
	
	public EntityBook(Integer bookId, String title, EntityAuthor author, EntityPublisher publisher) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
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
	public EntityAuthor getAuthor() {
		return author;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthor(EntityAuthor author) {
		this.author= author;
	}
	
	/**
	 * @return the publisher
	 */
	public EntityPublisher getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(EntityPublisher publisher) {
		this.publisher = publisher;
	}

	public EntityBook() {
		
	}
	
}