package com.ss.lms.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.dataaccess.AuthorDataAccess;
import com.ss.lms.dataaccess.BookDataAccess;
import com.ss.lms.dataaccess.BookLoanDataAccess;
import com.ss.lms.dataaccess.BorrowerDataAccess;
import com.ss.lms.dataaccess.DataAccess;
import com.ss.lms.dataaccess.LibraryBranchDataAccess;
import com.ss.lms.dataaccess.PublisherDataAccess;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;

public class UserAdmin implements ServiceAdmin
{
	private DataAccess<Author> authorDao;
	private DataAccess<Publisher> publisherDao;
	private DataAccess<Book> bookDao;
	private DataAccess<LibraryBranch> libraryBranchDao;
	private DataAccess<Borrower> borrowerDao;
	private DataAccess<BookLoan> bookLoanDao;
	
	public UserAdmin(
			AuthorDataAccess authorDao, PublisherDataAccess publisherDao, BookDataAccess bookDao,
			LibraryBranchDataAccess libraryBranchDao, BorrowerDataAccess borrowerDao, BookLoanDataAccess bookLoanDao) 
	{
		this.authorDao = authorDao;
		this.publisherDao = publisherDao;
		this.bookDao = bookDao;
		this.libraryBranchDao = libraryBranchDao;
		this.borrowerDao = borrowerDao;
		this.bookLoanDao = bookLoanDao;
	}
	
	public void closeConnection() 
	{
		try 
		{
			authorDao.close();
			publisherDao.close();
			bookDao.close();
			libraryBranchDao.close();
			borrowerDao.close();
			bookLoanDao.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void createAuthor(Author author) 
	{
		try 
		{
			// get a unique primary key
			ArrayList<Author> existing = authorDao.find(new Author(-1,"%"));
			ArrayList<Integer> keys = new ArrayList<Integer>();
			Integer newKey = 0;
			
			for(Author auth: existing) 
			{
				keys.add(auth.getAuthorId());
			}
			
	        do 
	        {
	            newKey++;
	        }
	        while(keys.contains(newKey));
	        
	        // assigning new key
	        author.setAuthorId(newKey);
			
	        // creating new author entry
			authorDao.insert(author);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void createPublisher(Publisher publisher) 
	{
		try 
		{
			// get a unique primary key
			ArrayList<Publisher> existing = publisherDao.find(new Publisher(-1,"%","%","%"));
			ArrayList<Integer> keys = new ArrayList<Integer>();
			Integer newKey = 0;
			
			for(Publisher pub: existing) 
			{
				keys.add(pub.getPublisherId());
			}
			
	        do 
	        {
	            newKey++;
	        }
	        while(keys.contains(newKey));
	        
	        // assigning new key
	        publisher.setPublisherId(newKey);
			
	        // creating new publisher entry
			publisherDao.insert(publisher);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void createBook(Book book) 
	{
		try 
		{
			// get a unique primary key
			ArrayList<Book> existing = bookDao.find(new Book(-1,"%", new Author(-1, "%"), new Publisher(-1, "%", "%", "%")));
			ArrayList<Integer> keys = new ArrayList<Integer>();
			Integer newKey = 0;
			
			for(Book row: existing) 
			{
				keys.add(row.getBookId());
			}
			
	        do 
	        {
	            newKey++;
	        }
	        while(keys.contains(newKey));
	        
	        // assigning new key
	        book.setBookId(newKey);
	        
	        // A new book much have exisiting correspinding publisher and author ID's
	        // check the pub and auths exist
	        
	        ArrayList<Author> authorResult = authorDao.find(new Author(book.getAuthor().getAuthorId(), "%"));
	        if(authorResult.size() != 1) 
	        {
	        	System.out.println("Unique Author ID for " + book.getAuthor().getAuthorId()+ " couldn't be found.");
	        	return;
	        }
	        
	        ArrayList<Publisher> publisherResult = publisherDao.find(new Publisher(book.getPublisher().getPublisherId(), "%", "%", "%"));
	        if(publisherResult.size() != 1) 
	        {
	        	System.out.println("Unique Publisher ID for " + book.getPublisher().getPublisherId()+ " couldn't be found.");
	        	return;
	        }
			
	        // creating new book entry
			bookDao.insert(book);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void createLibraryBranch(LibraryBranch libraryBranch) 
	{
		try 
		{
			// get a unique primary key
			ArrayList<LibraryBranch> existing = libraryBranchDao.find(new LibraryBranch(-1,"%", "%"));
			ArrayList<Integer> keys = new ArrayList<Integer>();
			Integer newKey = 0;
			
			for(LibraryBranch row: existing) 
			{
				keys.add(row.getBranchId());
			}
			
	        do 
	        {
	            newKey++;
	        }
	        while(keys.contains(newKey));
	        
	        // assigning new key
	        libraryBranch.setBranchId(newKey);
			
	        // creating new library branch
			libraryBranchDao.insert(libraryBranch);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void createBorrower(Borrower borrower) 
	{
		try 
		{
			// get a unique primary key
			ArrayList<Borrower> existing = borrowerDao.find(new Borrower(-1,"%", "%", "%"));
			ArrayList<Integer> keys = new ArrayList<Integer>();
			Integer newKey = 0;
			
			for(Borrower row: existing) 
			{
				keys.add(row.getCardNo());
			}
			
	        do 
	        {
	            newKey++;
	        }
	        while(keys.contains(newKey));
	        
	        // assigning new key
	        borrower.setCardNo(newKey);
			
	        // creating new borrower
			borrowerDao.insert(borrower);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Author> readAuthor(Author author) 
	{
		try 
		{
			return authorDao.find(author);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return new ArrayList<Author>();
		}
	}

	@Override
	public ArrayList<Publisher> readPublisher(Publisher publisher) 
	{
		try 
		{
			return publisherDao.find(publisher);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return new ArrayList<Publisher>();
		}
	}

	@Override
	public ArrayList<Book> readBook(Book book) 
	{
		try 
		{
			return bookDao.find(book);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return new ArrayList<Book>();
		}
	}

	@Override
	public ArrayList<LibraryBranch> readLibraryBranch(LibraryBranch libraryBranch) 
	{
		try 
		{
			return libraryBranchDao.find(libraryBranch);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return new ArrayList<LibraryBranch>();
		}
	}

	@Override
	public ArrayList<Borrower> readBorrower(Borrower borrower) 
	{
		try 
		{
			return borrowerDao.find(borrower);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return new ArrayList<Borrower>();
		}
	}

	@Override
	public ArrayList<BookLoan> readBookLoan(BookLoan bookLoan) 
	{
		try 
		{
			return bookLoanDao.find(bookLoan);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return new ArrayList<BookLoan>();
		}
	}

	@Override
	public void updateAuthor(Author author) 
	{
		try
		{
			ArrayList<Author> oldData = authorDao.find( new Author(author.getAuthorId(), "%") );
			
			if(oldData.size() != 1)
			{
				System.out.println("Unique Author ID for " + author.getAuthorId()+ " couldn't be found.");
				return;
			}
			else 
			{
				switch(author.getAuthorName()) 
				{
				case "%": // if the user sent in a %, leave the old data as is, else use the user's data
					author.setAuthorName(oldData.get(0).getAuthorName());
					break;
				}
			}
			
			// write to the db the filled out author 
			authorDao.update(author);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void updatePublisher(Publisher publisher) 
	{
		try 
		{
			ArrayList<Publisher> oldData = publisherDao.find(new Publisher(publisher.getPublisherId(), "%", "%", "%"));
			
			if(oldData.size() != 1) 
			{
				System.out.println("Unique Publisher ID for " + publisher.getPublisherId()+ " couldn't be found.");
				return;
			}
			else 
			{
				switch(publisher.getPublisherName()) 
				{
				case "%": // if the user sent in a %, leave the old data as is, else use the user's data
					publisher.setPublisherName(oldData.get(0).getPublisherName());
					break;
				}

				switch(publisher.getPublisherAddress()) 
				{
				case "%": // if the user sent in a %, leave the old data as is, else use the user's data
					publisher.setPublisherAddress(oldData.get(0).getPublisherAddress());
					break;
				}
				switch(publisher.getPublisherPhone()) 
				{
				case "%": // if the user sent in a %, leave the old data as is, else use the user's data
					publisher.setPublisherPhone(oldData.get(0).getPublisherPhone());
					break;
				}
			}
			
			// write to the db the filled out author 
			publisherDao.update(publisher);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void updateBook(Book book) 
	{
		try 
		{
			// Make sure the new Ids exist
			if(authorDao.find(new Author( book.getAuthor().getAuthorId(), "%")).size() < 1)
			{
				System.out.println("Unique Author ID for " + book.getAuthor().getAuthorId() + " couldn't be found.");
				return;
			}
			
			if(publisherDao.find(new Publisher( book.getPublisher().getPublisherId(), "%", "%", "%")).size() < 1)
			{
				System.out.println("Unique Publisher ID for " + book.getPublisher().getPublisherId() + " couldn't be found.");
				return;
			}
			
			ArrayList<Book> oldData = bookDao.find(new Book(
					book.getBookId(),
					"%",
					new Author(-1, "%"),
					new Publisher(-1,"%","%","%")));
			
			if(oldData.size() != 1) 
			{
				System.out.println("Unique Book ID for " + book.getBookId()+ " couldn't be found.");
				return;
			}
			else 
			{
				switch(book.getTitle()) 
				{
				case "%":
					book.setTitle(oldData.get(0).getTitle());
				}
				
				switch(book.getAuthor().getAuthorId()) 
				{
				case -1: // if the user sent in a -1, leave the old data as is, else use the user's data
					book.setAuthor(oldData.get(0).getAuthor());
					break;
				}
				
				switch(book.getPublisher().getPublisherId())
				{
				case -1: // if the user sent in a -1, leave the old data as is, else use the user's data
					book.setPublisher(oldData.get(0).getPublisher());
					break;
				}
			}
			
			bookDao.update(book);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void updateLibraryBranch(LibraryBranch libraryBranch) 
	{
		try 
		{
			ArrayList<LibraryBranch> oldData = libraryBranchDao.find( new LibraryBranch(libraryBranch.getBranchId(), "%", "%") );
			
			if(oldData.size() != 1)
			{
				System.out.println("Unique Branch ID for " + libraryBranch.getBranchId()+ " couldn't be found.");
				return;
			}
			else 
			{
				switch(libraryBranch.getBranchName()) 
				{
				case "%": // if the user sent in a %, leave the old data as is, else use the user's data
					libraryBranch.setBranchName(oldData.get(0).getBranchName());
					break;
				}
				
				switch(libraryBranch.getBranchAddress()) 
				{
				case "%": // if the user sent in a %, leave the old data as is, else use the user's data
					libraryBranch.setBranchAddress(oldData.get(0).getBranchAddress());
					break;
				}
			}
			
			libraryBranchDao.update(libraryBranch);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void updateBorrower(Borrower borrower) 
	{
		try 
		{
			ArrayList<Borrower> oldData = borrowerDao.find( new Borrower(borrower.getCardNo(), "%", "%", "%") );
			
			if(oldData.size() != 1)
			{
				System.out.println("Unique Card Number for " + borrower.getCardNo()+ " couldn't be found.");
				return;
			}
			else 
			{
				switch(borrower.getName()) 
				{
				case "%": // if the user sent in a %, leave the old data as is, else use the user's data
					borrower.setName(oldData.get(0).getName());
					break;
				}
				
				switch(borrower.getAddress()) 
				{
				case "%": // if the user sent in a %, leave the old data as is, else use the user's data
					borrower.setAddress(oldData.get(0).getAddress());
					break;
				}
				
				switch(borrower.getPhone()) 
				{
				case "%": // if the user sent in a %, leave the old data as is, else use the user's data
					borrower.setPhone(oldData.get(0).getPhone());
					break;
				}
			}
			
			borrowerDao.update(borrower);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void updateBookLoan(BookLoan bookLoan) 
	{
		try 
		{
			// make sure new ids exist
			ArrayList<Book> bookQueryResult = bookDao.find(new Book( bookLoan.getBook().getBookId(), "%", new Author(-1,"%"), new Publisher(-1, "%", "%", "%")));
			if(bookQueryResult.size() != 1)
			{
				System.out.println("Unique Book ID for " + bookLoan.getBook().getBookId() + " couldn't be found.");
				return;
			}
			
			ArrayList<LibraryBranch> libraryBranchResult = libraryBranchDao.find(new LibraryBranch(bookLoan.getBranch().getBranchId(), "%", "%"));
			if(libraryBranchResult.size() != 1)
			{
				System.out.println("Unique Branch ID for " + bookLoan.getBranch().getBranchId() + " couldn't be found.");
				return;
			}
			
			ArrayList<Borrower> borrowerResult = borrowerDao.find(new Borrower(bookLoan.getBorrower().getCardNo(), "%", "%", "%"));
			if(borrowerResult.size() != 1)
			{
				System.out.println("Unique Card Number for " + bookLoan.getBorrower().getCardNo() + " couldn't be found.");
				return;
			}
			
			ArrayList<BookLoan> oldData = bookLoanDao.find( new BookLoan(
					bookQueryResult.get(0),
					libraryBranchResult.get(0),
					borrowerResult.get(0),
					Date.valueOf("0001-01-01"), Date.valueOf("0001-01-01"))
					);
			
			if(oldData.size() != 1)
			{
				System.out.println("Unique Book Loan with:"
						+ "\nBook:\t" + bookQueryResult.get(0).getBookId() + "\t" + bookQueryResult.get(0).getTitle()  
						+ "\nBranch:\t" + libraryBranchResult.get(0).getBranchId() + "\t" + libraryBranchResult.get(0).getBranchName() 
						+ "\nBorrower:\t" + borrowerResult.get(0).getCardNo() + "\t" + borrowerResult.get(0).getName() 
						+ "\ncouldn't be found.");
				return;
			}
			else 
			{
				// if the user sent a due date 0001-01-01, use existing data 
				if(Date.valueOf("0001-01-01").equals(bookLoan.getDueDate()))
				{
					bookLoan.setDueDate(oldData.get(0).getDueDate());
				}
				
				bookLoan.setDateOut(oldData.get(0).getDateOut());
			}
			
			bookLoanDao.update(bookLoan);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAuthor(Author author) 
	{
		try 
		{
			authorDao.delete(author);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void deletePublisher(Publisher publisher) 
	{
		try 
		{
			publisherDao.delete(publisher);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBook(Book book) 
	{
		try 
		{
			bookDao.delete(book);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void deleteLibraryBranch(LibraryBranch libraryBranch) 
	{
		try 
		{
			libraryBranchDao.delete(libraryBranch);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBorrower(Borrower borrower) 
	{
		try 
		{
			borrowerDao.delete(borrower);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
