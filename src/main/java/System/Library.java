package System;


import Accounts.*;
import Book.Book;
import Orders.*;

import java.util.ArrayList;

public abstract class Library {
    public static ArrayList<Book> books = new ArrayList<Book>();
    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static ArrayList<Borrower> borrowers = new ArrayList<Borrower>();

    public static ArrayList<Admin> admins = new ArrayList<Admin>();



    public static boolean loginCustomer(String email, String password){
        for (Customer customer : customers){
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)){
                customer.setLoggedIn(true);
                return true;

            }
        }
        return false;

    }
    public static Customer logged_in_customer(){
        //boolean isCustomerLoggedin = false;
        for (Customer customer : customers){
            if(customer.getIsLoggedIn()){
                //isCustomerLoggedin = true;
                return customer;
            }
        }
        return null;
    }

    public static void logOutCustomer(){

        Customer customer = logged_in_customer();
        customers.get(customers.indexOf(customer)).setLoggedIn(false);

    }


    private static boolean checkPasswordLength(String password){
        if (password.length() > 8){
            return true;
        }
        return false;

    }
    private static boolean checkEmailExistBefore (String email){
        boolean email_found = false;
        for (Customer customer : customers){
            if (customer.getEmail().equals(email)){

                email_found = true;
            }
        }

        for (Borrower borrower : borrowers){
            if (borrower.getEmail().equals(email)){
                email_found = true;
            }
        }

        for (Admin admin : admins){
            if (admin.getEmail().equals(email)){
                email_found = true;
            }
        }

        if (email_found){
            return false;
        }
        return true;

    }

    private static boolean checkEmailFormat(String email){
        boolean atmark_found= false;
        boolean dot_found = false;

        for (int i = 0 ; i < email.length();i++){
            if (email.charAt(i) == '@'){
                atmark_found = true;
            }
            if (email.charAt(i) == '.'){
                dot_found = true;
            }
        }

        if (atmark_found && dot_found){
            return true;
        }
        return false;
    }


    public static boolean checkEmailAndPassword (String email , String password){ // I make it public cuz I need to use it in different class but the correct to be private
        if (checkEmailExistBefore(email) && checkEmailFormat(email) && checkPasswordLength(password)){
            return true;
        }
        return false;
    }

    public static ArrayList<String> getAllBooksNames(){
        ArrayList<String> booksNames = new ArrayList<String>();
        for (Book book: Library.books){
            booksNames.add(book.getBook_Title());
        }
        return booksNames;
    }

    public static ArrayList<String> getAllAuthorsNames(){
        ArrayList<String> authorsNames = new ArrayList<String>();
        for (Book book: Library.books){
            authorsNames.add(book.getAuthor());
        }
        return authorsNames;
    }


    public static boolean registerCustomer(String name , String email , String password){
        if (checkEmailAndPassword(email,password)){
            Customer customer = new Customer(name,email,password);
            customers.add(customer);
            return true;
        }
        return false;
    }
    // ---------------------------------------------------------------------------
    // for borrower
    public static boolean loginBorrower(String email, String password){
        for (Borrower borrower : borrowers){
            if (borrower.getEmail().equals(email) && borrower.getPassword().equals(password)){
                borrower.setLoggedIn(true);
                return true;
            }
        }
        return false;
    }

    public static Borrower logged_in_borrower(){
        for (Borrower borrower : borrowers){
            if(borrower.getIsLoggedIn()){
                return borrower;
            }
        }
        return null;
    }

    public static void logOutBorrower(){
        Borrower borrower = logged_in_borrower();
        borrowers.get(borrowers.indexOf(borrower)).setLoggedIn(false);
    }


    public static boolean registerBorrower(String name , String email , String password){
        if (checkEmailAndPassword(email,password)){
            Borrower borrower = new Borrower(name,email,password);
            borrowers.add(borrower);
            return true;
        }
        return false;
    }
    //-------------------------------------------------------------------
    // Admin
    public static boolean loginAdmin(String email, String password){
        for (Admin admin : admins){
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)){
                admin.setLoggedIn(true);
                return true;
            }
        }
        return false;
    }

    public static Admin logged_in_admin(){
        for (Admin admin : admins){
            if(admin.getIsLoggedIn()){
                return admin;
            }
        }
        return null;
    }

    public static void logOutAdmin(){
        Admin admin = logged_in_admin();
        admins.get(admins.indexOf(admin)).setLoggedIn(false);
    }

    private static boolean checkForSecretKey(String secretKey){
        if (secretKey.equals(Admin.getSecretCode())){
            return true;
        }
        return false;
    }
    public static boolean registerAdmin(String name , String email , String password,String secretKey){
        if (secretKey.equals(Admin.getSecretCode())){
            if (checkEmailAndPassword(email,password)){
                Admin admin = new Admin(name,email,password);
                admins.add(admin);
                return true;
            }
        }
        return false;
    }




    public static int BookIndex(Book book){
        return books.indexOf(book);
    }

    public static Book findBookByID(String BookID){
        for(Book book : Library.books){
            if (book.getBook_ID().equals(BookID)) {
                return book;
            }
        }
        return null;
    }

    public static void updateBook(Book book,int index){
        books.set(index,book);
    }

    public static boolean checkBookId(String BookId){
        for (Book book : Library.books){
            if (book.getBook_ID().equals(BookId)){
                return true;
            }


        }
        return false;
    }

    public static Borrower findBorrowerById(String targetId) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId().equals(targetId)) {
                return borrower;
            }
        }
        return null;
    }








}
