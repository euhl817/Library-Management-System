import java.util.*;

public class Book {
    private String title;
    private String author;
    private String ISBN;
    private int borrower;
    private boolean inStockStatus;
    private Date date;
	private Date returnDate;
	private boolean overdue;
	private Calendar c;
	private LibrarySystem LibrarySystem;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.borrower = -1;
        this.inStockStatus = true;
    }

	public void setLibrarySystem(LibrarySystem LibrarySystem) {
		this.LibrarySystem = LibrarySystem;
	}
    
    public int getBorrower() {
    	return borrower; 
    }
    
    public void borrowBook(int x) {
    	borrower = x;
    	inStockStatus = false;
    	this.c = Calendar.getInstance();
		this.date = c.getTime();
		c.add(Calendar.DATE, 7); //assuming that it increments month when month passes
		this.returnDate = c.getTime();
		this.overdue = false;
		
    }
    
    public void returnBook(){
    	borrower = -1; 
    	inStockStatus = true;
    	this.returnDate = null;
    }
    
    public boolean isInStock() {
    	return inStockStatus;
    }
    
    public boolean isOverDue()	//where to call this function?
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

	public void checkOverdue() {
		
	}

	public String getISBN() {
		return ISBN;
	}
    
}
