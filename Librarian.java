
public class Librarian {
	
	private String name; //name of Librarian
	private int eId; // Librarian ID
	private LibrarySystem LibrarySystem; //System the Librarian belongs to
	
	//Librarian class constructor
	public Librarian(String name, int eId) {
		this.name = name;
		this.eId = eId;
		this.LibrarySystem = null;
	}

	//sets the system that the librarian belongs too
	public void setLibrarySystem(LibrarySystem LibrarySystem) {
		this.LibrarySystem = LibrarySystem;
	}
	
	// function that allows the librarian to check out a book to a customer
	public boolean checkOutBook(String ISBN, int customerID){
		Book book = LibrarySystem.getBook(ISBN);
		Customer customer = LibrarySystem.getCustomer(customerID);

		if ( book.isInStock() && 
			customer.getNumCheckedOut() < LibrarySystem.getMaxCheckedOut() &&
			!customer.isOverdue()) {
			customer.borrowBook(book);
			return true;
		}
		
		System.out.println("Error: Customer is unable to borrow book");
		return false;
		
	}
	
	// function that allows the librarian to return a book from a customer
	public boolean returnBook(String ISBN, int ID) {
		Book book = LibrarySystem.getBook(ISBN);
		Customer customer = LibrarySystem.getCustomer(ID);

		if (customer.hasBook(book)) {
			customer.returnBook(book);
			return true;
		}

		return false;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return eId;
	}

	

}
