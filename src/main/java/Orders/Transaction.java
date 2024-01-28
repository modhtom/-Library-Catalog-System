package Orders;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import System.*;
import Orders.*;
import Accounts.*;
import Book.*;

public class Transaction implements IdGenerator {

    private String transactionId;
    private ArrayList<String> booksID;
    private ArrayList<Integer> booksQuantity;

    private ArrayList<Boolean> isBookReturned;
    private ArrayList<LocalDate> returnDate;
    private double totalPrice;
    private LocalDate borrowDate;

    public Transaction(ArrayList<Book> book, ArrayList<Integer> quantity, LocalDate borrowDate , ArrayList<LocalDate> returnDate) {

        do {
            this.transactionId = getRandomId(1000, 10000);
        }while(isIdExists(this.transactionId));

        totalPrice = 0;
        this.booksID = new ArrayList<String>();
        this.booksQuantity = new ArrayList<Integer>();
        this.returnDate = new ArrayList<LocalDate>();
        this.isBookReturned = new ArrayList<Boolean>();


        this.booksQuantity = quantity;
        this.returnDate = returnDate;

        for(int i=0;i<book.size();i++)
        {
            booksID.add(book.get(i).getBook_ID());
            isBookReturned.add(false);
            totalPrice += book.get(i).getPriceBorrowing()*quantity.get(i);
        }


        this.borrowDate = borrowDate;

    }

    public Transaction(String transactionId , String bookID , int quantity , LocalDate returnDate, double totalprice, LocalDate transactionDate,boolean isReturned){
        this.transactionId = transactionId;
        this.booksID = new ArrayList<String>();
        this.booksQuantity = new ArrayList<Integer>();
        this.returnDate = new ArrayList<LocalDate>();
        this.isBookReturned = new ArrayList<Boolean>();

        this.borrowDate = transactionDate;

        booksID.add(bookID);
        booksQuantity.add(quantity);
        this.returnDate.add(returnDate);
        this.isBookReturned.add(isReturned);

        this.totalPrice = totalprice;


    }

    public ArrayList<String> getBooksID(){return booksID;}

    public void addBook(String bookID, int quantity, LocalDate returndate,boolean isReturned){
        booksID.add(bookID);
        booksQuantity.add(quantity);
        returnDate.add(returndate);
        isBookReturned.add(isReturned);
    }




    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }



    public ArrayList<Integer> getQuantity() {
        return booksQuantity;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        this.booksQuantity = quantity;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double price) {
        totalPrice = price;
    }

    public LocalDate getBorrowDate() {
        return this.borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public ArrayList<LocalDate> getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(ArrayList<LocalDate> returnDate) {
        this.returnDate = returnDate;
    }

    public ArrayList<Boolean> getIsBookReturned() {
        return isBookReturned;
    }

    public void setIsBookReturned(ArrayList<Boolean> isBookReturned) {
        this.isBookReturned = isBookReturned;
    }


    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> allBooks = new ArrayList<Book>();
        for (String bookid : booksID){
            for (Book book : Library.books){
                if (book.getBook_ID().equals(bookid)){
                    allBooks.add(book);
                }
            }
        }
        return allBooks;
    }

    public boolean isIdExists(String id){
        for (Borrower borrower : Library.borrowers){
            for (Transaction transaction : borrower.getTransactionsHistory()){
                if (transaction.getTransactionId().equals(id)){
                    return true;
                }
            }

        }
        return false;

    }

}

