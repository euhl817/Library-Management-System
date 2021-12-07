public class Administrator {
    private String name;
    private String employeeID;
    private LibrarySystem LibrarySystem; 

    public Administrator(String name, String employeeID) {
        this.name = name;
        this.employeeID = employeeID;
        this.LibrarySystem = null;
    }

    public void setLibrarySystem(LibrarySystem LibrarySystem) {
        this.LibrarySystem = LibrarySystem;
    }

    public void addBook(String title, String author, String ISBN) {
        Book book = new Book(title, author, ISBN);
        LibrarySystem.addBook(ISBN, book);
    }

    public void removeBook(String ISBN) {
        LibrarySystem.removeBook(ISBN);
    }

    public void addCustomer(String name, String phone, String email ) {
        int ID = LibrarySystem.getNewCustomerID();
        Customer customer = new Customer(name, ID, phone, email);
        LibrarySystem.addCustomer(customer, ID);
    }

    public void removeCustomer(int ID) {
        LibrarySystem.removeCustomer(ID);
    }

    public void addLibrarian(String name) {
        int ID = LibrarySystem.getNewLibrarianID();
        Librarian librarian = new Librarian(name, ID);
        LibrarySystem.addLibrarian(librarian, ID);

    }

    public void removeLibrarian(int ID) {
        LibrarySystem.removeLibrarian(ID);
    }
}