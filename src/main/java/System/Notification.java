package System;

import System.Library;
import Orders.*;
import Accounts.*;
import Book.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Notification {

    static Borrower borrowerLoggedIn = Library.logged_in_borrower();
    public static String DueDate() {
        String msg="";

        for(Transaction transaction:borrowerLoggedIn.transactionsHistory){
            ArrayList<Boolean> isBookReturned = transaction.getIsBookReturned();
            for (int i = 0; i < isBookReturned.size(); i++) {
                LocalDate date=transaction.getReturnDate().get(i);
                if (LocalDate.now().isAfter(date)&&!isBookReturned.get(i)){
                    msg += transaction.getAllBooks()+" , ";
                }
            }
        }
        return msg;
    }

    public static String PickUp() {
        String msg="";
        ArrayList<String> reservedBooks = borrowerLoggedIn.getReservedBooksIDs();
        for (Book book : Library.books) {
            for (String res_book : reservedBooks) {
                if(book.getBook_ID().equals(res_book))
                    if(book.getStock()> 0)
                        msg += book.getBook_Title()+" , ";
            }
        }
        return msg;
    }
}