package com.ss.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.dataaccess.*;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopy;
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
			LibraryBranchDataAccess libraryBranchDao, BorrowerDataAccess borrowerDao,
			BookCopyDataAccess bookCopyDao, BookLoanDataAccess bookLoanDao) 
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
			authorDao.insert(author);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}


	public <T> Integer generatePrimaryKey(DataAccess<T> T) 
	{
		return null;
	}
	
	@Override
	public void createPublisher(Publisher publisher) 
	{
		try 
		{
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
				// TODO left off here.
				switch(book.getAuthor().getAuthorId()) 
				{
				case -1: // if the user sent in a % or -1, leave the old data as is, else use the user's data
					ArrayList<Author> authorData = authorDao.find(new Author(book.getAuthor().getAuthorId(), "%"));
					book.setAuthor(new Author(authorData.get(0).getAuthorId(), authorData.get(0).getAuthorName()));
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
