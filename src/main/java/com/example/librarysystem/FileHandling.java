package com.example.librarysystem;

import System.*;
import Orders.*;
import Accounts.*;
import Book.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FileHandling {


    private static final String DELIMITER = ",";




    public static void writeAllFiles(){
        writeIntoFile("admin.csv",1);
        writeIntoFile("customer.csv",2);
        writeIntoFile("borrower.csv",3);
        writeIntoFile("book.csv",4);
        writeIntoFile("reviews.csv",5);
        writeIntoFile("orders.csv",6);
        writeIntoFile("transactions.csv",7);
        writeIntoFile("reserved_books.csv",8);

    }

    public static void readAllFiles(){
        readFile("admin.csv",1);
        readFile("customer.csv",2);
        readFile("borrower.csv",3);
        readFile("book.csv",4);
        readFile("reviews.csv",5);
        readFile("orders.csv",6);
        readFile("transactions.csv",7);
        readFile("reserved_books.csv",8);
    }


    /*
     * Admin accs
     * Customers accs
     * Borrower accs
     * Books
     *
     * Orders for each customer
     * Transaction for each borrower
     * Reviews for each book
     *
     * */
    private static void writeIntoFile(String path, int no) {
        //String book_id;
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            switch (no){

                case 1:
                    for (Admin admin : Library.admins){
                        writer.println(admin);
                    }
                    break;

                case 2:
                    for (Customer customer : Library.customers){
                        writer.println(customer);
                    }
                    break;

                case 3:
                    for (Borrower borrower : Library.borrowers){
                        writer.println(borrower);
                    }
                    break;



                case 4:
                    for (Book book : Library.books) {
                        writer.println(book); // Using toString() to get CSV representation
                    }
                    break;


                case 5:
                    for(Book book : Library.books){ // lkol ktab hyroh yshof array of reviews bta3th
                        String book_id = book.getBook_ID();
                        for (Review rev : book.reviews) {
                            writer.println(book_id + "," + rev);
                        }
                        // #book_ID , #Customer_ID , comment , rating
                    }
                    break;
                case 6:
                    for (Customer cust : Library.customers){
                        String customer_id = cust.getId();
                        for (Order order : cust.ordersHistory){
                            // you dont need to store the data in variables for each order except books cuz you can access in book
                            // kont mmkn a3ml for each loop object mn no3 Book by3ml looping 3la array of objects of books ely mwgod fy order
                            // kont mmkn a3ml looping gwa object book lw hnsebha enna fy order tkon array of object of books w astkhdm getter ely btrg3 id w dy ely akhznha fy println
                            /*for (Book bookk : order.getBook()){
                                writer.println(customer_id);
                            }*/
                            ArrayList <String> bookIds = order.getBooksID();
                            ArrayList<Integer> bookQuantity = order.getQuantity();
                            for (int i = 0 ; i < bookIds.size() ; i++){
                                writer.println(customer_id + "," + order.getOrderId() + "," + bookIds.get(i) + "," + bookQuantity.get(i) + "," + order.getTotalPrice() + "," + order.getOrderDate());
                            }
                            // #customer_id , #order_id , #book1 , quantity of each book , totalprice of order , order date

                            /*for (String bookID : order.getBookID()){
                                writer.println(customer_id + "," + order.getOrderId() + "," + bookID + "," + order.getQuantity() + "," + order.getTotalPrice() + "," + order.getOrderDate());
                            }*/


                        }
                    }
                    break;
                case 7:
                    for (Borrower borrower : Library.borrowers){
                        String borrower_id = borrower.getId();
                        for (Transaction transaction : borrower.transactionsHistory){
                            /*
                            for (Book book : transaction.books){ // lw khlena array ykon public
                                book.getID();
                            }
                            */


                            /*for (String bookID : transaction.getBookID()){
                                writer.println(borrower_id + "," + transaction.getTransactionId() + "," + bookID + "," + transaction.getBorrowDate() + "," + transaction.getReturnDate() + "," + order.getQuantity() + "," + transaction.getPrice() + "," + transaction.getPrice());
                            }*/

                            ArrayList <String> bookIdsBorrowing = transaction.getBooksID();
                            ArrayList<Integer> bookQuanityBorrowing = transaction.getQuantity();
                            ArrayList<LocalDate> bookReturnDateBorrowing = transaction.getReturnDate();
                            ArrayList<Boolean> isBookReturded = transaction.getIsBookReturned();
                            for (int i = 0 ; i < bookIdsBorrowing.size() ; i++){
                                writer.println(borrower_id + "," + transaction.getTransactionId() + "," + bookIdsBorrowing.get(i) + "," + bookQuanityBorrowing.get(i) + "," + bookReturnDateBorrowing.get(i) + "," + transaction.getTotalPrice() + "," + transaction.getBorrowDate() + "," + isBookReturded.get(i) );
                            }



                        }
                    }
                    break;

                case 8:
                    for (Borrower borrower : Library.borrowers){
                        String borrower_id = borrower.getId();
                        for (String reservedBookIds : borrower.getReservedBooksIDs()){
                            writer.println(borrower_id + "," + reservedBookIds);
                        }

                    }
                    break;





            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }







    public static void readFile (String path , int no){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(String.valueOf(",")); //delimeter /// oposite of join


                switch (no){

                    case 1:
                        Admin admin = new Admin(values[0],values[1],values[2],values[3]);

                        Library.admins.add(admin);
                        break;

                    case 2:
                        Customer customer = new Customer(values[0],values[1],values[2],values[3]);

                        Library.customers.add(customer);
                        break;

                    case 3:
                        Borrower borrower = new Borrower(values[0],values[1],values[2],values[3]);
                        Library.borrowers.add(borrower);
                        break;

                    case 4:
                        Book book = new Book(values[0],values[1],values[2],Double.parseDouble(values[3]), Integer.parseInt(values[4]),values[5],values[6],values[7]);

                        //book.setRating(Double.parseDouble(values[9])); // kda kda price borrowing w rating hyt7sb automatic 3shan mybwzsh calculation lma hd ygy y3ml rating 2odam
                        Library.books.add(book);
                        break;


                    // lazm elawl nkon 3mlna load l kol ely fok
                    case 5:
                        // Reviews csv file
                        // #book_ID , #Customer_ID , comment , rating
                        for (Book bookk : Library.books){
                            if (bookk.getBook_ID().equals(values[0])){
                                Review review = new Review(values[1],values[2],Float.parseFloat(values[3]));
                                bookk.reviews.add(review);


                                //int index;
                                /*
                                for (Customer customer1 : Library.customers){
                                    if (customer1.getId().equals(values[1])){
                                        int index = Library.customers.indexOf(customer1);
                                        Review review = new Review(Library.customers.get(index),values[2],values[3]);
                                        bookk.reviews.add(review);
                                    }
                                }*/


                                //Review review = new Review(Library.customers.get(index),values[2],values[3]);
                                //bookk.reviews.add(review);

                                /*
                                for (Review review : bookk.reviews){
                                    review.setRating = values[3];
                                    review.setComment = values[2];
                                    review.setCustomerId = values[1];
                                    bs msh 3arf for loop mzonsh tnfy
                                    3shan hya bt3ml looping gwa elements aw objects ely fy array
                                    w aslun elarray fady mfehosh elements
                                    fa for each msh bt3ml initialization aw bt3ml creating l objects inside array
                                    even for loop can't do that


                                }
                                 */
                            }
                        }
                        break;

                    case 6:
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                        for (Customer cust : Library.customers){
                            if (cust.getId().equals(values[0])){
                                // #customer_id , #order_id , #bookID , quantity of each book , totalprice of order , order date
                                // khaly balk order id order date w total price already calculated dol sabten
                                boolean order_found_before = false;
                                for (Order orderr : cust.ordersHistory) {
                                    if (orderr.getOrderId().equals(values[1])) { // if there is already there is order by same order id
                                        order_found_before = true;
                                        orderr.addBook(values[2],Integer.parseInt(values[3]));

                                    }
                                }
                                if (!order_found_before){
                                    Order order = new Order(values[1],values[2],Integer.parseInt(values[3]),Double.parseDouble(values[4]), LocalDate.parse(values[5])); //LocalDate.parse(values[5],formatter)
                                    cust.addOrderHistory(order);
                                }
                                order_found_before = false;

                            }
                        }
                        break;

                    case 7:
                        DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        for (Borrower borrowerr : Library.borrowers){
                            if (borrowerr.getId().equals(values[0])){
                                boolean transaction_found_before = false;
                                for (Transaction transactionn : borrowerr.transactionsHistory) {
                                    if (transactionn.getTransactionId().equals(values[1])) { // if there is already there is order by same order id
                                        transaction_found_before = true;
                                        transactionn.addBook(values[2],Integer.parseInt(values[3]),LocalDate.parse(values[5],formatterr),Boolean.parseBoolean(values[7])); // Boolean.parseBoolean() :- casting from Boolean to String , in write file we store boolean of is returned or not in the 8th element

                                    }
                                }
                                if (!transaction_found_before){
                                    //Transaction transaction = new Transaction(values[1],values[2],Integer.parseInt(values[3]),LocalDate.parse(values[4],formatterr), Double.parseDouble(values[5]),LocalDate.parse(values[6],formatterr));
                                    Transaction transaction = new Transaction(values[1],values[2],Integer.parseInt(values[3]),LocalDate.parse(values[4]), Double.parseDouble(values[5]),LocalDate.parse(values[6]),Boolean.parseBoolean(values[7]));
                                    borrowerr.addBorrowingHistory(transaction);
                                }
                                transaction_found_before = false;


                            }
                        }
                        break;

                    case 8:
                        for (Borrower borrowerrr : Library.borrowers){
                            if (borrowerrr.getId().equals(values[0])){
                                borrowerrr.addReserveBook(values[1]);
                            }
                        }
                        break;



                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }














}
