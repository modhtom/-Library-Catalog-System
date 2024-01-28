package Orders;

import java.util.ArrayList;
import java.util.List;
import System.*;
import Orders.*;
import Accounts.*;
import Book.*;
public abstract class ShoppingCart {
    List<CartItem> cartItems;
    protected final int deliveryServicePrice = 30;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }


    public void removeFromCart(Book book) {
        CartItem item = findItemInCart(book);
        if (item != null) {
            CartItem temp_item =item;
            item.getBook().setStock(item.getQuantity() + item.getBook().getStock());
            Library.updateBook(item.getBook(),Library.BookIndex(book));
            cartItems.remove(temp_item);
        }
    }

    public boolean updateQuantity(Book book, int quantity) {
        CartItem item = findItemInCart(book);
        if (item != null) {
            if (item.getBook().getStock() >= quantity && quantity <=5){
                /*item.getBook().setStock(item.getBook().getStock() - quantity);
                if (item.getBook().getStock() <=0){
                    item.getBook().setStatus(false);

                }*/
                item.setQuantity(quantity);// lw eshtret elktab fy cart w 3ayz tzwd kmya bs
                Library.updateBook(item.getBook(),Library.BookIndex(book));
                return true;
            }
            else{
                return false;
            }



        }
        return false;
    }

    public int getDeliveryServiceFees(){
        return deliveryServicePrice;
    }

    public List<CartItem> getCartDetails() {
        return cartItems;
    }

    public ArrayList<Book> getAllBooksInCart(){
        ArrayList<Book> allBooks = new ArrayList<Book>();
        for (CartItem cartItem : cartItems){
            allBooks.add(cartItem.getBook());
        }
        return allBooks;
    }

    public ArrayList<Integer> getAllQuantitiesOfBooksInCart(){
        ArrayList<Integer> allQuantities = new ArrayList<Integer>();
        for (CartItem cartItem : cartItems){
            allQuantities.add(cartItem.getQuantity());
        }
        return allQuantities;
    }

    public abstract double calculateTotalPrice();
    public abstract double calculatepriceBeforeDiscount();


    public double applyDiscount() {
        int totalBooksInCart = cartItems.stream().mapToInt(CartItem::getQuantity).sum();

        if (totalBooksInCart >= 5) {
            return 0.5; // 50% discount
        } else if (totalBooksInCart == 4) {
            return 0.4; // 40% discount
        } else if (totalBooksInCart == 3) {
            return 0.3; // 30% discount
        } else {
            return 0.0; // No discount
        }
    }

    public int getNoOfBooksInCart(){
        int no_books = 0;
        for(Book book : getAllBooksInCart()){
            no_books++;
        }
        return no_books;
    }

    CartItem findItemInCart(Book book) {
        for (CartItem item : cartItems) {
            if (item.getBook().equals(book)) {
                return item;
            }
        }
        return null;
    }

    public abstract void completePurchase();
    public void processPayment(String paymentMethod) {
        if (paymentMethod.equalsIgnoreCase("cashondelivery")) {
            System.out.println("Payment method: Cash on Delivery");
            perform_Cash_Payment();
            completePurchase();
        } else if (paymentMethod.equalsIgnoreCase("creditcard")) {
            System.out.println("Payment method: Credit Card");
            perform_Credit_Payment();
            completePurchase();
        } else {
            System.out.println("Invalid payment method");
        }
    }

    private void perform_Cash_Payment() {
        System.out.println("Processing Cash on Delivery payment...");
        double totalPrice = calculateTotalPrice();
        System.out.println("Please have the exact amount ready upon delivery: $" + totalPrice);
        System.out.println("Thank you for your purchase!");
        clearCart();
    }

    private void perform_Credit_Payment() {
        System.out.println("Processing Credit Card payment...");
        double totalPrice = calculateTotalPrice();
        boolean paymentSuccessful = processCreditCardTransaction(totalPrice);

        if (paymentSuccessful) {
            System.out.println("Payment successful. Thank you for your purchase!");
            clearCart();
        } else {
            System.out.println("Credit Card payment failed. Please try again.");
        }
    }

    protected boolean processCreditCardTransaction(double amount) {
        return true;
    }

    protected void clearCart() {
        cartItems.clear();
    }
}