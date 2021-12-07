import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LibrarySystem {
    private HashMap<Integer, Customer> customers;
    private HashMap<Integer, Librarian> librarians;
    private HashMap<String, Book> books;
    private Administrator administrator;
    private int lastCustomerID;
    private int lastLibrarianID;
    private int maxCheckedOut;
    private int maxDaysBorrowed;
    private overdueStatusChecker overdueChecker;
    
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
        executorService.scheduleAtFixedRate(overdueChecker, 0, 1, TimeUnit.DAYS);
    }

    public void addAdministrator(Administrator administrator) {
        this.administrator = administrator;
        administrator.setLibrarySystem(this);
    }

    public void addBook(String ISBN, Book book) {
        books.put(ISBN, book);
        book.setLibrarySystem(this);
    }

    public void removeBook(String ISBN) {
        books.remove(ISBN);
    }

    public void addCustomer(Customer customer, int ID) {
        customers.put(ID, customer);
        customer.setLibrarySystem(this);
    }

    public void removeCustomer(int ID) {
        customers.get(ID).setLibrarySystem(null);
        customers.remove(ID);
    }

    public void addLibrarian(Librarian librarian, int ID) {
        librarians.put(ID, librarian);
        librarian.setLibrarySystem(this);
    }

    public void removeLibrarian(int ID) {
        librarians.get(ID).setLibrarySystem(null);
        librarians.remove(ID);
    }

    public int getNewCustomerID() {
        return ++lastCustomerID;
    }

    public int getNewLibrarianID() {
        return ++lastLibrarianID;
    }

    public int getMaxCheckedOut() {
        return maxCheckedOut;
    }

    public int getMaxDaysBorrowed() {
        return maxDaysBorrowed;
    }

    public Customer getCustomer(int ID) {
        return customers.get(ID);
    }

    public Librarian getLibrarian(int ID) {
        return librarians.get(ID);
    }

    public Book getBook(String ISBN) {
        return books.get(ISBN);
    }

    public HashMap<Integer, Customer> getCustomers() {
        return customers;
    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem(10, 14);

    }


}
