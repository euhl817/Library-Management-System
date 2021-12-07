import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
 

public class Customer {
	private String name;
	private int ID;
	private HashMap<String, Book> nonOverdueBooks;
	private HashMap<String, Book> overdueBooks;
	private int numCheckedout;
	private LibrarySystem LibrarySystem;
	private String phoneNumber;
	private String emailAddress;
	
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

	public int getNumCheckedOut() {
		return numCheckedout;
	}

	public void setLibrarySystem(LibrarySystem LibrarySystem) {
		this.LibrarySystem = LibrarySystem;
	}

	public LibrarySystem getLibrarySystem() {
		return LibrarySystem;
	}

	public void borrowBook(Book book) {
		book.borrowBook(ID);
		nonOverdueBooks.put(book.getISBN(), book);
	}

	public void returnBook(Book book) {
		book.returnBook();
		if (book.isOverDue()) {
			overdueBooks.remove(book.getISBN());
		} else {
			nonOverdueBooks.remove(book.getISBN());
		}
	}
	
	public boolean isOverdue() {
		return !overdueBooks.isEmpty();
	}

	public boolean hasBook(Book book) {
		return nonOverdueBooks.containsKey(book.getISBN()) || 
		overdueBooks.containsKey(book.getISBN());
	}

	public void addOverdueBook(Book book) {
		nonOverdueBooks.remove(book.getISBN());
		overdueBooks.put(book.getISBN(), book);
	}

	public void checkOverdueStatus() {
		for (Book book : nonOverdueBooks.values()) {
			book.isOverDue();
		}
	}



}
