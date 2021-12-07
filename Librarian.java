
public class Librarian {
	
	private String name;
	private int eId;
	private LibrarySystem LibrarySystem;
	
	public Librarian(String name, int eId) {
		this.name = name;
		this.eId = eId;
		this.LibrarySystem = null;
	}

	public void setLibrarySystem(LibrarySystem LibrarySystem) {
		this.LibrarySystem = LibrarySystem;
	}
	
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
	
	public boolean returnBook(String ISBN, int ID) {
		Book book = LibrarySystem.getBook(ISBN);
		Customer customer = LibrarySystem.getCustomer(ID);

		if (customer.hasBook(book)) {
			customer.returnBook(book);
			return true;
		}

		return false;
	}

	

}
