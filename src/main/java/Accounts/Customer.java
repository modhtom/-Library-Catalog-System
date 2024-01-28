package Accounts;


import System.*;
import Orders.*;
import Accounts.*;
import Book.*;
import System.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends Account {
    public CustomerCart shoppingCart;
    public ArrayList<Order> ordersHistory ;

    public Customer(String name, String email , String password){
        super(name,email,password);
        do {
            this.id = getRandomId(1, 1000); // ID FOR Borrowers
        }while(isIdExists(this.id));
        shoppingCart = new CustomerCart();
        ordersHistory = new ArrayList<Order>();
    }
    public Customer (String customer_id , String name, String email , String password){
        super(name,email,password);
        this.id = customer_id;
        shoppingCart = new CustomerCart();
        ordersHistory = new ArrayList<Order>();

    }
    public CustomerCart getShoppingCart(){
        return this.shoppingCart;
    }


    public void addOrderHistory(Order order) {
        this.ordersHistory.add(order);
    }

    public ArrayList<Order> getOrderHistory() {
        return this.ordersHistory;
    }



    public boolean isIdExists(String id){
        for (Customer customer : Library.customers){
            if (customer.getId().equals(id)){
                return true;
            }
        }
        return false;

    }

    public void displayAllOrders(){

    }

    public void displayShoppingCart(){

    }
    public static Customer getCustomer(String customer_id){
        for (Customer customer : Library.customers){
            if (customer.getId().equals(customer_id)){
                return customer;
            }
        }
        return null;
    }
    public String getUserFavoriteCategory() {
        ArrayList<String> purchasedCategories = new ArrayList<>();
        String userType="";
        for (Order order : ordersHistory) {
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


    public ArrayList<String> allBooksIdsCustomerBought(){
        ArrayList<String> bookIds = new ArrayList<String>();
        for (Order order : ordersHistory){
            bookIds.addAll(order.getBooksID());
            /*for (String bookId : order.getBooksID()){
                bookIds.add(bookId);
            }*/

        }
        return bookIds;
    }



    public boolean bookInOrder(String bookID){
        for (Order order : ordersHistory){
            for (String book_id: order.getBooksID()){
                if(book_id.equals(bookID)){
                    return true;
                }
            }
        }
        return false;
    }


}
