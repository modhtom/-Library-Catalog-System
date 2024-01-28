package com.example.librarysystem.borrowerpage.showreservedlist;

import Book.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class BookCardReserved implements Initializable {

    @FXML
    private Label bookCategory_label;

    @FXML
    private Label bookName;

    @FXML
    private Label bookPublicationYear_label;

    @FXML
    private Label bookauthor;

    @FXML
    private ImageView bookimg;

    @FXML
    private Button reservedBook_btn;

    private Book book;

    public void setBook(Book book){ // passing data between controller we will setBook first
        this.book = book;
    }

    public void setData (){
        Image bookImage = new Image(book.getBook_image());
        bookimg.setImage(bookImage);
        bookName.setText(book.getBook_Title());
        bookauthor.setText(book.getAuthor());
        bookCategory_label.setText(book.getCategory());
        bookPublicationYear_label.setText("Publication year: " + book.getPublication_year());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservedBook_btn.setDisable(true);
    }
}
