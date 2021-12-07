import java.util.*;

public class Book {
    private String title; //title of book
    private String author; //author of book
    private String ISBN; // book ISBN number
    private int borrower; // ID of customer that borrows book
    private boolean inStockStatus; // shows if the book is borrowed or in stock
    private Date date; // the date
	private Date returnDate; // the date that the book should be returned, if borrowed.
	private boolean overdue; // if the book is overdue
	private Calendar c; //java object that converts time to month, date, and year.
	private LibrarySystem LibrarySystem; //the system of the library that the book belongs too

	//Book object constructor
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.borrower = -1;
        this.inStockStatus = true;
    }

    //sets the library system that the book belongs too
	public void setLibrarySystem(LibrarySystem LibrarySystem) {
		this.LibrarySystem = LibrarySystem;
	}
    
	// gets the Customer ID of whoever borrowed the book
    public int getBorrower() {
    	return borrower; 
    }
    
    // function that gets called from the Librarian class to allow them to let customer borrow books.
    public void borrowBook(int x) {
    	borrower = x;
    	inStockStatus = false;
    	this.c = Calendar.getInstance();
		this.date = c.getTime();
		c.add(Calendar.DATE, 7); //assuming that it increments month when month passes
		this.returnDate = c.getTime();
		this.overdue = false;
		
    }
    
    // function that gets called from the Librarian class that allows them to mark books as returned.
    public void returnBook(){
    	borrower = -1; 
    	inStockStatus = true;
    	this.returnDate = null;
    }
    
    //boolean function that shows if the book is borrowed or in stock.
    public boolean isInStock() {
    	return inStockStatus;
    }
    
    //checks if the book is overdue. If the book is overdue, then it marks it as overdue.
    public boolean isOverDue()	
	{
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		int i = today.compareTo(this.returnDate);
		if(i > 0)
		{
			this.overdue = true;
			
			Customer customer = LibrarySystem.getCustomer(borrower); 
			customer.addOverdueBook(this);
			
			return this.overdue;
		}
		
		else
		{
			return this.overdue;
		}
		
	}

    // gets the ISBN number of the book
	public String getISBN() {
		return ISBN;
	}
    
}
