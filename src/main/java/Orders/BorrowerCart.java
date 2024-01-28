package Orders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import System.*;
import Orders.*;
import Accounts.*;
import Book.*;
import com.example.librarysystem.Starter;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class BorrowerCart extends ShoppingCart{
    ArrayList <LocalDate> returnDates = new ArrayList<LocalDate>();


    public void addToCart(Book book){
        CartItem item = findItemInCart(book);
        if (item != null) { // if book found in shopping cart just add quantity
            Starter.showAlert(Alert.AlertType.ERROR, "Error", "You It's already in the cart", null);
            return;
        }else{
            Book tempBook = book;
            int quantity = 1;
            CartItem cartitem = new CartItem (book,quantity);
            cartitem.getBook().setPriceBorrowing(LocalDate.now(),LocalDate.now().plusDays(14));
            Library.updateBook(cartitem.getBook(),Library.BookIndex(tempBook));
            cartItems.add(cartitem);// if book not found in shopping cart
            returnDates.add(LocalDate.now().plusDays(14));
        }


    }


    public boolean updateDueDate(Book book, LocalDate newDueDate) {
        // max due date = 40 days
        CartItem item = findItemInCart(book);
        if (item != null) {
            if (ChronoUnit.DAYS.between(LocalDate.now(),newDueDate) > 40){
                return false;
            }
            int index = cartItems.indexOf(item);
            returnDates.set(index,newDueDate);

            item.getBook().setPriceBorrowing(LocalDate.now(),newDueDate);
            // int index of old book details = Library.BookIndex(book)
            // new version of book .getBook() will be updated by details
            Library.updateBook(item.getBook(),Library.BookIndex(book));

            return true;


        }
        return false;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        double discount = applyDiscount();

        for (CartItem item : cartItems) {
            double itemPrice = item.getBook().getPriceBorrowing() * item.getQuantity();
            double discountedItemPrice = itemPrice - (itemPrice * discount);
            totalPrice += discountedItemPrice;
        }
        return totalPrice + deliveryServicePrice;
    }

    public double calculatepriceBeforeDiscount() {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getBook().getPriceBorrowing() * item.getQuantity();
        }
        return totalPrice;
    }


    public void completePurchase(){

        Borrower borrowerLoggedIn = Library.logged_in_borrower();
        ArrayList<Integer> quantities = getAllQuantitiesOfBooksInCart();
        ArrayList<Book> CartBooks = getAllBooksInCart();
        Transaction transaction = new Transaction(getAllBooksInCart(),getAllQuantitiesOfBooksInCart(), LocalDate.now() ,returnDates);
        if (borrowerLoggedIn != null){
            borrowerLoggedIn.transactionsHistory.add(transaction);
            ArrayList<String> reservedBooks = borrowerLoggedIn.getReservedBooksIDs();
            for (String res_book : reservedBooks) {
                for(Book book :CartBooks){
                    if(book.getBook_ID().equals(res_book)){
                        borrowerLoggedIn.removeReserveBook(res_book);}
                }
            }
            for(int i=0 ;i<CartBooks.size() ;i++){
                CartBooks.get(i).setStock(CartBooks.get(i).getStock()-quantities.get(i));
                if(CartBooks.get(i).getStock()<=0)
                    CartBooks.get(i).setStatus(false);
        }
            clearCart();
        }

    }






}
