package com.example.librarysystem.customerpage.showorders;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import System.Library;
import Accounts.Customer;
import Book.Book;

public class CustomerBookCardOrder {

    @FXML
    private Label bookCategory_label;

    @FXML
    private Label bookName_label;

    @FXML
    private Label bookQuanityOrder_label;

    @FXML
    private Label bookauthor_label;

    @FXML
    private ImageView bookimg;

    @FXML
    private Label bookprice_label;


    private Book book;


    public void setBook(Book book){
        this.book = book;
    }

    public void setData(int customerBookQuantity){
        Image bookImage = new Image(book.getBook_image());
        bookimg.setImage(bookImage);
        bookName_label.setText(book.getBook_Title());
        bookCategory_label.setText(book.getCategory());
        bookauthor_label.setText(book.getAuthor());
        bookprice_label.setText("$" + book.getPriceBuying());
        bookQuanityOrder_label.setText(String.valueOf(customerBookQuantity)); // casting from integer to string

    }

}
