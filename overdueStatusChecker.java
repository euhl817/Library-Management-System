import java.util.HashMap;

public class overdueStatusChecker implements Runnable{
    
    private static LibrarySystem library;

    public overdueStatusChecker(LibrarySystem library) {
        this.library = library;
    }

    public void run() {
        HashMap<Integer, Customer> customers = library.getCustomers();

       for (Customer customer : customers.values()) {
            customer.checkOverdueStatus();
        }
        
    }


    
}
