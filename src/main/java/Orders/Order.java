package Orders;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import System.*;
import Orders.*;
import Accounts.*;
import Book.*;

public class Order implements IdGenerator {
    private String OrderId;
    private ArrayList<String> booksID;

    private ArrayList<Integer> booksQuantity;
    private double totalPrice;
    private LocalDate OrderDate;


    public Order(ArrayList<Book> book, ArrayList<Integer> quantity, LocalDate orderDate) {
        do {
            this.OrderId = getRandomId(100, 1000); // ID FOR Borrowers
        }while(isIdExists(this.OrderId));


        this.booksID = new ArrayList<String>();

        this.booksQuantity = quantity;


        for(int i=0;i<book.size();i++)
        {

            booksID.add(book.get(i).getBook_ID());
            totalPrice += book.get(i).getPriceBuying()*quantity.get(i);
        }
        OrderDate = orderDate;

    }


    public Order(String orderId , String bookID , int quantity , double totalprice, LocalDate orderDate){
        this.OrderId = orderId;
        this.booksID = new ArrayList<String>();
        this.booksQuantity = new ArrayList<Integer>();
        this.OrderDate = orderDate;

        booksID.add(bookID);
        booksQuantity.add(quantity);
        this.totalPrice = totalprice;


    }

    public ArrayList<String> getBooksID(){return booksID;}


    public void addBook(String bookID, int quantity){
        booksID.add(bookID);
        booksQuantity.add(quantity);
    }



    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
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

    public LocalDate getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        OrderDate = orderDate;
    }

    public Book getBook (String bookID){ //mn hna t2dr tgeb title w kol byanat elktab
        for (Book book : Library.books){
            if (book.getBook_ID().equals(bookID)){
                return book;
            }
        }
        return null;
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

    // overriding isId exist
    public boolean isIdExists(String id){
        for (Customer customer : Library.customers){
            for (Order order : customer.getOrderHistory()){
                if (order.getOrderId().equals(id)){
                    return true;
                }
            }

        }
        return false;

    }

}
