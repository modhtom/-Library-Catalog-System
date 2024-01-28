package Accounts;
import Book.*;
import Orders.Order;
import Orders.Transaction;
import System.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Admin extends Account{
    // prive string secret code
    private static final String secretKey = "sdnjsadjnjdsanj";

    public Admin(String name, String email , String password) {
        super(name, email, password);
        do {
            this.id = getRandomId(9000, 10000); // ID FOR Borrowers
        }while(isIdExists(this.id));


    }

    public Admin (String admin_id , String name, String email , String password){
        super(name,email,password);
        this.id = admin_id;


    }



    //-----------------------------------------------------------------------------
    // For books
    public static void addBook(Book book){
        if (book != null){
            Library.books.add(book);
        }
    }

    // over loading method

    public static void addBook(String name , String author , String category , String publicationYear , int stock , double price , String bookImage_path){
        Book book = new Book(name,author,price,stock,category,publicationYear,bookImage_path);
        Library.books.add(book);
    }

    public void removeBook(String BookID) {
        Book book = getBookInformation(BookID);
        if (book != null) {
            Library.books.remove(book);
        }
    }
    public void updateBookStock(String bookID, int newStock){
        Book book = getBookInformation(bookID);
        if (book != null){
            int index = Library.books.indexOf(book);
            Library.books.get(index).setStock(newStock);
        }


    }

    public void updateBookAvailability(String bookID, boolean available){
        Book book = getBookInformation(bookID);
        if (book != null){
            int index = Library.books.indexOf(book);
            Library.books.get(index).setStatus(available);
        }

    }

    public void updateBookPriceBuying(String bookID, double newPrice){
        Book book = getBookInformation(bookID);
        if (book != null){
            int index = Library.books.indexOf(book);
            Library.books.get(index).setPriceBuying(newPrice);
        }

    }

    public static Book getBookInformation(String bookID){ // return the whole object
        for(Book books : Library.books){
            if (books.getBook_ID().equals(bookID)){
                return books;
            }

        }
        return null;

    }
    // ------------------------------------------------------------------
    // For Accounts.Borrower
    public static void addBorrower(String name , String email , String password){
        Borrower borrower = new Borrower(name , email , password);
        Library.borrowers.add(borrower);
    }

    public static Borrower getBorrowerInformation(String borrowerID){
        for(Borrower borrower: Library.borrowers){
            if (borrower.getId().equals(borrowerID)){
                return borrower;
            }
        }
        return null;
    }

    public static int getBorrowerIndex(String borrower_id){
        Borrower borrower = getBorrowerInformation(borrower_id);
        if (borrower != null){
            return Library.borrowers.indexOf(borrower);
        }
        return -1;


    }

    public static void removeBorrower(String borrower_id){
        Borrower borrower = getBorrowerInformation(borrower_id);
        if (borrower != null){ // ehna b3nml not null 3shan mmkn ykon mdkhl id ghalt
            Library.borrowers.remove(borrower);
        }


    }

    public static void updateBorrowerName(String borrower_id, String name){
        int index = getBorrowerIndex(borrower_id);
        if (index != -1){
            Library.borrowers.get(index).setName(name);
        }


    }

    public static void updateBorrowerEmail(String borrower_id, String email){
        int index = getBorrowerIndex(borrower_id);
        if (index != -1){
            Library.borrowers.get(index).setEmail(email);
        }


    }

    public static void updateBorrowerPassword(String borrower_id, String password){
        int index = getBorrowerIndex(borrower_id);
        if (index != -1){
            Library.borrowers.get(index).setPassword(password);
        }


    }
    public static Customer getCustomerInformation(String customerID){
        for(Customer customer: Library.customers){
            if (customer.getId().equals(customerID)){
                return customer;
            }
        }
        return null;
    }

    public boolean isIdExists(String id){
        for (Admin admin : Library.admins){
            if (admin.getId().equals(id)){
                return true;
            }

        }
        return false;

    }

    public static String getSecretCode(){
        return secretKey;
    }

    public double returningBooks(String borrowerID,String transactionID,String bookID) {

        double fine = 0;
        Borrower borrower = Library.findBorrowerById(borrowerID);
        if(borrower!=null) {
            for (Transaction transaction : borrower.transactionsHistory) {
                if (transaction.getTransactionId().equals(transactionID)) {
                    ArrayList<String> booksID = transaction.getBooksID();
                    ArrayList<Boolean> isBookReturned = transaction.getIsBookReturned();

                    for (int i = 0; i < booksID.size(); i++) {
                        if (booksID.get(i).equals(bookID) && !isBookReturned.get(i)) {
                            isBookReturned.set(i, true);
                            LocalDate date=transaction.getReturnDate().get(i);
                            if(LocalDate.now().isAfter(date))
                            fine = 50;
                            transaction.setIsBookReturned(isBookReturned);
                            for (int j = 0; j < borrower.transactionsHistory.size(); j++) {
                                Transaction a_transaction = borrower.transactionsHistory.get(j);
                                if (a_transaction.getTransactionId().equals(transaction.getTransactionId())) {
                                    borrower.transactionsHistory.set(j, transaction);

                                    break;
                                }
                            }
                        }
                    }

                }
            }
        }else{
            fine = -1;
        }

        return fine;
    }
}
