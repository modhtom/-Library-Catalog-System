package com.example.librarysystem.customerpage.addreview;

public class BookModel {
    String bookID;
    String bookName;
    String bookCategory;
    String authorName;

    public BookModel(String id , String name ,String category , String author){
        this.bookID = id;
        this.bookName = name;
        this.bookCategory = category;
        this.authorName = author;

    }


    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
