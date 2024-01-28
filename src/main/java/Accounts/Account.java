package Accounts;
import System.*;
import Book.*;

import java.util.ArrayList;
import java.util.Random;
public abstract class Account implements IdGenerator {
    protected String id;
    protected String name;

    protected String email;

    protected String password;

    protected boolean isLoggedIn;

    public Account(String name , String email , String password ){

        this.name = name;
        this.email = email;
        this.password = password;
        this.isLoggedIn = false;

    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) { // to be able to change its value from another class instead of accessing the variable directly for encapsulation and abstraction
        isLoggedIn = loggedIn;
    }



    public String toString(){
        return this.id + "," + this.name + "," + this.email + "," + this.password ;
    }

    public ArrayList<Book> searchBooks(String search,String recommend) {
        ArrayList<Book> matchingBooks = new ArrayList<>();

        for (Book book : Library.books) {
            if (book.getBook_Title().toLowerCase().contains(search.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(search.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        if (matchingBooks.isEmpty()) {
            for (Book book : Library.books) {
                if (book.getCategory().equalsIgnoreCase(recommend)) {
                    matchingBooks.add(book);
                }
            }
        }
        return matchingBooks;
    }

    public ArrayList<Book> recommendBooks(String recommend){
        ArrayList<Book> matchingBooks = new ArrayList<>();
        for (Book book : Library.books) {
            if (book.getCategory().equalsIgnoreCase(recommend)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;

    }
}