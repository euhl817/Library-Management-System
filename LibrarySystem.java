import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LibrarySystem {
    private HashMap<Integer, Customer> customers;
    private HashMap<Integer, Librarian> librarians;
    private HashMap<String, Book> books;
    private Administrator administrator;
    private int lastCustomerID; // running count of how many customers have been added
    private int lastLibrarianID; // running count of how many librarians have been added
    private int maxCheckedOut; // max amount of books each customer can check out
    private int maxDaysBorrowed; // max amount for days a book can be checked out
    private overdueStatusChecker overdueChecker; // runnable which checks overdue status of books every 24 hours
    
    public LibrarySystem(int maxCheckedOut, int maxDaysBorrowed) {
        this.customers = new HashMap<>();
        this.librarians = new HashMap<>();
        this.books = new HashMap<>();
        this.administrator = null;
        this.lastCustomerID = -1;
        this.lastLibrarianID = -1;
        this.maxCheckedOut = maxCheckedOut;
        this.maxDaysBorrowed = maxDaysBorrowed;

        ScheduledExecutorService executorService;
        overdueChecker = new overdueStatusChecker(this);
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(overdueChecker, 0, 1, TimeUnit.DAYS); // tells runnable to execute every 24 hours
    }

    // adds administrator to system
    public void addAdministrator(Administrator administrator) {
        this.administrator = administrator;
        administrator.setLibrarySystem(this);
    }

    // adds book to system
    public void addBook(String ISBN, Book book) {
        books.put(ISBN, book);
        book.setLibrarySystem(this);
    }

    // removes book from system
    public void removeBook(String ISBN) {
        books.get(ISBN).setLibrarySystem(null);
        books.remove(ISBN);
    }

    // adds customer to system
    public void addCustomer(Customer customer, int ID) {
        customers.put(ID, customer);
        customer.setLibrarySystem(this);
    }

    // removes customer from system
    public void removeCustomer(int ID) {
        customers.get(ID).setLibrarySystem(null);
        customers.remove(ID);
    }

    // adds librarian to system
    public void addLibrarian(Librarian librarian, int ID) {
        librarians.put(ID, librarian);
        librarian.setLibrarySystem(this);
    }

    // removes librarian from system
    public void removeLibrarian(int ID) {
        librarians.get(ID).setLibrarySystem(null);
        librarians.remove(ID);
    }


    // returns ID for newly added customer
    public int getNewCustomerID() {
        return ++lastCustomerID;
    }

    // returns ID for newly added librarian
    public int getNewLibrarianID() {
        return ++lastLibrarianID;
    }

    // returns max number
    public int getMaxCheckedOut() {
        return maxCheckedOut;
    }

    // returns max days borrowed
    public int getMaxDaysBorrowed() {
        return maxDaysBorrowed;
    }

    // returns customer based on ID
    public Customer getCustomer(int ID) {
        return customers.get(ID);
    }

    // returns librarian based on ID
    public Librarian getLibrarian(int ID) {
        return librarians.get(ID);
    }

    // returns book based on ISBN
    public Book getBook(String ISBN) {
        return books.get(ISBN);
    }

    public HashMap<Integer, Customer> getCustomers() {
        return customers;
    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem(10, 14);
        Administrator admin = new Administrator("Eugene", "1");
        library.addAdministrator(admin);
        admin.addLibrarian("Andrew");
        admin.addCustomer("June", "8083923815", "june@bu.edu");
        admin.addBook("Harry Potter", "J.K Rowling", "0001");
        admin.addBook("1984", "George Orwell", "0002");
        admin.addBook("Pride and Prejudice", "Jane Austen", "0003");

        

        Librarian andrew = library.getLibrarian(0);
        Customer june = library.getCustomer(0);

        andrew.checkOutBook("0001", 0);
        andrew.checkOutBook("0002", 0);
        
        andrew.returnBook("0001", 0);

        june.printBooks();
        

    }


}
