package com.example.librarysystem.borrowerpage.showtransactions;

import Book.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

public class BorrowerBookCardTransaction {

    @FXML
    private Label bookCategory_label;

    @FXML
    private Label bookName_label;

    @FXML
    private Label bookQuanityTransaction_label;

    @FXML
    private Label bookReturnDate_label;

    @FXML
    private Label bookauthor_label;

    @FXML
    private ImageView bookimg;

    @FXML
    private Label bookprice_label;

    @FXML
    private Label isbookReturn_label;

    private Book book;


    public void setBook(Book book){
        this.book = book;
    }

    public void setData(int borrowerBookQuantity , LocalDate returnDate , boolean isBookReturned){
        Image bookImage = new Image(book.getBook_image());
        bookimg.setImage(bookImage);
        bookName_label.setText(book.getBook_Title());
        bookCategory_label.setText(book.getCategory());
        bookauthor_label.setText(book.getAuthor());
        bookprice_label.setText("$" + book.getPriceBorrowing());
        bookQuanityTransaction_label.setText(String.valueOf(borrowerBookQuantity)); // casting from integer to string
        bookReturnDate_label.setText(String.valueOf(returnDate)); // casting from local date to string
        if(isBookReturned){
            isbookReturn_label.setText("Returned: " + "Yes");
        }else{
            isbookReturn_label.setText("Returned: " + "No");
        }

    }

}
