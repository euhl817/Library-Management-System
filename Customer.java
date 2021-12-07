import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
 

public class Customer {
	private String name;								//Customer name
	private int ID;										//Customer ID
	private HashMap<String, Book> nonOverdueBooks;		//list of books that are not overdue
	private HashMap<String, Book> overdueBooks;			//list of books that are overdue
	private int numCheckedout;							//number of books checked out
	private LibrarySystem LibrarySystem;				
	private String phoneNumber;							//Customer phone number
	private String emailAddress;						//Customer email address
	
	/*
	 * Customer - initializes a customer
	 */
	public Customer(String name, int ID, String phoneNumber, String emailAddress)
	{
		this.name = name;
		this.ID = ID;
		this.LibrarySystem = null;
		this.nonOverdueBooks = new HashMap<>();
		this.overdueBooks = new HashMap<>();
		this.numCheckedout = nonOverdueBooks.size() + overdueBooks.size();
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;

		
	}
	
	/*
	 * getNumCheckedOut - returns the number of checked out books
	 */
	public int getNumCheckedOut() {
		return numCheckedout;
	}

	/*
	 * setLibrarySystem - sets an instance of a LibrarySystem
	 */
	public void setLibrarySystem(LibrarySystem LibrarySystem) {
		this.LibrarySystem = LibrarySystem;
	}

	/*
	 * getLibrarySystem - returns the LibrarySystem
	 */
	public LibrarySystem getLibrarySystem() {
		return LibrarySystem;
	}

	/*
	 * borrowBook - lends a book out to a customer
	 */
	public void borrowBook(Book book) {
		book.borrowBook(ID);
		nonOverdueBooks.put(book.getISBN(), book);
	}

	/*
	 * returnBook - returns a book back to the library
	 */
	public void returnBook(Book book) {
		book.returnBook();
		if (book.isOverDue()) {
			overdueBooks.remove(book.getISBN());
		} else {
			nonOverdueBooks.remove(book.getISBN());
		}
	}
	
	/*
	 * isOverdue - returns a boolean based on whether a book is overdue or not
	 */
	public boolean isOverdue() {
		return !overdueBooks.isEmpty();
	}

	/*
	 * hasBook - returns a boolean based on whether a book is contained within the library
	 */
	public boolean hasBook(Book book) {
		return nonOverdueBooks.containsKey(book.getISBN()) || 
		overdueBooks.containsKey(book.getISBN());
	}

	/*
	 * addOverdueBook - adds a book to the list of overdue books when called
	 */
	public void addOverdueBook(Book book) {
		nonOverdueBooks.remove(book.getISBN());
		overdueBooks.put(book.getISBN(), book);
	}

	/*
	 * checkOverdueStatus - checks whether any of the books that have been lent out have become overdue 
	 */
	public void checkOverdueStatus() {
		for (Book book : nonOverdueBooks.values()) {
			book.isOverDue();
		}
	}



}
