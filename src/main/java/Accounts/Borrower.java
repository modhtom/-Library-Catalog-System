package Accounts;
import System.*;
import Orders.*;
import Book.*;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.ArrayList;

public class Borrower extends Account{

    public BorrowerCart shoppingCart;
    public ArrayList<Transaction> transactionsHistory ;

    ArrayList<String> reservedBooks; //books IDs

    public Borrower(String name, String email , String password) {
        super(name, email, password);
        do {
            this.id = getRandomId(1, 100); // ID FOR Borrowers
        }while(isIdExists(this.id));

        shoppingCart = new BorrowerCart();
        transactionsHistory = new ArrayList<Transaction>();
        reservedBooks= new ArrayList<String>();
        this.reservedBooks = new ArrayList<String>();

    }

    public Borrower (String borrower_id , String name, String email , String password ){
        super(name,email,password);
        this.id = borrower_id;
        //shoppingCart=null; //---
        shoppingCart = new BorrowerCart();
        transactionsHistory = new ArrayList<Transaction>();
        this.reservedBooks = new ArrayList<String>();
        //reservedBooks = reservedBooksID;

    }

    public BorrowerCart getShoppingCart(){
        return this.shoppingCart;
    }


    public void addBorrowingHistory(Transaction transaction) {
        transactionsHistory.add(transaction);
    }

    public ArrayList<Transaction> getTransactionsHistory() {
        return transactionsHistory;
    }


    public boolean isIdExists(String id){
        for (Borrower borrower : Library.borrowers){
            if (borrower.getId().equals(id)){
                return true;
            }

        }
        return false;

    }

    public void displayAllTransactions(){

    }

    public void displayShoppingCart(){

    }

    public String getUserFavoriteCategory() {
        ArrayList<String> purchasedCategories = new ArrayList<>();
        String userType="";
        for (Transaction order : transactionsHistory) {
            ArrayList<Book> booksInOrder = order.getAllBooks();
            for (Book book : booksInOrder) {
                String category = book.getCategory();
                purchasedCategories.add(category);
            }
        }
        userType = purchasedCategories.get(0);
        int maxCount = 1;
        for (int i = 0; i < purchasedCategories.size(); i++) {
            String currentCategory = purchasedCategories.get(i);
            int currentCount = 1;

            for (int j = i + 1; j < purchasedCategories.size(); j++) {
                if (currentCategory.equals(purchasedCategories.get(j))) {
                    currentCount++;
                }
            }

            if (currentCount > maxCount) {
                userType = currentCategory;
                maxCount = currentCount;
            }
        }
        return userType;
    }


    public double returningBooks(Book[]book)
    {

        double fine=0;

        for (int i = 0; i < book.length; i++)
        {
            for(Transaction transaction :this.transactionsHistory)
            {
                for(String id:transaction.getBooksID())
                {
                    LocalDate returnDate = transaction.getReturnDate().get(i);
                    if (LocalDate.now().isAfter(returnDate)&&id.equals(book[i].getBook_ID()))
                        fine+=10;
                }
            }
        }

        return fine;

    }


    public boolean addReserveBook(String bookID){
        for(String book_id : reservedBooks){
            if(book_id.equals(bookID)){
                return false;

            }
        }
        reservedBooks.add(bookID);
        return true;
    }

    // Remove a book from reserved List


    public ArrayList<String> getReservedBooksIDs(){
        return reservedBooks;
    }

    public ArrayList<Book> getReservedBooks(){
        ArrayList<Book> reserved_books = new ArrayList<Book>();
        for (String book_ID : reservedBooks){
            for(Book book : Library.books){
                if(book.getBook_ID().equals(book_ID)){
                    reserved_books.add(book);
                }

            }

        }
        return reserved_books;

    }


    public void removeReserveBook(String bookID){
        reservedBooks.remove(bookID);
    }

}
